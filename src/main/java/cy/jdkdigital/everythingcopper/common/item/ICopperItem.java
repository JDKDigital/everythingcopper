package cy.jdkdigital.everythingcopper.common.item;

import cy.jdkdigital.everythingcopper.init.ModDataComponents;
import cy.jdkdigital.everythingcopper.init.ModTags;
import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.SimpleTier;

public interface ICopperItem
{
    Tier COPPER_TIER = new SimpleTier(ModTags.INCORRECT_FOR_COPPER_TOOL, 180, 7.0f, 1.8f, 18, () -> Ingredient.of(Items.COPPER_INGOT));

    default void weatheringTick(ItemStack stack, Level level) {
        if (canAge(stack) && level.random.nextFloat() < 0.00018962963F) {
            setAge(stack, WeatheringUtils.nextState(getAge(stack)));
        }
    }

    static boolean isWaxed(ItemStack stack) {
        if (stack.has(ModDataComponents.WAXED)) {
            return Boolean.TRUE.equals(stack.get(ModDataComponents.WAXED));
        }
        return false;
    }

    static void setWaxed(ItemStack stack, boolean waxed) {
        stack.set(ModDataComponents.WAXED, waxed);
    }

    static boolean canAge(ItemStack stack) {
        return !isWaxed(stack) && !getAge(stack).equals("oxidized");
    }

    static String getAge(ItemStack stack) {
        if (stack.has(ModDataComponents.OXIDATION_STATE)) {
            return stack.get(ModDataComponents.OXIDATION_STATE);
        }
        return "unaffected";
    }

    static void setAge(ItemStack stack, String age) {
        stack.set(ModDataComponents.OXIDATION_STATE, age);
    }

    static Float getStateAsFloat(ItemStack stack) {
        if (stack.has(ModDataComponents.OXIDATION_STATE)) {
            var state = stack.get(ModDataComponents.OXIDATION_STATE);
            return switch (state) {
                case "exposed" -> 1.0F;
                case "weathered" -> 2.0F;
                case "oxidized" -> 3.0F;
                default -> 0.0F;
            };
        }
        return 0.0F;
    }
}
