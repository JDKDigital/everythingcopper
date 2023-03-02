package cy.jdkdigital.everythingcopper.common.container;

import cy.jdkdigital.everythingcopper.common.block.entity.WeatheringStationBlockEntity;
import cy.jdkdigital.everythingcopper.init.ModContainerTypes;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.Objects;

public class WeatheringStationMenu extends AbstractContainer
{
    public final WeatheringStationBlockEntity blockEntity;

    public WeatheringStationMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    public WeatheringStationMenu(int id, Inventory inventory, WeatheringStationBlockEntity blockEntity) {
        super(ModContainerTypes.WEATHERING_STATION.get(), id);

        this.blockEntity = blockEntity;

        addDataSlots(new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> blockEntity.progress;
                    case 1 -> blockEntity.isRunning ? 1 : 0;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> blockEntity.progress = value;
                    case 1 -> blockEntity.isRunning = value > 0;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        // Fluid
        addDataSlots(new ContainerData()
        {
            @Override
            public int get(int i) {
                return i == 0 ? blockEntity.fluidId : blockEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).map(fluidHandler -> fluidHandler.getFluidInTank(0).getAmount()).orElse(0);
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0:
                        blockEntity.fluidId = value;
                    case 1:
                        blockEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).ifPresent(fluidHandler -> {
                            FluidStack fluid = fluidHandler.getFluidInTank(0);
                            if (fluid.isEmpty()) {
                                fluidHandler.fill(new FluidStack(Registry.FLUID.byId(blockEntity.fluidId), value), IFluidHandler.FluidAction.EXECUTE);
                            } else {
                                fluid.setAmount(value);
                            }
                        });
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(inv -> {
            if (inv instanceof ManualItemHandler itemHandler) {
                addSlot(new ManualSlotItemHandler(itemHandler, WeatheringStationBlockEntity.SLOT_INPUT, 60, 54));
                addSlot(new ManualSlotItemHandler(itemHandler, WeatheringStationBlockEntity.SLOT_FUEL, 80, 54));
                addSlot(new ManualSlotItemHandler(itemHandler, WeatheringStationBlockEntity.SLOT_OUTPUT, 120, 54));
            }
        });

        layoutPlayerInventorySlots(inventory, 0, 8, 84);
    }

    private static WeatheringStationBlockEntity getTileEntity(final Inventory playerInventory, final FriendlyByteBuf data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null!");
        Objects.requireNonNull(data, "data cannot be null!");
        final BlockEntity tileAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
        if (tileAtPos instanceof WeatheringStationBlockEntity) {
            return (WeatheringStationBlockEntity) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }

    @Override
    public boolean stillValid(Player player) {
        return this.blockEntity != null && player.distanceToSqr(this.blockEntity.getBlockPos().getX() + 0.5D, this.blockEntity.getBlockPos().getY() + 0.5D, this.blockEntity.getBlockPos().getZ() + 0.5D) <= 64.0D;
    }

    public boolean isRunning() {
        return this.blockEntity.isRunning;
    }

    public int getLitProgress() {
        return this.blockEntity.progress * 13 / 200;
    }
}
