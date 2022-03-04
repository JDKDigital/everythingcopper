package cy.jdkdigital.everythingcopper.event.loot;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CopperItemLootModifier extends LootModifier
{
    private final Item addition;

    protected CopperItemLootModifier(LootItemCondition[] conditionsIn, Item addition) {
        super(conditionsIn);
        this.addition = addition;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        ItemStack loot = new ItemStack(addition, 1);

        // Randomized tarnish level
        CompoundTag tag = loot.getOrCreateTag();
        switch (context.getRandom().nextInt(3)) {
            case 0 -> tag.putString("state", "exposed");
            case 1 -> tag.putString("state", "weathered");
            default -> tag.putString("state", "oxidized");
        }

        generatedLoot.add(loot);
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<CopperItemLootModifier>
    {
        @Override
        public CopperItemLootModifier read(ResourceLocation resourceLocation, JsonObject jsonObject, LootItemCondition[] lootItemConditions) {
            Item addition = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(jsonObject, "addition"))));
            return new CopperItemLootModifier(lootItemConditions, addition);
        }

        @Override
        public JsonObject write(CopperItemLootModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            return json;
        }
    }
}
