package cy.jdkdigital.everythingcopper.common.block.entity;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.block.WeatheringStation;
import cy.jdkdigital.everythingcopper.common.container.ManualItemHandler;
import cy.jdkdigital.everythingcopper.common.container.WeatheringStationMenu;
import cy.jdkdigital.everythingcopper.common.item.ICopperItem;
import cy.jdkdigital.everythingcopper.init.ModBlockEntities;
import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

public class WeatheringStationBlockEntity extends BlockEntity implements MenuProvider, Nameable
{
    public static int SLOT_INPUT = 0;
    public static int SLOT_FUEL = 1;
    public static int SLOT_OUTPUT = 2;

    private int tickCounter = 0;
    public boolean isRunning = false;
    public int fluidId = 0;
    public int progress = 0;

    private LazyOptional<IItemHandlerModifiable> inventoryHandler = LazyOptional.of(() -> new ManualItemHandler(3)
    {
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if (slot == SLOT_FUEL) {
                if (stack.is(Items.WATER_BUCKET)) {
                    return true;
                }
                var fluid = FluidUtil.getFluidContained(stack);
                if (fluid.isPresent()) {
                    return fluid.get().getFluid().isSame(Fluids.WATER);
                }
            }
            return slot == SLOT_INPUT && ((stack.getItem() instanceof ICopperItem && ICopperItem.canAge(stack)) || (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof WeatheringCopper));
        }

//        @Override
//        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate, boolean fromAutomation) {
//            if (fromAutomation && slot == SLOT_OUTPUT) {
//                super.extractItem(slot, amount, simulate, false);
//            }
//            return super.extractItem(slot, amount, simulate, fromAutomation);
//        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();

            // Reset if the input is cleared
            if (slot == SLOT_INPUT && this.getStackInSlot(slot).isEmpty()) {
                isRunning = false;
                progress = 0;
            }
        }
    });

    private final LazyOptional<IFluidHandler> fluidInventory = LazyOptional.of(() -> new FluidTank(10000) {
        @Override
        public boolean isFluidValid(FluidStack stack) {
            return super.isFluidValid(stack);
        }

        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            fluidId = Registry.FLUID.getId(getFluid().getFluid());
            if (level instanceof ServerLevel) {
                if (getFluidAmount() >= 100 && !getBlockState().getValue(WeatheringStation.WET)) {
                    level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(WeatheringStation.WET, true));
                } else if (getFluidAmount() < 100 && getBlockState().getValue(WeatheringStation.WET)) {
                    level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(WeatheringStation.WET, false));
                }
            }
        }
    });

    public WeatheringStationBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.WEATHERING_STATION.get(), blockPos, blockState);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (level instanceof ServerLevel) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState blockState, WeatheringStationBlockEntity blockEntity) {
        blockEntity.tickCounter++;

        blockEntity.inventoryHandler.ifPresent(items -> {
            if (blockEntity.tickCounter % 5 == 0) {
                // Consume input fluid item
                blockEntity.fluidInventory.ifPresent(fluid -> {
                    int availableFluidSpace = fluid.getTankCapacity(0) - fluid.getFluidInTank(0).getAmount();
                    ItemStack fuelStack = items.getStackInSlot(SLOT_FUEL);
                    if (!fuelStack.isEmpty()) {
                        if (fuelStack.is(Items.WATER_BUCKET) && availableFluidSpace >= 1000) {
                            items.setStackInSlot(SLOT_FUEL, fuelStack.getContainerItem());
                            fluid.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.EXECUTE);
                        } else {
                            var fuelContainer = FluidUtil.getFluidHandler(fuelStack);
                            fuelContainer.ifPresent(iFluidHandlerItem -> {
                                var fuel = fuelContainer.map(handler -> handler.drain(Integer.MAX_VALUE, IFluidHandler.FluidAction.SIMULATE));
                                if (fuel.isPresent() && fuel.get().getAmount() > 0 && fuel.get().getFluid().isSame(Fluids.WATER)) {
                                    int transferAmount = Math.min(fuel.get().getAmount(), availableFluidSpace);
                                    FluidUtil.tryFluidTransfer(fluid, iFluidHandlerItem, transferAmount, true);
                                }
                            });
                        }
                    }
                });
            }

            if (blockEntity.isRunning) {
                blockEntity.progress++;
            }

            if (blockEntity.isRunning && blockEntity.progress >= 200) {
                // finish processing
                AtomicBoolean hasFinished = new AtomicBoolean(false);
                ItemStack inputItem = items.getStackInSlot(SLOT_INPUT);
                ItemStack outputItem = items.getStackInSlot(SLOT_OUTPUT);
                if (inputItem.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof WeatheringCopper weatheringBlock) {
                    weatheringBlock.getNext(blockItem.getBlock().defaultBlockState()).ifPresent(newState -> {
                        var newItem = new ItemStack(newState.getBlock().asItem());
                        if (outputItem.isEmpty()) {
                            items.setStackInSlot(SLOT_OUTPUT, newItem);
                            hasFinished.set(true);
                        } else if (ItemStack.isSame(newItem, outputItem) && outputItem.getCount() < outputItem.getMaxStackSize()) {
                            outputItem.grow(1);
                            hasFinished.set(true);
                        }
                    });
                } else if (outputItem.isEmpty() && inputItem.getItem() instanceof ICopperItem && ICopperItem.canAge(inputItem)) {
                    ItemStack newItem = inputItem.copy();
                    ICopperItem.setAge(newItem, WeatheringUtils.nextState(ICopperItem.getAge(newItem)));
                    items.setStackInSlot(SLOT_OUTPUT, newItem);
                    hasFinished.set(true);
                }

                if (hasFinished.get()) {
                    inputItem.shrink(1);
                    blockEntity.isRunning = false;
                    blockEntity.progress = 0;
                }
            }

            if (!blockEntity.isRunning) {
                blockEntity.fluidInventory.ifPresent(fluid -> {
                    ItemStack inputItem = items.getStackInSlot(SLOT_INPUT);
                    if (!inputItem.isEmpty() && fluid.getFluidInTank(0).getAmount() >= 100) {
                        fluid.drain(100, IFluidHandler.FluidAction.EXECUTE);
                        blockEntity.isRunning = true;
                        blockEntity.progress = 0;
                    }
                });
            }
        });
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new WeatheringStationMenu(id, inventory, this);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return getName();
    }

    @Override
    public @NotNull Component getName() {
        return new TranslatableComponent("block." + EverythingCopper.MODID + ".weathering_station");
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventoryHandler.cast();
        } else if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return fluidInventory.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.loadPacketNBT(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        this.savePacketNBT(tag);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return saveWithId();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        this.loadPacketNBT(pkt.getTag());
        if (level instanceof ClientLevel) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 0);
        }
    }

    public void savePacketNBT(CompoundTag tag) {
        getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(inv -> {
            CompoundTag compound = ((ItemStackHandler) inv).serializeNBT();
            tag.put("inv", compound);
        });

        getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).ifPresent(fluid -> {
            CompoundTag nbt = new CompoundTag();
            ((FluidTank) fluid).writeToNBT(nbt);
            tag.put("fluid", nbt);
        });

        tag.putInt("progress", progress);
    }

    public void loadPacketNBT(CompoundTag tag) {
        if (tag.contains("inv")) {
            getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(inv -> ((ItemStackHandler) inv).deserializeNBT(tag.getCompound("inv")));
        }

        if (tag.contains("fluid")) {
            getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).ifPresent(fluid -> {
                ((FluidTank) fluid).readFromNBT(tag.getCompound("fluid"));
            });
        }

        progress = tag.getInt("progress");
    }
}
