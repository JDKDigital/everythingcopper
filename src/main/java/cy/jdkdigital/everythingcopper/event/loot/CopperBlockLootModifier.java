package cy.jdkdigital.everythingcopper.event.loot;

import com.google.gson.JsonObject;
import cy.jdkdigital.everythingcopper.common.block.IWeatheringBlock;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CopperBlockLootModifier extends LootModifier
{
    private final Item addition;
    private final int count;

    protected CopperBlockLootModifier(LootItemCondition[] conditionsIn, Item addition, Integer count) {
        super(conditionsIn);
        this.addition = addition;
        this.count = count;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        // Randomized tarnish level
        if (context.getRandom().nextBoolean() && addition instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            for (int i = 0; i < context.getRandom().nextInt(5); i++) {
                block = IWeatheringBlock.getNext(block).orElse(block);
            }
            ItemStack loot = new ItemStack(block.asItem(), count);
            generatedLoot.add(loot);
        }

        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<CopperBlockLootModifier>
    {
        @Override
        public CopperBlockLootModifier read(ResourceLocation resourceLocation, JsonObject jsonObject, LootItemCondition[] lootItemConditions) {
            Item addition = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(jsonObject, "addition"))));
            return new CopperBlockLootModifier(lootItemConditions, addition, GsonHelper.getAsInt(jsonObject, "count", 1));
        }

        @Override
        public JsonObject write(CopperBlockLootModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            json.addProperty("count", instance.count);
            return json;
        }
    }
}
