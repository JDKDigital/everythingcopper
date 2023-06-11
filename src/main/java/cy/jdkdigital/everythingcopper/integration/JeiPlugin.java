package cy.jdkdigital.everythingcopper.integration;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.item.ICopperItem;
import cy.jdkdigital.everythingcopper.crafting.recipe.WeatheringRecipe;
import cy.jdkdigital.everythingcopper.init.ModBlocks;
import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.ArrayList;

@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin
{
    private static final ResourceLocation pluginId = new ResourceLocation(EverythingCopper.MODID, EverythingCopper.MODID);

    public static RecipeType<WeatheringRecipe> WEATHERING_RECIPE_TYPE = RecipeType.create(EverythingCopper.MODID, "weathering", WeatheringRecipe.class);

    public JeiPlugin() {

    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.WEATHERING_STATION.get()), WEATHERING_RECIPE_TYPE);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        registration.addRecipeCategories(new WeatheringRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        // Find all weathering blocks
        var weatheringRecipes = new ArrayList<WeatheringRecipe>();
        ForgeRegistries.BLOCKS.getValues().forEach(block -> {
            if (block instanceof WeatheringCopper weatheringBlock) {
                weatheringBlock.getNext(block.defaultBlockState()).ifPresent(newState -> {
                    var key = ForgeRegistries.BLOCKS.getKey(block);
                    weatheringRecipes.add(new WeatheringRecipe(new ResourceLocation(EverythingCopper.MODID, "weathering_" + key.getPath()), new ItemStack(block), new ItemStack(newState.getBlock())));
                });
            }
        });
        ForgeRegistries.ITEMS.getValues().forEach(item -> {
            if (item instanceof ICopperItem) {
                var key = ForgeRegistries.ITEMS.getKey(item);
                ItemStack sourceItem = new ItemStack(item);
                ItemStack newItem = new ItemStack(item);
                while (ICopperItem.canAge(newItem)) {
                    ICopperItem.setAge(newItem, WeatheringUtils.nextState(ICopperItem.getAge(newItem)));
                    weatheringRecipes.add(new WeatheringRecipe(new ResourceLocation(EverythingCopper.MODID, "weathering_" + key.getPath()), sourceItem, newItem.copy()));
                    sourceItem = newItem.copy();
                }
            }
        });
        registration.addRecipes(WEATHERING_RECIPE_TYPE, weatheringRecipes);
    }

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return pluginId;
    }
}
