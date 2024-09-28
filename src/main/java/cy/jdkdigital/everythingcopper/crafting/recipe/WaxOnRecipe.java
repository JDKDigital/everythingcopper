package cy.jdkdigital.everythingcopper.crafting.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.item.ICopperItem;
import cy.jdkdigital.everythingcopper.init.ModRecipeTypes;
import cy.jdkdigital.everythingcopper.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class WaxOnRecipe implements CraftingRecipe
{
    public final Ingredient input;
    public final Ingredient wax;

    public WaxOnRecipe(Ingredient input, Ingredient wax) {
        this.input = input;
        this.wax = wax;
    }

    @Override
    public boolean matches(CraftingInput pInput, Level pLevel) {
        boolean hasWaxItem = false;
        boolean hasWaxableItem = false;
        for (int j = 0; j < pInput.size(); ++j) {
            ItemStack itemStack = pInput.getItem(j);
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
    public ItemStack assemble(CraftingInput pInput, HolderLookup.Provider pRegistries) {
        ItemStack outputItem = ItemStack.EMPTY;
        for (int j = 0; j < pInput.size(); ++j) {
            ItemStack itemStack = pInput.getItem(j);
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
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
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
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.WAX_ON.get();
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    public static class Serializer implements RecipeSerializer<WaxOnRecipe>
    {
        private static final MapCodec<WaxOnRecipe> CODEC = RecordCodecBuilder.mapCodec(
                builder -> builder.group(
                                Ingredient.CODEC.fieldOf("item").forGetter(recipe -> recipe.input),
                                Ingredient.CODEC.fieldOf("wax").forGetter(recipe -> recipe.input)
                        )
                        .apply(builder, WaxOnRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, WaxOnRecipe> STREAM_CODEC = StreamCodec.of(
                WaxOnRecipe.Serializer::toNetwork, WaxOnRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<WaxOnRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, WaxOnRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        public static WaxOnRecipe fromNetwork(@Nonnull RegistryFriendlyByteBuf buffer) {
            try {
                return new WaxOnRecipe(Ingredient.CONTENTS_STREAM_CODEC.decode(buffer), Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            } catch (Exception e) {
                EverythingCopper.LOGGER.error("Error reading waxing recipe from packet. ", e);
                throw e;
            }
        }

        public static void toNetwork(@Nonnull RegistryFriendlyByteBuf buffer, WaxOnRecipe recipe) {
            try {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.input);
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.wax);
            } catch (Exception e) {
                EverythingCopper.LOGGER.error("Error writing waxing recipe to packet. ", e);
                throw e;
            }
        }
    }
}