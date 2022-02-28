package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.effect.CopperPoisoning;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects
{
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EverythingCopper.MODID);

    public static final RegistryObject<MobEffect> COPPER_POISONING = EFFECTS.register("copper_poisoning", () -> new CopperPoisoning(MobEffectCategory.HARMFUL, 12741452));

    public static final FoodProperties NUGGY = (new FoodProperties.Builder()).nutrition(0).saturationMod(0).alwaysEat()
            .effect(() -> new MobEffectInstance(COPPER_POISONING.get(), 1, 1), 1.0f)
            .build();
}
