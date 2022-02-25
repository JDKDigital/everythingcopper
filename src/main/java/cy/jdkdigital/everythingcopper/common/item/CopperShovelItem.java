package cy.jdkdigital.everythingcopper.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CopperShovelItem extends ShovelItem implements ICopperItem
{
    public CopperShovelItem(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        String id = (ICopperItem.isWaxed(stack) ? "waxed_" : "") + ICopperItem.getAge(stack).toLowerCase() + "_copper_shovel";
        return new TranslatableComponent(this.getDescriptionId(stack).replace("copper_shovel", id));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slot, isSelected);
        if (isSelected || slot == 0) {
            weatheringTick(stack, level);
        }
    }
}
