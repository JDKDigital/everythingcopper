package cy.jdkdigital.everythingcopper.event;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.client.render.blockentity.CopperChimeRenderer;
import cy.jdkdigital.everythingcopper.client.render.blockentity.WeatheringStationRenderer;
import cy.jdkdigital.everythingcopper.client.render.entity.CopperGolemRenderer;
import cy.jdkdigital.everythingcopper.client.render.entity.CopperMinecartRenderer;
import cy.jdkdigital.everythingcopper.client.render.entity.TntCopperMinecartRenderer;
import cy.jdkdigital.everythingcopper.init.ModBlockEntities;
import cy.jdkdigital.everythingcopper.init.ModBlocks;
import cy.jdkdigital.everythingcopper.init.ModEntities;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.block.Blocks;
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
        event.getBlockColors().register((state, tintGetter, blockPos, i) -> tintGetter != null && blockPos != null ? BiomeColors.getAverageWaterColor(tintGetter, blockPos) : -1, ModBlocks.WEATHERING_STATION.get());

//        event.getBlockColors().register((blockState, tintGetter, pos, i) -> {
//            return i == 1 ? RedStoneWireBlock.getColorForPower(blockState.getValue(RedStoneWireBlock.POWER)) : -1;
//        }, ModBlocks.CONDUCTIVE_ROD.get());
//        event.getBlockColors().addColoringState(RedStoneWireBlock.POWER, ModBlocks.CONDUCTIVE_ROD.get());
    }

    @SubscribeEvent
    public static void layerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CopperChimeRenderer.COPPER_CHIME_MAIN_LAYER, CopperChimeRenderer::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.WEATHERING_STATION.get(), WeatheringStationRenderer::new);
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
