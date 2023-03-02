package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.crafting.recipe.ScrapeRecipe;
import cy.jdkdigital.everythingcopper.crafting.recipe.WaxOnRecipe;
import cy.jdkdigital.everythingcopper.crafting.recipe.WeatheringRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = EverythingCopper.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ModRecipeTypes
{
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, EverythingCopper.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, EverythingCopper.MODID);

    public static final RegistryObject<RecipeSerializer<?>> WAX_ON = RECIPE_SERIALIZERS.register("wax_on", () -> new WaxOnRecipe.Serializer<>(WaxOnRecipe::new));
    public static final RegistryObject<RecipeSerializer<?>> SCRAPE = RECIPE_SERIALIZERS.register("scrape", () -> new ScrapeRecipe.Serializer<>(ScrapeRecipe::new));

    public static RegistryObject<RecipeType<WeatheringRecipe>> WEATHERING = RECIPE_TYPES.register("weathering", () -> new RecipeType<>() {
        public String toString() {
            return EverythingCopper.MODID + ":weathering";
        }
    });
}
