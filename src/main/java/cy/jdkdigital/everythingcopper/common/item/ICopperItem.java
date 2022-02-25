package cy.jdkdigital.everythingcopper.common.item;

import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface ICopperItem
{
    default void weatheringTick(ItemStack stack, Level level) {
        if (canAge(stack) && level.random.nextFloat() < 0.00018962963F) {
            setAge(stack, WeatheringUtils.nextState(getAge(stack)));
        }
    }

    static boolean isWaxed(ItemStack stack) {
        if (stack.hasTag()) {
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains("waxed")) {
                return tag.getBoolean("waxed");
            }
        }
        return false;
    }

    static void setWaxed(ItemStack stack, boolean waxed) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean("waxed", waxed);
        stack.setTag(tag);
    }

    static boolean canAge(ItemStack stack) {
        return !isWaxed(stack) && !getAge(stack).equals("oxidized");
    }

    static String getAge(ItemStack stack) {
        if (stack.hasTag()) {
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains("state") && !tag.getString("state").isEmpty()) {
                return tag.getString("state");
            }
        }
        return "unaffected";
    }

    static void setAge(ItemStack stack, String age) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString("state", age);
        stack.setTag(tag);
    }

    static Float getStateAsFloat(ItemStack stack) {
        if (stack.hasTag()) {
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains("state") && !tag.getString("state").isEmpty()) {
                return switch (tag.getString("state")) {
                    case "exposed" -> 1.0F;
                    case "weathered" -> 2.0F;
                    case "oxidized" -> 3.0F;
                    default -> 0.0F;
                };
            }
        }
        return 0.0F;
    }
}
