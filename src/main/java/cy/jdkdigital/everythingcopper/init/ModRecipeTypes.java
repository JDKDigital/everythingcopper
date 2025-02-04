package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.crafting.recipe.ScrapeRecipe;
import cy.jdkdigital.everythingcopper.crafting.recipe.WaxOnRecipe;
import cy.jdkdigital.everythingcopper.crafting.recipe.WeatheringRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModRecipeTypes
{
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, EverythingCopper.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, EverythingCopper.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> WAX_ON = RECIPE_SERIALIZERS.register("wax_on", WaxOnRecipe.Serializer::new);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> SCRAPE = RECIPE_SERIALIZERS.register("scrape", ScrapeRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<WeatheringRecipe>> WEATHERING = RECIPE_TYPES.register("weathering", () -> new RecipeType<>() {});
}
