package cy.jdkdigital.everythingcopper.common.item;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CopperHorseArmorItem extends AnimalArmorItem implements ICopperItem
{
//    private static ResourceLocation EXPOSED = new ResourceLocation("textures/entity/horse/armor/exposed_horse_armor_copper.png");
//    private static ResourceLocation WEATHERED = new ResourceLocation("textures/entity/horse/armor/weathered_horse_armor_copper.png");
//    private static ResourceLocation OXIDIZED = new ResourceLocation("textures/entity/horse/armor/oxidized_horse_armor_copper.png");

    public CopperHorseArmorItem(Holder<ArmorMaterial> pArmorMaterial, boolean pHasOverlay, Item.Properties pProperties) {
        super(pArmorMaterial, AnimalArmorItem.BodyType.EQUESTRIAN, pHasOverlay, pProperties);
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        String id = (ICopperItem.isWaxed(stack) ? "waxed_" : "") + ICopperItem.getAge(stack).toLowerCase() + "_copper_horse_armor";
        return Component.translatable(this.getDescriptionId(stack).replace("copper_horse_armor", id));
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        weatheringTick(pStack, pLevel);
    }

//    @Override
//    public int getColor(ItemStack stack) {
//        return switch (ICopperItem.getAge(stack).toLowerCase()) {
//            case "unaffected" ->  12741452;
//            case "exposed" -> 9997417;
//            case "weathered" -> 6594679;
//            case "oxidized" -> 5878418;
//            default -> super.getColor(stack);
//        };
//    }

//    @Override
//    public ResourceLocation getTexture() {
//        return switch (ICopperItem.getAge(stack).toLowerCase()) {
//            case "exposed" -> EXPOSED;
//            case "weathered" -> WEATHERED;
//            case "oxidized" -> OXIDIZED;
//            default -> super.getTexture();
//        };
//    }
}
