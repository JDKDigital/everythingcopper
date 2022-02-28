package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.item.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EverythingCopper.MODID);

    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword", () -> new CopperSwordItem(Tiers.IRON, 3, -2.4F, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel", () -> new CopperShovelItem(Tiers.IRON, 1.5F, -3.0F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", () -> new CopperPickaxeItem(Tiers.IRON, 1, -2.8F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe", () -> new CopperAxeItem(Tiers.IRON, 6.0F, -3.1F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe", () -> new CopperHoeItem(Tiers.IRON, -2, -1.0F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));

    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet", () -> new CopperArmorItem(ArmorMaterials.IRON, EquipmentSlot.HEAD, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate", () -> new CopperArmorItem(ArmorMaterials.IRON, EquipmentSlot.CHEST, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings", () -> new CopperArmorItem(ArmorMaterials.IRON, EquipmentSlot.LEGS, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots", () -> new CopperArmorItem(ArmorMaterials.IRON, EquipmentSlot.FEET, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new Item((new Item.Properties().food(ModEffects.NUGGY)).tab(CreativeModeTab.TAB_MATERIALS)));
//    public static final RegistryObject<Item> COPPER_BUCKET = ITEMS.register("copper_bucket", () -> new BucketItem(() -> Fluids.EMPTY, (new Item.Properties()).stacksTo(16).tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> COPPER_SHEARS = ITEMS.register("copper_shears", () -> new CopperShearsItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> COPPER_HORSE_ARMOR = ITEMS.register("copper_horse_armor", () -> new CopperHorseArmorItem(5, "leather", (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> COPPER_MINECART = ITEMS.register("copper_minecart", () -> new CopperMinecartItem(AbstractMinecart.Type.RIDEABLE, (new Item.Properties()).stacksTo(1).tab(null))); // CreativeModeTab.TAB_TRANSPORTATION
    public static final RegistryObject<Item> COPPER_CHEST_MINECART = ITEMS.register("chest_copper_minecart", () -> new CopperMinecartItem(AbstractMinecart.Type.CHEST, (new Item.Properties()).stacksTo(1).tab(null)));
    public static final RegistryObject<Item> COPPER_FURNACE_MINECART = ITEMS.register("furnace_copper_minecart", () -> new CopperMinecartItem(AbstractMinecart.Type.FURNACE, (new Item.Properties()).stacksTo(1).tab(null)));
    public static final RegistryObject<Item> COPPER_TNT_MINECART = ITEMS.register("tnt_copper_minecart", () -> new CopperMinecartItem(AbstractMinecart.Type.TNT, (new Item.Properties()).stacksTo(1).tab(null)));
    public static final RegistryObject<Item> COPPER_HOPPER_MINECART = ITEMS.register("hopper_copper_minecart", () -> new CopperMinecartItem(AbstractMinecart.Type.HOPPER, (new Item.Properties()).stacksTo(1).tab(null)));
}
