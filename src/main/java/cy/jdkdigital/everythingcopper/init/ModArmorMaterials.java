package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ModArmorMaterials
{
    @SuppressWarnings("unchecked")
    private static final Map<ArmorItem.Type, Integer> COPPER_DEFENSE = (EnumMap) Util.make(new EnumMap(ArmorItem.Type.class), (copper) -> {
        copper.put(ArmorItem.Type.HELMET, 2);
        copper.put(ArmorItem.Type.CHESTPLATE, 4);
        copper.put(ArmorItem.Type.LEGGINGS, 5);
        copper.put(ArmorItem.Type.BOOTS, 2);
        copper.put(ArmorItem.Type.BODY, 3);
    });

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, EverythingCopper.MODID);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> COPPER_MATERIAL = ARMOR_MATERIALS.register("copper", () -> new ArmorMaterial(COPPER_DEFENSE, 12, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(Items.COPPER_INGOT), List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(EverythingCopper.MODID, "copper"))), 0.0F, 0.1f));
}
