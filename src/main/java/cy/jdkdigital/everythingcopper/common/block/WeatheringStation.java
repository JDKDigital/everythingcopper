package cy.jdkdigital.everythingcopper.common.block;

import com.mojang.serialization.MapCodec;
import cy.jdkdigital.everythingcopper.common.block.entity.WeatheringStationBlockEntity;
import cy.jdkdigital.everythingcopper.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
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
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

public class WeatheringStation extends BaseEntityBlock
{
    public static final MapCodec<WeatheringStation> CODEC = simpleCodec(WeatheringStation::new);

    public static final BooleanProperty WET = BooleanProperty.create("wet");

    private static final VoxelShape OUTER_SHAPE = Shapes.block();
    private static final VoxelShape SHAPE = Shapes.join(OUTER_SHAPE, Block.box(2.0D, Math.max(2, 1), 2.0D, 14.0D, 16.0D, 14.0D), BooleanOp.ONLY_FIRST);

    public WeatheringStation(Properties properties) {
        super(properties);

        this.registerDefaultState(this.defaultBlockState().setValue(WET, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
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

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        final BlockEntity blockEntity = pLevel.getBlockEntity(pPos);

        if (blockEntity instanceof WeatheringStationBlockEntity weatheringStationBlockEntity) {
            AtomicBoolean hasInteracted = new AtomicBoolean(pLevel.isClientSide());
            if (!pLevel.isClientSide()) {
                ItemStack outputItem = weatheringStationBlockEntity.inventoryHandler.getStackInSlot(WeatheringStationBlockEntity.SLOT_OUTPUT);
                if (pStack.isEmpty()) {
                    if (!outputItem.isEmpty()) {
                        // Put output in hand
                        pPlayer.setItemInHand(pHand, outputItem.copy());
                        outputItem.setCount(0);
                        hasInteracted.set(true);
                    } else {
                        // take input item out
                        ItemStack inputItem = weatheringStationBlockEntity.inventoryHandler.getStackInSlot(WeatheringStationBlockEntity.SLOT_INPUT);
                        pPlayer.setItemInHand(pHand, inputItem.copy());
                        inputItem.setCount(0);
                        hasInteracted.set(true);
                    }
                } else if (ItemStack.isSameItemSameComponents(pStack, outputItem)) {
                    // Grab output into existing held stack
                    int grabbedCount = Math.min(pStack.getMaxStackSize() - pStack.getCount(), outputItem.getCount());
                    pStack.grow(grabbedCount);
                    outputItem.shrink(grabbedCount);
                    hasInteracted.set(true);
                } else {
                    // Insert item
                    if (weatheringStationBlockEntity.inventoryHandler.isItemValid(WeatheringStationBlockEntity.SLOT_INPUT, pStack)) {
                        ItemStack leftOver = weatheringStationBlockEntity.inventoryHandler.insertItem(WeatheringStationBlockEntity.SLOT_INPUT, pStack, false);
                        pPlayer.setItemInHand(pHand, leftOver);
                        hasInteracted.set(true);
                    } else if (pStack.is(Items.WATER_BUCKET)) {
                        if (weatheringStationBlockEntity.fluidInventory.getTankCapacity(0) - weatheringStationBlockEntity.fluidInventory.getFluidInTank(0).getAmount() >= 1000) {
                            weatheringStationBlockEntity.fluidInventory.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.EXECUTE);
                            if (!pPlayer.isCreative()) {
                                pPlayer.setItemInHand(pHand, pStack.getCraftingRemainingItem());
                            }
                            hasInteracted.set(true);
                        }
                    }
                }
            }

            if (hasInteracted.get()) {
                weatheringStationBlockEntity.setChanged();
                return ItemInteractionResult.SUCCESS;
            }
        }
        return super.useItemOn(pStack, pState, pLevel, pPos, pPlayer, pHand, pHitResult);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState oldState, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (oldState.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof WeatheringStationBlockEntity weatheringStationBlockEntity) {
                // Drop inventory
                for (int slot = 0; slot < weatheringStationBlockEntity.inventoryHandler.getSlots(); ++slot) {
                    Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), weatheringStationBlockEntity.inventoryHandler.getStackInSlot(slot));
                }
            }
        }
        super.onRemove(oldState, worldIn, pos, newState, isMoving);
    }
}
