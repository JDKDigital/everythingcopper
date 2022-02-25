package cy.jdkdigital.everythingcopper.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CopperPickaxeItem extends PickaxeItem implements ICopperItem
{
    public CopperPickaxeItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        String id = (ICopperItem.isWaxed(stack) ? "waxed_" : "") + ICopperItem.getAge(stack).toLowerCase() + "_copper_pickaxe";
        return new TranslatableComponent(this.getDescriptionId(stack).replace("copper_pickaxe", id));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slot, isSelected);
        if (isSelected || slot == 0) {
            weatheringTick(stack, level);
        }
    }
}
