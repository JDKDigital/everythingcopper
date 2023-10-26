package cy.jdkdigital.everythingcopper.event;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.client.render.blockentity.WeatheringStationRenderer;
import cy.jdkdigital.everythingcopper.client.render.entity.CopperGolemRenderer;
import cy.jdkdigital.everythingcopper.client.render.entity.CopperMinecartRenderer;
import cy.jdkdigital.everythingcopper.client.render.entity.TntCopperMinecartRenderer;
import cy.jdkdigital.everythingcopper.common.item.ICopperItem;
import cy.jdkdigital.everythingcopper.init.ModBlockEntities;
import cy.jdkdigital.everythingcopper.init.ModBlocks;
import cy.jdkdigital.everythingcopper.init.ModEntities;
import cy.jdkdigital.everythingcopper.init.ModItems;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EverythingCopper.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup
{
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        ItemProperties.register(ModItems.COPPER_SWORD.get(), new ResourceLocation("state"), (stack, world, entity, i) -> ICopperItem.getStateAsFloat(stack));
        ItemProperties.register(ModItems.COPPER_SHOVEL.get(), new ResourceLocation("state"), (stack, world, entity, i) -> ICopperItem.getStateAsFloat(stack));
        ItemProperties.register(ModItems.COPPER_PICKAXE.get(), new ResourceLocation("state"), (stack, world, entity, i) -> ICopperItem.getStateAsFloat(stack));
        ItemProperties.register(ModItems.COPPER_AXE.get(), new ResourceLocation("state"), (stack, world, entity, i) -> ICopperItem.getStateAsFloat(stack));
        ItemProperties.register(ModItems.COPPER_HOE.get(), new ResourceLocation("state"), (stack, world, entity, i) -> ICopperItem.getStateAsFloat(stack));
        ItemProperties.register(ModItems.COPPER_SHEARS.get(), new ResourceLocation("state"), (stack, world, entity, i) -> ICopperItem.getStateAsFloat(stack));
        ItemProperties.register(ModItems.COPPER_HELMET.get(), new ResourceLocation("state"), (stack, world, entity, i) -> ICopperItem.getStateAsFloat(stack));
        ItemProperties.register(ModItems.COPPER_CHESTPLATE.get(), new ResourceLocation("state"), (stack, world, entity, i) -> ICopperItem.getStateAsFloat(stack));
        ItemProperties.register(ModItems.COPPER_LEGGINGS.get(), new ResourceLocation("state"), (stack, world, entity, i) -> ICopperItem.getStateAsFloat(stack));
        ItemProperties.register(ModItems.COPPER_BOOTS.get(), new ResourceLocation("state"), (stack, world, entity, i) -> ICopperItem.getStateAsFloat(stack));
        ItemProperties.register(ModItems.COPPER_HORSE_ARMOR.get(), new ResourceLocation("state"), (stack, world, entity, i) -> ICopperItem.getStateAsFloat(stack));
    }

    @SubscribeEvent
    public static void registerBlockColors(final RegisterColorHandlersEvent.Block event) {
        event.register((state, tintGetter, blockPos, i) -> tintGetter != null && blockPos != null ? BiomeColors.getAverageWaterColor(tintGetter, blockPos) : -1, ModBlocks.WEATHERING_STATION.get());
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
}
