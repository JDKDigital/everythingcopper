package cy.jdkdigital.everythingcopper.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeableHorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CopperHorseArmorItem extends DyeableHorseArmorItem implements ICopperItem
{
//    private static ResourceLocation EXPOSED = new ResourceLocation("textures/entity/horse/armor/exposed_horse_armor_copper.png");
//    private static ResourceLocation WEATHERED = new ResourceLocation("textures/entity/horse/armor/weathered_horse_armor_copper.png");
//    private static ResourceLocation OXIDIZED = new ResourceLocation("textures/entity/horse/armor/oxidized_horse_armor_copper.png");

    public CopperHorseArmorItem(int protection, String textureName, Properties properties) {
        super(protection, textureName, properties);
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        String id = (ICopperItem.isWaxed(stack) ? "waxed_" : "") + ICopperItem.getAge(stack).toLowerCase() + "_copper_horse_armor";
        return Component.translatable(this.getDescriptionId(stack).replace("copper_horse_armor", id));
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);
        weatheringTick(stack, level);
    }

    @Override
    public int getColor(ItemStack stack) {
        return switch (ICopperItem.getAge(stack).toLowerCase()) {
            case "unaffected" ->  12741452;
            case "exposed" -> 9997417;
            case "weathered" -> 6594679;
            case "oxidized" -> 5878418;
            default -> super.getColor(stack);
        };
    }

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
