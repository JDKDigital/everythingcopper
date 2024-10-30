package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.item.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, EverythingCopper.MODID);

    public static final DeferredHolder<Item, ? extends Item> COPPER_SWORD = ITEMS.register("copper_sword", () -> new CopperSwordItem(ICopperItem.COPPER_TIER, new Item.Properties().attributes(SwordItem.createAttributes(ICopperItem.COPPER_TIER, 3, -2.4F))));
    public static final DeferredHolder<Item, ? extends Item> COPPER_SHOVEL = ITEMS.register("copper_shovel", () -> new CopperShovelItem(ICopperItem.COPPER_TIER, new Item.Properties().attributes(ShovelItem.createAttributes(ICopperItem.COPPER_TIER, 1.5F, -3.0F))));
    public static final DeferredHolder<Item, ? extends Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", () -> new CopperPickaxeItem(ICopperItem.COPPER_TIER, new Item.Properties().attributes(PickaxeItem.createAttributes(ICopperItem.COPPER_TIER, 1, -2.8F))));
    public static final DeferredHolder<Item, ? extends Item> COPPER_AXE = ITEMS.register("copper_axe", () -> new CopperAxeItem(ICopperItem.COPPER_TIER, new Item.Properties().attributes(AxeItem.createAttributes(ICopperItem.COPPER_TIER, 6.0F, -3.1F))));
    public static final DeferredHolder<Item, ? extends Item> COPPER_HOE = ITEMS.register("copper_hoe", () -> new CopperHoeItem(ICopperItem.COPPER_TIER, new Item.Properties().attributes(HoeItem.createAttributes(ICopperItem.COPPER_TIER, -2, -1.0F))));

    public static final DeferredHolder<Item, ? extends Item> COPPER_HELMET = ITEMS.register("copper_helmet", () -> new CopperArmorItem(ModArmorMaterials.COPPER_MATERIAL, ArmorItem.Type.HELMET, (new Item.Properties().durability(180))));
    public static final DeferredHolder<Item, ? extends Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate", () -> new CopperArmorItem(ModArmorMaterials.COPPER_MATERIAL, ArmorItem.Type.CHESTPLATE, (new Item.Properties().durability(280))));
    public static final DeferredHolder<Item, ? extends Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings", () -> new CopperArmorItem(ModArmorMaterials.COPPER_MATERIAL, ArmorItem.Type.LEGGINGS, (new Item.Properties().durability(250))));
    public static final DeferredHolder<Item, ? extends Item> COPPER_BOOTS = ITEMS.register("copper_boots", () -> new CopperArmorItem(ModArmorMaterials.COPPER_MATERIAL, ArmorItem.Type.BOOTS, (new Item.Properties().durability(210))));

    public static final DeferredHolder<Item, ? extends Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new Item((new Item.Properties().food(ModEffects.NUGGY))));
    public static final DeferredHolder<Item, ? extends Item> COPPER_SHEARS = ITEMS.register("copper_shears", () -> new CopperShearsItem((new Item.Properties()).durability(200)));
    public static final DeferredHolder<Item, ? extends Item> COPPER_HORSE_ARMOR = ITEMS.register("copper_horse_armor", () -> new CopperHorseArmorItem(ModArmorMaterials.COPPER_MATERIAL, true, (new Item.Properties()).stacksTo(1)));

    public static final DeferredHolder<Item, ? extends Item> COPPER_MINECART = ITEMS.register("copper_minecart", () -> new CopperMinecartItem(AbstractMinecart.Type.RIDEABLE, (new Item.Properties()).stacksTo(1))); // CreativeModeTab.TAB_TRANSPORTATION
    public static final DeferredHolder<Item, ? extends Item> COPPER_CHEST_MINECART = ITEMS.register("chest_copper_minecart", () -> new CopperMinecartItem(AbstractMinecart.Type.CHEST, (new Item.Properties()).stacksTo(1)));
    public static final DeferredHolder<Item, ? extends Item> COPPER_FURNACE_MINECART = ITEMS.register("furnace_copper_minecart", () -> new CopperMinecartItem(AbstractMinecart.Type.FURNACE, (new Item.Properties()).stacksTo(1)));
    public static final DeferredHolder<Item, ? extends Item> COPPER_TNT_MINECART = ITEMS.register("tnt_copper_minecart", () -> new CopperMinecartItem(AbstractMinecart.Type.TNT, (new Item.Properties()).stacksTo(1)));
    public static final DeferredHolder<Item, ? extends Item> COPPER_HOPPER_MINECART = ITEMS.register("hopper_copper_minecart", () -> new CopperMinecartItem(AbstractMinecart.Type.HOPPER, (new Item.Properties()).stacksTo(1)));
}
