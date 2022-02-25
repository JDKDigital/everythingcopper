package cy.jdkdigital.everythingcopper.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CopperShearsItem extends ShearsItem implements ICopperItem
{
    public CopperShearsItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        String id = (ICopperItem.isWaxed(stack) ? "waxed_" : "") + ICopperItem.getAge(stack).toLowerCase() + "_copper_shears";
        return new TranslatableComponent(this.getDescriptionId(stack).replace("copper_shears", id));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slot, isSelected);
        if (isSelected || slot == 0) {
            weatheringTick(stack, level);
        }
    }
}
