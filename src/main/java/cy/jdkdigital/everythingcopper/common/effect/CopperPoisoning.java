package cy.jdkdigital.everythingcopper.common.effect;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class CopperPoisoning extends InstantenousMobEffect {
    public static final ResourceKey<DamageType> COPPER_POISONING = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(EverythingCopper.MODID, "copper_poisoning"));
    public static final ResourceKey<DamageType> COPPER_POISONING1 = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(EverythingCopper.MODID, "copper_poisoning1"));
    public static final ResourceKey<DamageType> COPPER_POISONING2 = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(EverythingCopper.MODID, "copper_poisoning2"));
    public static final ResourceKey<DamageType> COPPER_POISONING3 = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(EverythingCopper.MODID, "copper_poisoning3"));

    public CopperPoisoning(MobEffectCategory category, int color) {
        super(category, color);

    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) {
            ResourceKey<DamageType> POISONING = switch (entity.level().random.nextInt(4)) {
                case 0 -> COPPER_POISONING1;
                case 1 -> COPPER_POISONING2;
                case 2 -> COPPER_POISONING3;
                default -> COPPER_POISONING;
            };
            Holder.Reference<DamageType> damageType = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(POISONING);
            entity.hurt(new DamageSource(damageType), (float)(2 << amplifier));
        }
        super.applyEffectTick(entity, amplifier);
    }
}
