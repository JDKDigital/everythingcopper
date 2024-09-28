package cy.jdkdigital.everythingcopper.common.block.entity;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.block.WeatheringStation;
import cy.jdkdigital.everythingcopper.common.container.ManualItemHandler;
import cy.jdkdigital.everythingcopper.common.item.ICopperItem;
import cy.jdkdigital.everythingcopper.init.ModBlockEntities;
import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Nameable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

public class WeatheringStationBlockEntity extends BlockEntity implements Nameable
{
    public static int SLOT_INPUT = 0;
    public static int SLOT_FUEL = 1;
    public static int SLOT_OUTPUT = 2;

    private int tickCounter = 0;
    public boolean isRunning = false;
    public int fluidId = 0;
    public int progress = 0;

    public IItemHandlerModifiable inventoryHandler = new ManualItemHandler(3)
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

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();

            // Reset if the input is cleared
            if (slot == SLOT_INPUT && this.getStackInSlot(slot).isEmpty()) {
                isRunning = false;
                progress = 0;
            }
        }
    };

    public final IFluidHandler fluidInventory = new FluidTank(10000) {
        @Override
        public boolean isFluidValid(FluidStack stack) {
            return super.isFluidValid(stack);
        }

        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            fluidId = BuiltInRegistries.FLUID.getId(getFluid().getFluid());
            if (level instanceof ServerLevel) {
                if (getFluidAmount() >= 100 && !getBlockState().getValue(WeatheringStation.WET)) {
                    level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(WeatheringStation.WET, true));
                } else if (getFluidAmount() < 100 && getBlockState().getValue(WeatheringStation.WET)) {
                    level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(WeatheringStation.WET, false));
                }
            }
        }
    };

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

        if (blockEntity.tickCounter % 5 == 0) {
            // Consume input fluid item
            int availableFluidSpace = blockEntity.fluidInventory.getTankCapacity(0) - blockEntity.fluidInventory.getFluidInTank(0).getAmount();
            ItemStack fuelStack = blockEntity.inventoryHandler.getStackInSlot(SLOT_FUEL);
            if (!fuelStack.isEmpty()) {
                if (fuelStack.is(Items.WATER_BUCKET) && availableFluidSpace >= 1000) {
                    blockEntity.inventoryHandler.setStackInSlot(SLOT_FUEL, fuelStack.getCraftingRemainingItem());
                    blockEntity.fluidInventory.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.EXECUTE);
                } else {
                    var fuelContainer = FluidUtil.getFluidHandler(fuelStack);
                    fuelContainer.ifPresent(iFluidHandlerItem -> {
                        var fuel = fuelContainer.map(handler -> handler.drain(Integer.MAX_VALUE, IFluidHandler.FluidAction.SIMULATE));
                        if (fuel.isPresent() && fuel.get().getAmount() > 0 && fuel.get().getFluid().isSame(Fluids.WATER)) {
                            int transferAmount = Math.min(fuel.get().getAmount(), availableFluidSpace);
                            FluidUtil.tryFluidTransfer(blockEntity.fluidInventory, iFluidHandlerItem, transferAmount, true);
                        }
                    });
                }
            }
        }

        if (blockEntity.isRunning) {
            blockEntity.progress++;
        }

        if (blockEntity.isRunning && blockEntity.progress >= 200) {
            // finish processing
            AtomicBoolean hasFinished = new AtomicBoolean(false);
            ItemStack inputItem = blockEntity.inventoryHandler.getStackInSlot(SLOT_INPUT);
            ItemStack outputItem = blockEntity.inventoryHandler.getStackInSlot(SLOT_OUTPUT);
            if (inputItem.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof WeatheringCopper weatheringBlock) {
                weatheringBlock.getNext(blockItem.getBlock().defaultBlockState()).ifPresent(newState -> {
                    var newItem = new ItemStack(newState.getBlock().asItem());
                    if (outputItem.isEmpty()) {
                        blockEntity.inventoryHandler.setStackInSlot(SLOT_OUTPUT, newItem);
                        hasFinished.set(true);
                    } else if (ItemStack.isSameItemSameComponents(newItem, outputItem) && outputItem.getCount() < outputItem.getMaxStackSize()) {
                        outputItem.grow(1);
                        hasFinished.set(true);
                    }
                });
            } else if (outputItem.isEmpty() && inputItem.getItem() instanceof ICopperItem && ICopperItem.canAge(inputItem)) {
                ItemStack newItem = inputItem.copy();
                ICopperItem.setAge(newItem, WeatheringUtils.nextState(ICopperItem.getAge(newItem)));
                blockEntity.inventoryHandler.setStackInSlot(SLOT_OUTPUT, newItem);
                hasFinished.set(true);
            }

            if (hasFinished.get()) {
                inputItem.shrink(1);
                blockEntity.isRunning = false;
                blockEntity.progress = 0;
            }
        }

        if (!blockEntity.isRunning) {
            ItemStack inputItem = blockEntity.inventoryHandler.getStackInSlot(SLOT_INPUT);
            if (!inputItem.isEmpty() && blockEntity.fluidInventory.getFluidInTank(0).getAmount() >= 100) {
                blockEntity.fluidInventory.drain(100, IFluidHandler.FluidAction.EXECUTE);
                blockEntity.isRunning = true;
                blockEntity.progress = 0;
            }
        }
    }

    @Override
    public @NotNull Component getDisplayName() {
        return getName();
    }

    @Override
    public @NotNull Component getName() {
        return Component.translatable("block." + EverythingCopper.MODID + ".weathering_station");
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        this.loadPacketNBT(pTag, pRegistries);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        this.savePacketNBT(pTag, pRegistries);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithId(pRegistries);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
        super.onDataPacket(net, pkt, lookupProvider);
        this.loadPacketNBT(pkt.getTag(), lookupProvider);
        if (level instanceof ClientLevel) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 0);
        }
    }

    public void savePacketNBT(CompoundTag tag, HolderLookup.Provider pRegistries) {
        CompoundTag compound = ((ItemStackHandler) inventoryHandler).serializeNBT(pRegistries);
        tag.put("inv", compound);

        CompoundTag nbt = new CompoundTag();
        ((FluidTank) fluidInventory).writeToNBT(pRegistries, nbt);
        tag.put("fluid", nbt);

        tag.putInt("progress", progress);
    }

    public void loadPacketNBT(CompoundTag tag, HolderLookup.Provider pRegistries) {
        if (tag.contains("inv")) {
            ((ItemStackHandler) inventoryHandler).deserializeNBT(pRegistries, tag.getCompound("inv"));
        }

        if (tag.contains("fluid")) {
            ((FluidTank) fluidInventory).readFromNBT(pRegistries, tag.getCompound("fluid"));
        }

        progress = tag.getInt("progress");
    }
}
