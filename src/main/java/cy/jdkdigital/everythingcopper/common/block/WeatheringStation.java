package cy.jdkdigital.everythingcopper.common.block;

import cy.jdkdigital.everythingcopper.common.block.entity.WeatheringStationBlockEntity;
import cy.jdkdigital.everythingcopper.init.ModBlockEntities;
import cy.jdkdigital.productivebees.common.block.entity.UpgradeableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

public class WeatheringStation extends BaseEntityBlock
{
    public static final BooleanProperty WET = BooleanProperty.create("wet");

    private static final VoxelShape OUTER_SHAPE = Shapes.block();
    private static final VoxelShape SHAPE = Shapes.join(OUTER_SHAPE, Block.box(2.0D, Math.max(2, 1), 2.0D, 14.0D, 16.0D, 14.0D), BooleanOp.ONLY_FIRST);

    public WeatheringStation(Properties properties) {
        super(properties);

        this.registerDefaultState(this.defaultBlockState().setValue(WET, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WET);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new WeatheringStationBlockEntity(pos, blockState);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> entityType) {
        return level.isClientSide ? null : createTickerHelper(entityType, ModBlockEntities.WEATHERING_STATION.get(), WeatheringStationBlockEntity::tick);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public VoxelShape getShape(BlockState p_51973_, BlockGetter p_51974_, BlockPos p_51975_, CollisionContext p_51976_) {
        return SHAPE;
    }

    public VoxelShape getInteractionShape(BlockState p_51969_, BlockGetter p_51970_, BlockPos p_51971_) {
        return OUTER_SHAPE;
    }

    public VoxelShape getCollisionShape(BlockState p_51990_, BlockGetter p_51991_, BlockPos p_51992_, CollisionContext p_51993_) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide()) {
            final BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof WeatheringStationBlockEntity weatheringStationBlockEntity) {
                ItemStack heldItem = player.getItemInHand(hand);
//                if (heldItem.isEmpty() && player.isShiftKeyDown()) {
//                    NetworkHooks.openGui((ServerPlayer) player, weatheringStationBlockEntity, packetBuffer -> packetBuffer.writeBlockPos(blockEntity.getBlockPos()));
//                    return InteractionResult.SUCCESS;
//                }

                AtomicBoolean hasInteracted = new AtomicBoolean(false);
                weatheringStationBlockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                    ItemStack outputItem = h.getStackInSlot(WeatheringStationBlockEntity.SLOT_OUTPUT);
                    if (heldItem.isEmpty()) {
                        if (!outputItem.isEmpty()) {
                            // Put output in hand
                            player.setItemInHand(hand, outputItem.copy());
                            outputItem.setCount(0);
                            hasInteracted.set(true);
                        } else {
                            // take input item out
                            ItemStack inputItem = h.getStackInSlot(WeatheringStationBlockEntity.SLOT_INPUT);
                            player.setItemInHand(hand, inputItem.copy());
                            inputItem.setCount(0);
                            hasInteracted.set(true);
                        }
                    } else if (ItemStack.isSame(heldItem, outputItem)) {
                        // Grab output into existing held stack
                        int grabbedCount = Math.min(heldItem.getMaxStackSize() - heldItem.getCount(), outputItem.getCount());
                        heldItem.grow(grabbedCount);
                        outputItem.shrink(grabbedCount);
                        hasInteracted.set(true);
                    } else {
                        // Insert item
                        if (h.isItemValid(WeatheringStationBlockEntity.SLOT_INPUT, heldItem)) {
                            ItemStack leftOver = h.insertItem(WeatheringStationBlockEntity.SLOT_INPUT, heldItem, false);
                            player.setItemInHand(hand, leftOver);
                            hasInteracted.set(true);
                        } else if (heldItem.is(Items.WATER_BUCKET)) {
                            weatheringStationBlockEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).ifPresent(f -> {
                                if (f.getTankCapacity(0) - f.getFluidInTank(0).getAmount() >= 1000) {
                                    f.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.EXECUTE);
                                    if (!player.isCreative()) {
                                        player.setItemInHand(hand, heldItem.getContainerItem());
                                    }
                                    hasInteracted.set(true);
                                }
                            });
                        }
                    }
                });

                if (hasInteracted.get()) {
                    weatheringStationBlockEntity.setChanged();
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState oldState, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (oldState.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity != null) {
                // Drop inventory
                tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                    for (int slot = 0; slot < handler.getSlots(); ++slot) {
                        Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), handler.getStackInSlot(slot));
                    }
                });
            }
        }
        super.onRemove(oldState, worldIn, pos, newState, isMoving);
    }
}
