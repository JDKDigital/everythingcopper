package cy.jdkdigital.everythingcopper.event;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.client.render.entity.CopperGolemRenderer;
import cy.jdkdigital.everythingcopper.client.render.entity.CopperMinecartRenderer;
import cy.jdkdigital.everythingcopper.client.render.entity.TntCopperMinecartRenderer;
import cy.jdkdigital.everythingcopper.init.ModEntities;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EverythingCopper.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup
{
    @SubscribeEvent
    public static void registerItemColors(final ColorHandlerEvent.Item event) {
    }

    @SubscribeEvent
    public static void registerBlockColors(final ColorHandlerEvent.Block event) {
    }

    @SubscribeEvent
    public static void layerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
    }

    @SubscribeEvent
    public static void registerEntityRendering(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.COPPER_GOLEM.get(), CopperGolemRenderer::new);
        event.registerEntityRenderer(ModEntities.COPPER_MINECART.get(), (context) -> new CopperMinecartRenderer(context, ModelLayers.MINECART));
        event.registerEntityRenderer(ModEntities.CHEST_COPPER_MINECART.get(), (context) -> new CopperMinecartRenderer(context, ModelLayers.CHEST_MINECART));
        event.registerEntityRenderer(ModEntities.COMMAND_BLOCK_COPPER_MINECART.get(), (context) -> new CopperMinecartRenderer(context, ModelLayers.COMMAND_BLOCK_MINECART));
        event.registerEntityRenderer(ModEntities.FURNACE_COPPER_MINECART.get(), (context) -> new CopperMinecartRenderer(context, ModelLayers.FURNACE_MINECART));
        event.registerEntityRenderer(ModEntities.HOPPER_COPPER_MINECART.get(), (context) -> new CopperMinecartRenderer(context, ModelLayers.HOPPER_MINECART));
        event.registerEntityRenderer(ModEntities.SPAWNER_COPPER_MINECART.get(), (context) -> new CopperMinecartRenderer(context, ModelLayers.SPAWNER_MINECART));
        event.registerEntityRenderer(ModEntities.TNT_COPPER_MINECART.get(), TntCopperMinecartRenderer::new);
    }

    @SubscribeEvent
    public static void registerModelLoaders(ModelRegistryEvent event) {
    }
}
