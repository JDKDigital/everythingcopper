package cy.jdkdigital.everythingcopper.common.block.entity;

import cy.jdkdigital.everythingcopper.common.block.CopperChime;
import cy.jdkdigital.everythingcopper.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.entity.BellBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class CopperChimeBlockEntity extends BellBlockEntity {
    private boolean resonating;
    private int resonationTicks;
    public WeatheringCopper.WeatherState weatherState;
    public Direction facing;

    public CopperChimeBlockEntity(BlockPos pos, BlockState blockState) {
        super(pos, blockState);
        if (blockState.getBlock() instanceof CopperChime chimeBlock) {
            weatherState = chimeBlock.weatherState;
            facing = blockState.getValue(HorizontalDirectionalBlock.FACING);
        }
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.COPPER_CHIME.get();
    }

    private static void tick(Level level, BlockPos pos, BlockState blockState, CopperChimeBlockEntity chimeBlockEntity, ResonationEndAction action) {
        if (chimeBlockEntity.shaking) {
            ++chimeBlockEntity.ticks;
        }

        if (chimeBlockEntity.ticks >= 50) {
            chimeBlockEntity.shaking = false;
            chimeBlockEntity.ticks = 0;
        }

        if (chimeBlockEntity.ticks >= 5 && chimeBlockEntity.resonationTicks == 0 && areBeesNearby(pos, chimeBlockEntity.nearbyEntities)) {
            chimeBlockEntity.resonating = true;
            level.playSound(null, pos, SoundEvents.BELL_RESONATE, SoundSource.BLOCKS, 1.0F, 1.0F);
        }

        if (chimeBlockEntity.resonating) {
            if (chimeBlockEntity.resonationTicks < 40) {
                ++chimeBlockEntity.resonationTicks;
            } else {
                action.run(level, pos, chimeBlockEntity.nearbyEntities);
                chimeBlockEntity.resonating = false;
            }
        }
    }

    public static void serverTick(Level level, BlockPos pos, BlockState blockState, CopperChimeBlockEntity blockEntity) {
        tick(level, pos, blockState, blockEntity, CopperChimeBlockEntity::makeBeesGlow);
    }

    private static boolean areBeesNearby(BlockPos pos, List<LivingEntity> entities) {
        for(LivingEntity livingentity : entities) {
            if (livingentity.isAlive() && !livingentity.isRemoved() && pos.closerToCenterThan(livingentity.position(), 32.0D) && livingentity.getType().is(EntityTypeTags.BEEHIVE_INHABITORS)) {
                return true;
            }
        }
        return false;
    }

    private static void makeBeesGlow(Level level, BlockPos pos, List<LivingEntity> entities) {
        entities.stream().filter((entity) -> {
            return entity.isAlive() && !entity.isRemoved() && pos.closerToCenterThan(entity.position(), 48.0D) && entity.getType().is(EntityTypeTags.BEEHIVE_INHABITORS);
        }).forEach(livingEntity -> livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 120)));
    }

    @FunctionalInterface
    interface ResonationEndAction {
        void run(Level level, BlockPos pos, List<LivingEntity> entities);
    }
}
