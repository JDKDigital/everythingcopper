package cy.jdkdigital.everythingcopper.init;

import com.mojang.serialization.MapCodec;
import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.event.loot.CopperBlockLootModifier;
import cy.jdkdigital.everythingcopper.event.loot.CopperItemLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class ModLootModifiers
{
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, EverythingCopper.MODID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<CopperItemLootModifier>> COPPER_ITEM = LOOT_SERIALIZERS.register("copper_item", CopperItemLootModifier.CODEC);
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<CopperBlockLootModifier>> COPPER_BLOCK = LOOT_SERIALIZERS.register("copper_block", CopperBlockLootModifier.CODEC);
}

