package cy.jdkdigital.everythingcopper.common.item;

import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public interface ICopperItem
{
    Tier COPPER_TIER = new Tier() {
        @Override
        public int getUses() {
            return 180;
        }

        @Override
        public float getSpeed() {
            return 7.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 1.8F;
        }

        @Override
        public int getLevel() {
            return 2;
        }

        @Override
        public int getEnchantmentValue() {
            return 18;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.COPPER_INGOT);
        }
    };

    ArmorMaterial COPPER_MATERIAL = new ArmorMaterial() {
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            Map<ArmorItem.Type, Integer> HEALTH = new HashMap<>() {{
                put(ArmorItem.Type.BOOTS, 12);
                put(ArmorItem.Type.LEGGINGS, 14);
                put(ArmorItem.Type.CHESTPLATE, 15);
                put(ArmorItem.Type.HELMET, 10);
            }};
            return HEALTH.get(type) * 12;
        }

        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            Map<ArmorItem.Type, Integer> DEFENSE = new HashMap<>() {{
                put(ArmorItem.Type.BOOTS, 2);
                put(ArmorItem.Type.LEGGINGS, 4);
                put(ArmorItem.Type.CHESTPLATE, 5);
                put(ArmorItem.Type.HELMET, 2);
            }};
            return DEFENSE.get(type);
        }

        @Override
        public int getEnchantmentValue() {
            return 12;
        }

        @Override
        public @NotNull SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_IRON;
        }

        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(Items.COPPER_INGOT);
        }

        @Override
        public @NotNull String getName() {
            return "copper";
        }

        @Override
        public float getToughness() {
            return 0.0F;
        }

        @Override
        public float getKnockbackResistance() {
            return 0.1F;
        }
    };

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
