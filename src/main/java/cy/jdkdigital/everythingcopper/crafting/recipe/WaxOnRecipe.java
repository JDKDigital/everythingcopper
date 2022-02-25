package cy.jdkdigital.everythingcopper.crafting.recipe;

import com.google.gson.JsonObject;
import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.item.ICopperItem;
import cy.jdkdigital.everythingcopper.init.ModRecipeTypes;
import cy.jdkdigital.everythingcopper.init.ModTags;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;

public class WaxOnRecipe implements CraftingRecipe
{
    public final ResourceLocation id;
    public final Ingredient input;
    public final Ingredient wax;

    public WaxOnRecipe(ResourceLocation id, Ingredient input, Ingredient wax) {
        this.id = id;
        this.input = input;
        this.wax = wax;
    }

    @Override
    public boolean matches(CraftingContainer inv, Level worldIn) {
        boolean hasWaxItem = false;
        boolean hasWaxableItem = false;
        for (int j = 0; j < inv.getContainerSize(); ++j) {
            ItemStack itemStack = inv.getItem(j);
            if (!itemStack.isEmpty()) {
                if (itemStack.is(ModTags.WAXING_ITEMS)) {
                    hasWaxItem = true;
                } else if (input.test(itemStack)) {
                    hasWaxableItem = true;
                } else if (!itemStack.equals(ItemStack.EMPTY)) {
                    return false;
                }
            }
        }
        return hasWaxItem && hasWaxableItem;
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

        ICopperItem.setWaxed(outputItem, true);

        return outputItem;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
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
        list.add(Ingredient.of(Items.HONEYCOMB));

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
        return ModRecipeTypes.WAX_ON.get();
    }

    public static class Serializer<T extends WaxOnRecipe> extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<T>
    {
        final WaxOnRecipe.Serializer.IRecipeFactory<T> factory;

        public Serializer(WaxOnRecipe.Serializer.IRecipeFactory<T> factory) {
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
            Ingredient wax;
            if (GsonHelper.isArrayNode(json, "wax")) {
                wax = Ingredient.fromJson(GsonHelper.getAsJsonArray(json, "wax"));
            } else {
                wax = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "wax"));
            }
            return this.factory.create(id, input, wax);
        }

        public T fromNetwork(@Nonnull ResourceLocation id, @Nonnull FriendlyByteBuf buffer) {
            try {
                return this.factory.create(id, Ingredient.fromNetwork(buffer), Ingredient.fromNetwork(buffer));
            } catch (Exception e) {
                EverythingCopper.LOGGER.error("Error reading waxing recipe from packet. " + id, e);
                throw e;
            }
        }

        public void toNetwork(@Nonnull FriendlyByteBuf buffer, T recipe) {
            try {
                recipe.input.toNetwork(buffer);
                recipe.wax.toNetwork(buffer);
            } catch (Exception e) {
                EverythingCopper.LOGGER.error("Error writing waxing recipe to packet. " + recipe.getId(), e);
                throw e;
            }
        }

        public interface IRecipeFactory<T extends WaxOnRecipe>
        {
            T create(ResourceLocation id, Ingredient input, Ingredient wax);
        }
    }
}