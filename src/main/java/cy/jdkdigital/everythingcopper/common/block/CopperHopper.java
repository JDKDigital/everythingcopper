package cy.jdkdigital.everythingcopper.common.block;

import cy.jdkdigital.everythingcopper.common.block.entity.CopperHopperBlockEntity;
import cy.jdkdigital.everythingcopper.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class CopperHopper extends HopperBlock implements IWeatheringBlock
{
    private final WeatherState weatherState;

    public CopperHopper(WeatherState state, Properties properties) {
        super(properties);
        weatherState = state;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        this.onRandomTick(blockState, level, blockPos, random);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return IWeatheringBlock.getNext(blockState.getBlock()).isPresent() || super.isRandomlyTicking(blockState);
    }

    @Override
    public WeatherState getAge() {
        return this.weatherState;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockpos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        InteractionResult res = applyWax(blockState, level, blockpos, player, hand);
        return res.equals(InteractionResult.PASS) ? super.use(blockState, level, blockpos, player, hand, hitResult) : res;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new CopperHopperBlockEntity(pos, blockState);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> entityType) {
        return level.isClientSide ? null : createTickerHelper(entityType, ModBlockEntities.COPPER_HOPPER.get(), HopperBlockEntity::pushItemsTick);
    }

    @Override
    public void applyChangeOverTime(BlockState blockState, ServerLevel level, BlockPos pos, RandomSource random) {
        int i = this.getAge().ordinal();
        int j = 0;
        int k = 0;

        for(BlockPos blockpos : BlockPos.withinManhattan(pos, 4, 4, 4)) {
            int l = blockpos.distManhattan(pos);
            if (l > 4) {
                break;
            }

            if (!blockpos.equals(pos)) {
                BlockState blockstate = level.getBlockState(blockpos);
                Block block = blockstate.getBlock();
                if (block instanceof ChangeOverTimeBlock changeOverTimeBlock) {
                    Enum<?> age = changeOverTimeBlock.getAge();
                    if (this.getAge().getClass() == age.getClass()) {
                        int i1 = age.ordinal();
                        if (i1 < i) {
                            return;
                        }

                        if (i1 > i) {
                            ++k;
                        } else {
                            ++j;
                        }
                    }
                }
            }
        }

        float f = (float)(k + 1) / (float)(k + j + 1);
        float f1 = f * f * this.getChanceModifier();
        if (random.nextFloat() < f1) {
            this.getNext(blockState).ifPresent((newState) -> {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof CopperHopperBlockEntity hopperBlockEntity) {
                    CompoundTag tag = hopperBlockEntity.saveWithoutMetadata();
                    hopperBlockEntity.clearContent();

                    level.setBlockAndUpdate(pos, newState);
                    level.getBlockEntity(pos).load(tag);
                }
            });
        }
    }
}
