package cy.jdkdigital.everythingcopper.crafting.recipe;

import com.google.gson.JsonObject;
import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.item.ICopperItem;
import cy.jdkdigital.everythingcopper.init.ModRecipeTypes;
import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;

public class ScrapeRecipe implements CraftingRecipe
{
    public final ResourceLocation id;
    public final Ingredient input;

    public ScrapeRecipe(ResourceLocation id, Ingredient input) {
        this.id = id;
        this.input = input;
    }

    @Override
    public boolean matches(CraftingContainer inv, Level worldIn) {
        boolean hasValidItem = false;
        for (int j = 0; j < inv.getContainerSize(); ++j) {
            ItemStack itemStack = inv.getItem(j);
            if (!itemStack.isEmpty() && input.test(itemStack) && (ICopperItem.isWaxed(itemStack) || !ICopperItem.getAge(itemStack).equals("unaffected"))) {
                hasValidItem = true;
            } else if (!itemStack.isEmpty()) {
                return false;
            }
        }

        return hasValidItem;
    }

    @Nonnull
    @Override
    public ItemStack assemble(CraftingContainer inv) {
        ItemStack outputItem = ItemStack.EMPTY;
        for (int j = 0; j < inv.getContainerSize(); ++j) {
            ItemStack itemStack = inv.getItem(j);
            if (!itemStack.isEmpty() && input.test(itemStack)) {
                outputItem = itemStack.copy();
            }
        }

        if (ICopperItem.isWaxed(outputItem)) {
            ICopperItem.setWaxed(outputItem, false);
        } else {
            ICopperItem.setAge(outputItem, WeatheringUtils.prevState(ICopperItem.getAge(outputItem)));
        }

        return outputItem;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack getResultItem() {
        return this.input.getItems().length > 0 ? this.input.getItems()[0] : ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();

        list.add(input);

        return list;
    }

    @Nonnull
    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Nonnull
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.SCRAPE.get();
    }

    public static class Serializer<T extends ScrapeRecipe> extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<T>
    {
        final ScrapeRecipe.Serializer.IRecipeFactory<T> factory;

        public Serializer(ScrapeRecipe.Serializer.IRecipeFactory<T> factory) {
            this.factory = factory;
        }

        @Override
        public T fromJson(ResourceLocation id, JsonObject json) {
            Ingredient input;
            if (GsonHelper.isArrayNode(json, "item")) {
                input = Ingredient.fromJson(GsonHelper.getAsJsonArray(json, "item"));
            } else {
                input = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "item"));
            }
            return this.factory.create(id, input);
        }

        public T fromNetwork(@Nonnull ResourceLocation id, @Nonnull FriendlyByteBuf buffer) {
            try {
                return this.factory.create(id, Ingredient.fromNetwork(buffer));
            } catch (Exception e) {
                EverythingCopper.LOGGER.error("Error reading scraping recipe from packet. " + id, e);
                throw e;
            }
        }

        public void toNetwork(@Nonnull FriendlyByteBuf buffer, T recipe) {
            try {
                recipe.input.toNetwork(buffer);
            } catch (Exception e) {
                EverythingCopper.LOGGER.error("Error writing scraping recipe to packet. " + recipe.getId(), e);
                throw e;
            }
        }

        public interface IRecipeFactory<T extends ScrapeRecipe>
        {
            T create(ResourceLocation id, Ingredient input);
        }
    }
}