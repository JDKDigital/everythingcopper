package cy.jdkdigital.everythingcopper.crafting.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.item.ICopperItem;
import cy.jdkdigital.everythingcopper.init.ModRecipeTypes;
import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class ScrapeRecipe implements CraftingRecipe
{
    public final Ingredient input;

    public ScrapeRecipe(Ingredient input) {
        this.input = input;
    }

    @Override
    public boolean matches(CraftingInput pInput, Level pLevel) {
        boolean hasValidItem = false;
        for (int j = 0; j < pInput.size(); ++j) {
            ItemStack itemStack = pInput.getItem(j);
            if (!itemStack.isEmpty() && input.test(itemStack) && (ICopperItem.isWaxed(itemStack) || !ICopperItem.getAge(itemStack).equals("unaffected"))) {
                hasValidItem = true;
            } else if (!itemStack.isEmpty()) {
                return false;
            }
        }

        return hasValidItem;
    }

    @Override
    public ItemStack assemble(CraftingInput pInput, HolderLookup.Provider pRegistries) {
        ItemStack outputItem = ItemStack.EMPTY;
        for (int j = 0; j < pInput.size(); ++j) {
            ItemStack itemStack = pInput.getItem(j);
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

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
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
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.SCRAPE.get();
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    public static class Serializer implements RecipeSerializer<ScrapeRecipe>
    {
        private static final MapCodec<ScrapeRecipe> CODEC = RecordCodecBuilder.mapCodec(
                builder -> builder.group(
                                Ingredient.CODEC.fieldOf("item").forGetter(recipe -> recipe.input)
                        )
                        .apply(builder, ScrapeRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, ScrapeRecipe> STREAM_CODEC = StreamCodec.of(
                ScrapeRecipe.Serializer::toNetwork, ScrapeRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<ScrapeRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ScrapeRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        public static ScrapeRecipe fromNetwork(@Nonnull RegistryFriendlyByteBuf buffer) {
            try {
                return new ScrapeRecipe(Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            } catch (Exception e) {
                EverythingCopper.LOGGER.error("Error reading scraping recipe from packet. ", e);
                throw e;
            }
        }

        public static void toNetwork(@Nonnull RegistryFriendlyByteBuf buffer, ScrapeRecipe recipe) {
            try {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.input);
            } catch (Exception e) {
                EverythingCopper.LOGGER.error("Error writing scraping recipe to packet.", e);
                throw e;
            }
        }
    }
}