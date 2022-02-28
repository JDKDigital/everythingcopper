package cy.jdkdigital.everythingcopper.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class CopperDoor extends DoorBlock implements IWeatheringBlock
{
    private final WeatheringCopper.WeatherState weatherState;

    public CopperDoor(WeatheringCopper.WeatherState state, Properties properties) {
        super(properties);
        weatherState = state;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, Random random) {
        this.onRandomTick(blockState, level, blockPos, random);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return blockState.getValue(DoorBlock.HALF).equals(DoubleBlockHalf.LOWER) && IWeatheringBlock.getNext(blockState.getBlock()).isPresent();
    }

    @Override
    public WeatheringCopper.WeatherState getAge() {
        return this.weatherState;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockpos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        InteractionResult res = applyWax(blockState, level, blockpos, player, hand);
        return res.equals(InteractionResult.PASS) ? super.use(blockState, level, blockpos, player, hand, hitResult) : res;
    }

    @Override
    public void applyChangeOverTime(BlockState blockState, ServerLevel level, BlockPos pos, Random random) {
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
                BlockPos replacedPosition = pos;
                if (blockState.getValue(DoorBlock.HALF).equals(DoubleBlockHalf.UPPER)) {
                    replacedPosition = replacedPosition.below();
                }
                level.setBlock(replacedPosition, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                level.setBlock(replacedPosition, newState.setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER), Block.UPDATE_CLIENTS);
                level.setBlock(replacedPosition.above(), newState.setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL_IMMEDIATE);
            });
        }
    }
}
