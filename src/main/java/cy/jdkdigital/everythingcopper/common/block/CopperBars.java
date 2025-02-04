package cy.jdkdigital.everythingcopper.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

public class CopperBars extends IronBarsBlock implements IWeatheringBlock
{
    private final WeatherState weatherState;

    public CopperBars(WeatherState state, Properties properties) {
        super(properties);
        weatherState = state;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        this.changeOverTime(blockState, level, blockPos, random);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return WeatheringCopper.getNext(blockState.getBlock()).isPresent();
    }

    @Override
    public WeatherState getAge() {
        return this.weatherState;
    }

    @Override
    public boolean skipRendering(BlockState blockState, BlockState otherBlockState, Direction direction) {
        if (otherBlockState.getBlock() instanceof IronBarsBlock) {
            if (!direction.getAxis().isHorizontal()) {
                return true;
            }

            if (blockState.getValue(PROPERTY_BY_DIRECTION.get(direction)) && otherBlockState.getValue(PROPERTY_BY_DIRECTION.get(direction.getOpposite()))) {
                return true;
            }
        }

        return super.skipRendering(blockState, otherBlockState, direction);
    }
}
