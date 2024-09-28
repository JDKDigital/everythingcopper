package cy.jdkdigital.everythingcopper.crafting.recipe;

import cy.jdkdigital.everythingcopper.init.ModRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record WeatheringRecipe(ResourceLocation id, ItemStack input, ItemStack output) implements Recipe<RecipeInput>
{
    @Override
    public boolean matches(RecipeInput pInput, Level pLevel) {
        return false;
    }

    @Override
    public ItemStack assemble(RecipeInput pInput, HolderLookup.Provider pRegistries) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
        return output();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.WEATHERING.get();
    }
}
