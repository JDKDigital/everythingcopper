package cy.jdkdigital.everythingcopper.common.item;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class CopperArmorItem extends ArmorItem implements ICopperItem
{
    public CopperArmorItem(Holder<ArmorMaterial> pMaterial, ArmorItem.Type type, Properties properties) {
        super(pMaterial, type, properties);
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        String id = (ICopperItem.isWaxed(stack) ? "waxed_" : "") + ICopperItem.getAge(stack).toLowerCase() + "_copper_";
        return Component.translatable(this.getDescriptionId(stack).replace("copper_", id));
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        if (pSlotId >= 36 && pSlotId <= 39) {
            weatheringTick(pStack, pLevel);
        }
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, net.minecraft.world.entity.EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        String id = ICopperItem.getAge(stack).toLowerCase() + "_copper_";
        int layerNum = slot.equals(EquipmentSlot.LEGS) ? 2 : 1;
        return ResourceLocation.parse(String.format("everythingcopper:textures/models/armor/%slayer_%d.png", id, layerNum));
    }
}
