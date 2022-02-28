package cy.jdkdigital.everythingcopper.common.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class CopperPoisoning extends InstantenousMobEffect {
    public static final DamageSource COPPER_POISONING = new DamageSource("everythingcopper.copper_poisoning");
    public static final DamageSource COPPER_POISONING1 = new DamageSource("everythingcopper.copper_poisoning1");
    public static final DamageSource COPPER_POISONING2 = new DamageSource("everythingcopper.copper_poisoning2");
    public static final DamageSource COPPER_POISONING3= new DamageSource("everythingcopper.copper_poisoning3");

    public CopperPoisoning(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level.isClientSide()) {
            switch (entity.level.random.nextInt(4)) {
                case 0 -> entity.hurt(COPPER_POISONING1, (float)(2 << amplifier));
                case 1 -> entity.hurt(COPPER_POISONING2, (float)(2 << amplifier));
                case 2 -> entity.hurt(COPPER_POISONING3, (float)(2 << amplifier));
                default -> entity.hurt(COPPER_POISONING, (float)(2 << amplifier));
            }
        }
        super.applyEffectTick(entity, amplifier);
    }
}
