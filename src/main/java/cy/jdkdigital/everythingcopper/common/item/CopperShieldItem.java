package cy.jdkdigital.everythingcopper.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;


public class CopperShieldItem extends ShieldItem implements ICopperItem
{
    public CopperShieldItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        String id = (ICopperItem.isWaxed(stack) ? "waxed_" : "") + ICopperItem.getAge(stack).toLowerCase() + "_copper_shield";
        return Component.translatable(this.getDescriptionId(stack).replace("copper_shield", id));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slot, isSelected);
        if (isSelected || slot == 0) {
            weatheringTick(stack, level);
        }
    }
//
//    @Override
//    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
//        consumer.accept(new IItemRenderProperties()
//        {
//            @Override
//            public BlockEntityWithoutLevelRenderer getItemStackRenderer()
//            {
//                return CopperShieldRenderer.INSTANCE;
//            }
//        });
//    }
}
