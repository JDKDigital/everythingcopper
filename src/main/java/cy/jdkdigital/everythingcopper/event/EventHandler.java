package cy.jdkdigital.everythingcopper.event;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.block.IWeatheringBlock;
import cy.jdkdigital.everythingcopper.common.entity.CopperGolem;
import cy.jdkdigital.everythingcopper.event.loot.CopperBlockLootModifier;
import cy.jdkdigital.everythingcopper.event.loot.CopperItemLootModifier;
import cy.jdkdigital.everythingcopper.init.ModEntities;
import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EverythingCopper.MODID)
public class EventHandler
{
    @SubscribeEvent
    public static void onLootRegister(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().registerAll(
            new CopperItemLootModifier.Serializer().setRegistryName(new ResourceLocation(EverythingCopper.MODID, "copper_boots")),
            new CopperItemLootModifier.Serializer().setRegistryName(new ResourceLocation(EverythingCopper.MODID, "copper_chestplate")),
            new CopperItemLootModifier.Serializer().setRegistryName(new ResourceLocation(EverythingCopper.MODID, "copper_helmet")),
            new CopperItemLootModifier.Serializer().setRegistryName(new ResourceLocation(EverythingCopper.MODID, "copper_horse_armor")),
            new CopperItemLootModifier.Serializer().setRegistryName(new ResourceLocation(EverythingCopper.MODID, "copper_leggings")),
            new CopperItemLootModifier.Serializer().setRegistryName(new ResourceLocation(EverythingCopper.MODID, "copper_pickaxe")),
            new CopperItemLootModifier.Serializer().setRegistryName(new ResourceLocation(EverythingCopper.MODID, "copper_shovel")),
            new CopperItemLootModifier.Serializer().setRegistryName(new ResourceLocation(EverythingCopper.MODID, "copper_sword")),
            new CopperBlockLootModifier.Serializer().setRegistryName(new ResourceLocation(EverythingCopper.MODID, "copper_rail"))
        );
    }

    @SubscribeEvent
    public static void placeCarvedPumpkin(BlockEvent.EntityPlaceEvent event) {
        if (event.getPlacedBlock().getBlock().equals(Blocks.CARVED_PUMPKIN) || event.getPlacedBlock().getBlock().equals(Blocks.JACK_O_LANTERN)) {
            LevelAccessor level = event.getWorld();

            BlockPattern copperGolemPattern = CopperGolem.getOrCreateCopperGolemFull();

            BlockPattern.BlockPatternMatch blockPatternMatch = copperGolemPattern.find(event.getWorld(), event.getPos());
            if (blockPatternMatch != null) {
                for(int j = 0; j < copperGolemPattern.getWidth(); ++j) {
                    for(int k = 0; k < copperGolemPattern.getHeight(); ++k) {
                        BlockInWorld block = blockPatternMatch.getBlock(j, k, 0);
                        level.setBlock(block.getPos(), Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
                        level.levelEvent(2001, block.getPos(), Block.getId(block.getState()));
                    }
                }

                BlockPos blockpos = blockPatternMatch.getBlock(1, 2, 0).getPos();
                IronGolem copperGolem = ModEntities.COPPER_GOLEM.get().create((Level) level);
                copperGolem.setPlayerCreated(true);
                copperGolem.moveTo((double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.05D, (double)blockpos.getZ() + 0.5D, 0.0F, 0.0F);
                level.addFreshEntity(copperGolem);

                for(ServerPlayer serverplayer1 : level.getEntitiesOfClass(ServerPlayer.class, copperGolem.getBoundingBox().inflate(5.0D))) {
                    CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer1, copperGolem);
                }

                for(int i = 0; i < copperGolemPattern.getWidth(); ++i) {
                    for(int j = 0; j < copperGolemPattern.getHeight(); ++j) {
                        BlockInWorld block = blockPatternMatch.getBlock(i, j, 0);
                        level.blockUpdated(block.getPos(), Blocks.AIR);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ModEntities.COPPER_GOLEM.get(), IronGolem.createAttributes().build());
    }

    @SubscribeEvent
    public static void toolInteract(BlockEvent.BlockToolInteractEvent event) {
        Block block = event.getState().getBlock();
        LevelAccessor level = event.getWorld();

        BlockState blockState = event.getState();
        if (event.getToolAction().equals(ToolActions.AXE_WAX_OFF)) {
            // Replace the opposite half of double blocks
            if (blockState.hasProperty(DoorBlock.HALF)) {
                IWeatheringBlock.getUnwaxed(blockState).ifPresent((unWaxedBlockState) -> {
                    WeatheringUtils.handleAxeEvent(level, unWaxedBlockState, event.getPos());
                    event.setFinalState(unWaxedBlockState);
                });
            } else {
                IWeatheringBlock.getUnwaxed(blockState).ifPresent(event::setFinalState);
            }
        } else if (event.getToolAction().equals(ToolActions.AXE_SCRAPE) && block instanceof IWeatheringBlock) {
            // Replace the opposite half of double blocks
            if (blockState.hasProperty(DoorBlock.HALF)) {
                IWeatheringBlock.getPrevious(blockState).ifPresent((nextBlockState) -> {
                    WeatheringUtils.handleAxeEvent(level, nextBlockState, event.getPos());
                    event.setFinalState(nextBlockState);
                });
            } else {
                IWeatheringBlock.getPrevious(blockState).ifPresent(event::setFinalState);
            }
        }
    }
}
