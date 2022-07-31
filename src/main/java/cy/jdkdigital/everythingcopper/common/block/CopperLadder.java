package cy.jdkdigital.everythingcopper.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class CopperLadder extends LadderBlock implements IWeatheringBlock
{
    private final WeatherState weatherState;

    public CopperLadder(WeatherState state, Properties properties) {
        super(properties);
        weatherState = state;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        this.onRandomTick(blockState, level, blockPos, random);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return IWeatheringBlock.getNext(blockState.getBlock()).isPresent();
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

    private boolean isSupportSolid(BlockGetter level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        return blockstate.isFaceSturdy(level, pos, Direction.UP);
    }

    private boolean isCopperLadder(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos).getBlock() instanceof CopperLadder;
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos pos) {
        // valid if below or above are copper ladders or if opposite face is solid
        return super.canSurvive(blockState, level, pos) || isSupportSolid(level, pos.above()) || isCopperLadder(level, pos.below());
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor level, BlockPos blockPos, BlockPos blockPos1) {
        if (!blockState.canSurvive(level, blockPos)) {
            level.scheduleTick(blockPos, this, 1);
        }

        return super.updateShape(blockState, direction, blockState1, level, blockPos, blockPos1);
    }

    @Override
    public void tick(BlockState blockState, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (!blockState.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }
}
