package cy.jdkdigital.everythingcopper.integration;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.crafting.recipe.WeatheringRecipe;
import cy.jdkdigital.everythingcopper.init.ModBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

public class WeatheringRecipeCategory implements IRecipeCategory<WeatheringRecipe>
{
    private final IDrawable background;
    private final IDrawable icon;

    public WeatheringRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(EverythingCopper.MODID, "textures/gui/jei/weathering_recipe.png");
        this.background = guiHelper.createDrawable(location, 0, 0, 126, 70);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.WEATHERING_STATION.get()));
    }

    @Override
    public Component getTitle() {
        return Component.translatable(EverythingCopper.MODID + ".recipe.weathering");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public RecipeType<WeatheringRecipe> getRecipeType() {
        return JeiPlugin.WEATHERING_RECIPE_TYPE;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, WeatheringRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 33, 27)
                .addItemStack(recipe.input())
                .setSlotName("input");
        builder.addSlot(RecipeIngredientRole.OUTPUT, 75, 27)
                .addItemStack(recipe.output())
                .setSlotName("output");
        builder.addSlot(RecipeIngredientRole.INPUT, 0, 27)
                .addIngredient(ForgeTypes.FLUID_STACK, new FluidStack(Fluids.WATER, 100))
                .setFluidRenderer(100, false, 16, 16)
                .setSlotName("water");
    }
}
