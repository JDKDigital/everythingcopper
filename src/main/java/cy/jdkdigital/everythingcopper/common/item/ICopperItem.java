package cy.jdkdigital.everythingcopper.common.item;

import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

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
        public int getDurabilityForSlot(EquipmentSlot slot) {
            int[] HEALTH_PER_SLOT = new int[]{12, 14, 15, 10};
            return HEALTH_PER_SLOT[slot.getIndex()] * 12;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot slot) {
            int[] def = new int[]{2, 4, 5, 2};
            return def[slot.getIndex()];
        }

        @Override
        public int getEnchantmentValue() {
            return 12;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_IRON;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.COPPER_INGOT);
        }

        @Override
        public String getName() {
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
