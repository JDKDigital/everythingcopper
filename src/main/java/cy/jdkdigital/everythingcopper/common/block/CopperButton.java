package cy.jdkdigital.everythingcopper.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class CopperButton extends ButtonBlock implements IWeatheringBlock
{
    private final WeatherState weatherState;

    public CopperButton(WeatherState state, Properties properties) {
        super(BlockSetType.IRON, getPressDuration(state), properties);
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

    private static int getPressDuration(WeatherState state) {
        return switch (state) {
            case OXIDIZED -> 45;
            case WEATHERED -> 40;
            case EXPOSED -> 35;
            default -> 30; // wood button equivalent
        };
    }
}
