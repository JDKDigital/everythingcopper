package cy.jdkdigital.everythingcopper.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class CopperButton extends ButtonBlock implements IWeatheringBlock
{
    private final WeatherState weatherState;

    public CopperButton(WeatherState state, Properties properties) {
        super(false, properties);
        weatherState = state;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, Random random) {
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

    @Override
    protected SoundEvent getSound(boolean stateOn) {
        return stateOn ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF;
    }

    @Override
    public int getPressDuration() {
        return switch (getAge()) {
            case OXIDIZED -> 45;
            case WEATHERED -> 40;
            case EXPOSED -> 35;
            default -> 30; // wood button equivalent
        };
    }
}