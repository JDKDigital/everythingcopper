package cy.jdkdigital.everythingcopper.datagen;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.datagen.recipes.CopperRecipeProvider;
import cy.jdkdigital.productivebees.ProductiveBees;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = EverythingCopper.MODID, bus = Bus.MOD)
public class DataProvider
{
    private DataProvider() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        ProductiveBees.LOGGER.info("Generating data");
        DataGenerator gen = event.getGenerator();
        gen.addProvider(event.includeServer(), new CopperRecipeProvider(gen));
    }
}
