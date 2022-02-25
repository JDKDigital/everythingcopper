package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.event.loot.CopperLootModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = EverythingCopper.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModLootModifiers
{
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_SERIALIZERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, EverythingCopper.MODID);

    public static final RegistryObject<GlobalLootModifierSerializer<CopperLootModifier>> COPPER_BOOTS = LOOT_SERIALIZERS.register("copper_boots_loot", CopperLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperLootModifier>> COPPER_CHESTPLATE = LOOT_SERIALIZERS.register("copper_chestplate_loot", CopperLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperLootModifier>> COPPER_HELMET = LOOT_SERIALIZERS.register("copper_helmet_loot", CopperLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperLootModifier>> COPPER_HORSE_ARMOR = LOOT_SERIALIZERS.register("copper_horse_armor_loot", CopperLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperLootModifier>> COPPER_LEGGINGS = LOOT_SERIALIZERS.register("copper_leggings_loot", CopperLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperLootModifier>> COPPER_PICKAXE = LOOT_SERIALIZERS.register("copper_pickaxe_loot", CopperLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperLootModifier>> COPPER_SHOVEL = LOOT_SERIALIZERS.register("copper_shovel_loot", CopperLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperLootModifier>> COPPER_SWORD = LOOT_SERIALIZERS.register("copper_sword_loot", CopperLootModifier.Serializer::new);
}

