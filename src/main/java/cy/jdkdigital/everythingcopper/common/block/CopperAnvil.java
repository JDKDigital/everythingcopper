package cy.jdkdigital.everythingcopper.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class CopperAnvil extends AnvilBlock implements IWeatheringBlock
{
    private final WeatherState weatherState;

    public CopperAnvil(WeatherState state, Properties properties) {
        super(properties);
        weatherState = state;
    }

    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, Random random) {
        this.onRandomTick(blockState, level, blockPos, random);
    }

    public boolean isRandomlyTicking(BlockState blockState) {
        return IWeatheringBlock.getNext(blockState.getBlock()).isPresent();
    }

    public WeatherState getAge() {
        return this.weatherState;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockpos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        InteractionResult res = applyWax(blockState, level, blockpos, player, hand);
        return res.equals(InteractionResult.PASS) ? super.use(blockState, level, blockpos, player, hand, hitResult) : res;
    }
}
