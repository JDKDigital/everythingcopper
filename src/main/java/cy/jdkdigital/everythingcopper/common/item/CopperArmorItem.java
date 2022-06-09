package cy.jdkdigital.everythingcopper.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class CopperArmorItem extends ArmorItem implements ICopperItem
{
    public CopperArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        String id = (ICopperItem.isWaxed(stack) ? "waxed_" : "") + ICopperItem.getAge(stack).toLowerCase() + "_copper_";
        return Component.translatable(this.getDescriptionId(stack).replace("copper_", id));
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);
        weatheringTick(stack, level);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        String id = ICopperItem.getAge(stack).toLowerCase() + "_copper_";
        int layer = slot.equals(EquipmentSlot.LEGS) ? 2 : 1;
        return String.format("everythingcopper:textures/models/armor/%slayer_%d.png", id, layer);
    }
}
