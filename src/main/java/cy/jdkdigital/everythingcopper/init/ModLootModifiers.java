package cy.jdkdigital.everythingcopper.init;

import com.mojang.serialization.Codec;
import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.event.loot.CopperBlockLootModifier;
import cy.jdkdigital.everythingcopper.event.loot.CopperItemLootModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = EverythingCopper.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModLootModifiers
{
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, EverythingCopper.MODID);

    public static final RegistryObject<Codec<CopperItemLootModifier>> COPPER_ITEM = LOOT_SERIALIZERS.register("copper_item", CopperItemLootModifier.CODEC);
    public static final RegistryObject<Codec<CopperBlockLootModifier>> COPPER_BLOCK = LOOT_SERIALIZERS.register("copper_block", CopperBlockLootModifier.CODEC);
}

