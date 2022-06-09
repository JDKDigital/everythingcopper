package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.event.loot.CopperBlockLootModifier;
import cy.jdkdigital.everythingcopper.event.loot.CopperItemLootModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = EverythingCopper.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModLootModifiers
{
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, EverythingCopper.MODID);

    public static final RegistryObject<GlobalLootModifierSerializer<CopperItemLootModifier>> COPPER_BOOTS = LOOT_SERIALIZERS.register("copper_boots", CopperItemLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperItemLootModifier>> COPPER_CHESTPLATE = LOOT_SERIALIZERS.register("copper_chestplate", CopperItemLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperItemLootModifier>> COPPER_HELMET = LOOT_SERIALIZERS.register("copper_helmet", CopperItemLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperItemLootModifier>> COPPER_HORSE_ARMOR = LOOT_SERIALIZERS.register("copper_horse_armor", CopperItemLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperItemLootModifier>> COPPER_LEGGINGS = LOOT_SERIALIZERS.register("copper_leggings", CopperItemLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperItemLootModifier>> COPPER_PICKAXE = LOOT_SERIALIZERS.register("copper_pickaxe", CopperItemLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperItemLootModifier>> COPPER_SHOVEL = LOOT_SERIALIZERS.register("copper_shovel", CopperItemLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperItemLootModifier>> COPPER_SWORD = LOOT_SERIALIZERS.register("copper_sword", CopperItemLootModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<CopperBlockLootModifier>> COPPER_RAIL = LOOT_SERIALIZERS.register("copper_rail", CopperBlockLootModifier.Serializer::new);
}

