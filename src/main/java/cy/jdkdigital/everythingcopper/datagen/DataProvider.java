package cy.jdkdigital.everythingcopper.datagen;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.datagen.recipes.CopperRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = EverythingCopper.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataProvider
{
    private DataProvider() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        gen.addProvider(event.includeServer(), new CopperRecipeProvider(gen.getPackOutput(), event.getLookupProvider()));
    }
}
