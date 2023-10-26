package cy.jdkdigital.everythingcopper.event;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.block.IWeatheringBlock;
import cy.jdkdigital.everythingcopper.common.entity.CopperGolem;
import cy.jdkdigital.everythingcopper.init.ModBlocks;
import cy.jdkdigital.everythingcopper.init.ModEntities;
import cy.jdkdigital.everythingcopper.init.ModItems;
import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = EverythingCopper.MODID)
public class EventHandler
{
    @SubscribeEvent
    public static void placeCarvedPumpkin(BlockEvent.EntityPlaceEvent event) {
        if (event.getPlacedBlock().getBlock().equals(Blocks.CARVED_PUMPKIN) || event.getPlacedBlock().getBlock().equals(Blocks.JACK_O_LANTERN)) {
            LevelAccessor level = event.getLevel();

            BlockPattern copperGolemPattern = CopperGolem.getOrCreateCopperGolemFull();

            BlockPattern.BlockPatternMatch blockPatternMatch = copperGolemPattern.find(event.getLevel(), event.getPos());
            if (blockPatternMatch != null) {
                int waxed = 0;
                for(int j = 0; j < copperGolemPattern.getWidth(); ++j) {
                    for(int k = 0; k < copperGolemPattern.getHeight(); ++k) {
                        BlockInWorld block = blockPatternMatch.getBlock(j, k, 0);
                        if (ForgeRegistries.BLOCKS.getKey(block.getState().getBlock()).getPath().contains("waxed")) {
                            waxed++;
                        }
                        level.setBlock(block.getPos(), Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
                        level.levelEvent(2001, block.getPos(), Block.getId(block.getState()));
                    }
                }

                BlockPos blockpos = blockPatternMatch.getBlock(1, 2, 0).getPos();
                CopperGolem copperGolem = ModEntities.COPPER_GOLEM.get().create((Level) level);
                copperGolem.setPlayerCreated(true);
                copperGolem.moveTo((double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.05D, (double)blockpos.getZ() + 0.5D, 0.0F, 0.0F);
                level.addFreshEntity(copperGolem);
                copperGolem.setWaxed(waxed == 4);

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
    public static void toolInteract(BlockEvent.BlockToolModificationEvent event) {
        Block block = event.getState().getBlock();
        LevelAccessor level = event.getLevel();

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

    public static void onEntityAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ModEntities.COPPER_GOLEM.get(), IronGolem.createAttributes().build());
    }

    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.COMBAT)) {
            event.accept(ModItems.COPPER_SWORD.get());
            event.accept(ModItems.COPPER_SHIELD.get());
            event.accept(ModItems.COPPER_HELMET.get());
            event.accept(ModItems.COPPER_CHESTPLATE.get());
            event.accept(ModItems.COPPER_LEGGINGS.get());
            event.accept(ModItems.COPPER_BOOTS.get());
            event.accept(ModItems.COPPER_HORSE_ARMOR.get());
        }
        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.accept(ModItems.COPPER_HOE.get());
            event.accept(ModItems.COPPER_AXE.get());
            event.accept(ModItems.COPPER_PICKAXE.get());
            event.accept(ModItems.COPPER_SHOVEL.get());
            event.accept(ModItems.COPPER_SHEARS.get());
        }
        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.accept(ModItems.COPPER_NUGGET.get());
        }
        if (event.getTabKey().equals(CreativeModeTabs.BUILDING_BLOCKS)) {
            event.accept(ModBlocks.COPPER_DOOR.get());
            event.accept(ModBlocks.EXPOSED_COPPER_DOOR.get());
            event.accept(ModBlocks.WEATHERED_COPPER_DOOR.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_DOOR.get());
            event.accept(ModBlocks.COPPER_TRAPDOOR.get());
            event.accept(ModBlocks.EXPOSED_COPPER_TRAPDOOR.get());
            event.accept(ModBlocks.WEATHERED_COPPER_TRAPDOOR.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_TRAPDOOR.get());
            event.accept(ModBlocks.COPPER_PRESSURE_PLATE.get());
            event.accept(ModBlocks.EXPOSED_COPPER_PRESSURE_PLATE.get());
            event.accept(ModBlocks.WEATHERED_COPPER_PRESSURE_PLATE.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_PRESSURE_PLATE.get());
            event.accept(ModBlocks.COPPER_BARS.get());
            event.accept(ModBlocks.EXPOSED_COPPER_BARS.get());
            event.accept(ModBlocks.WEATHERED_COPPER_BARS.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_BARS.get());
            event.accept(ModBlocks.COPPER_CHAIN.get());
            event.accept(ModBlocks.EXPOSED_COPPER_CHAIN.get());
            event.accept(ModBlocks.WEATHERED_COPPER_CHAIN.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_CHAIN.get());
            event.accept(ModBlocks.COPPER_BUTTON.get());
            event.accept(ModBlocks.EXPOSED_COPPER_BUTTON.get());
            event.accept(ModBlocks.WEATHERED_COPPER_BUTTON.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_BUTTON.get());
            event.accept(ModBlocks.COPPER_GRATE.get());
            event.accept(ModBlocks.EXPOSED_COPPER_GRATE.get());
            event.accept(ModBlocks.WEATHERED_COPPER_GRATE.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_GRATE.get());
            event.accept(ModBlocks.CHISELED_COPPER.get());
            event.accept(ModBlocks.EXPOSED_CHISELED_COPPER.get());
            event.accept(ModBlocks.WEATHERED_CHISELED_COPPER.get());
            event.accept(ModBlocks.OXIDIZED_CHISELED_COPPER.get());

            event.accept(ModBlocks.WAXED_COPPER_DOOR.get());
            event.accept(ModBlocks.WAXED_EXPOSED_COPPER_DOOR.get());
            event.accept(ModBlocks.WAXED_WEATHERED_COPPER_DOOR.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_COPPER_DOOR.get());
            event.accept(ModBlocks.WAXED_COPPER_TRAPDOOR.get());
            event.accept(ModBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR.get());
            event.accept(ModBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR.get());
            event.accept(ModBlocks.WAXED_COPPER_PRESSURE_PLATE.get());
            event.accept(ModBlocks.WAXED_EXPOSED_COPPER_PRESSURE_PLATE.get());
            event.accept(ModBlocks.WAXED_WEATHERED_COPPER_PRESSURE_PLATE.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_COPPER_PRESSURE_PLATE.get());
            event.accept(ModBlocks.WAXED_COPPER_BARS.get());
            event.accept(ModBlocks.WAXED_EXPOSED_COPPER_BARS.get());
            event.accept(ModBlocks.WAXED_WEATHERED_COPPER_BARS.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_COPPER_BARS.get());
            event.accept(ModBlocks.WAXED_COPPER_CHAIN.get());
            event.accept(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN.get());
            event.accept(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN.get());
            event.accept(ModBlocks.WAXED_COPPER_BUTTON.get());
            event.accept(ModBlocks.WAXED_EXPOSED_COPPER_BUTTON.get());
            event.accept(ModBlocks.WAXED_WEATHERED_COPPER_BUTTON.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get());
            event.accept(ModBlocks.WAXED_COPPER_GRATE.get());
            event.accept(ModBlocks.WAXED_EXPOSED_COPPER_GRATE.get());
            event.accept(ModBlocks.WAXED_WEATHERED_COPPER_GRATE.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_COPPER_GRATE.get());
            event.accept(ModBlocks.WAXED_CHISELED_COPPER.get());
            event.accept(ModBlocks.WAXED_EXPOSED_CHISELED_COPPER.get());
            event.accept(ModBlocks.WAXED_WEATHERED_CHISELED_COPPER.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_CHISELED_COPPER.get());
        }
        if (event.getTabKey().equals(CreativeModeTabs.REDSTONE_BLOCKS)) {
            event.accept(ModBlocks.COPPER_HOPPER.get());
            event.accept(ModBlocks.EXPOSED_COPPER_HOPPER.get());
            event.accept(ModBlocks.WEATHERED_COPPER_HOPPER.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_HOPPER.get());
            event.accept(ModBlocks.COPPER_RAIL.get());
            event.accept(ModBlocks.EXPOSED_COPPER_RAIL.get());
            event.accept(ModBlocks.WEATHERED_COPPER_RAIL.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_RAIL.get());

            event.accept(ModBlocks.WAXED_COPPER_HOPPER.get());
            event.accept(ModBlocks.WAXED_EXPOSED_COPPER_HOPPER.get());
            event.accept(ModBlocks.WAXED_WEATHERED_COPPER_HOPPER.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_COPPER_HOPPER.get());
            event.accept(ModBlocks.WAXED_COPPER_RAIL.get());
            event.accept(ModBlocks.WAXED_EXPOSED_COPPER_RAIL.get());
            event.accept(ModBlocks.WAXED_WEATHERED_COPPER_RAIL.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_COPPER_RAIL.get());
        }
        if (event.getTabKey().equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
            event.accept(ModBlocks.WEATHERING_STATION.get());
            event.accept(ModBlocks.COPPER_LANTERN.get());
            event.accept(ModBlocks.EXPOSED_COPPER_LANTERN.get());
            event.accept(ModBlocks.WEATHERED_COPPER_LANTERN.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_LANTERN.get());
            event.accept(ModBlocks.COPPER_SOUL_LANTERN.get());
            event.accept(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN.get());
            event.accept(ModBlocks.WEATHERED_COPPER_SOUL_LANTERN.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN.get());
            event.accept(ModBlocks.COPPER_ANVIL.get());
            event.accept(ModBlocks.EXPOSED_COPPER_ANVIL.get());
            event.accept(ModBlocks.WEATHERED_COPPER_ANVIL.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_ANVIL.get());
            event.accept(ModBlocks.CHIPPED_COPPER_ANVIL.get());
            event.accept(ModBlocks.CHIPPED_EXPOSED_COPPER_ANVIL.get());
            event.accept(ModBlocks.CHIPPED_WEATHERED_COPPER_ANVIL.get());
            event.accept(ModBlocks.CHIPPED_OXIDIZED_COPPER_ANVIL.get());
            event.accept(ModBlocks.DAMAGED_COPPER_ANVIL.get());
            event.accept(ModBlocks.DAMAGED_EXPOSED_COPPER_ANVIL.get());
            event.accept(ModBlocks.DAMAGED_WEATHERED_COPPER_ANVIL.get());
            event.accept(ModBlocks.DAMAGED_OXIDIZED_COPPER_ANVIL.get());
            event.accept(ModBlocks.COPPER_LADDER.get());
            event.accept(ModBlocks.EXPOSED_COPPER_LADDER.get());
            event.accept(ModBlocks.WEATHERED_COPPER_LADDER.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_LADDER.get());
            event.accept(ModBlocks.COPPER_LAMP.get());
            event.accept(ModBlocks.EXPOSED_COPPER_LAMP.get());
            event.accept(ModBlocks.WEATHERED_COPPER_LAMP.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_LAMP.get());

            event.accept(ModBlocks.WAXED_COPPER_LANTERN.get());
            event.accept(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN.get());
            event.accept(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN.get());
            event.accept(ModBlocks.WAXED_COPPER_SOUL_LANTERN.get());
            event.accept(ModBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN.get());
            event.accept(ModBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN.get());
            event.accept(ModBlocks.WAXED_COPPER_ANVIL.get());
            event.accept(ModBlocks.WAXED_EXPOSED_COPPER_ANVIL.get());
            event.accept(ModBlocks.WAXED_WEATHERED_COPPER_ANVIL.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_COPPER_ANVIL.get());
            event.accept(ModBlocks.WAXED_CHIPPED_COPPER_ANVIL.get());
            event.accept(ModBlocks.WAXED_CHIPPED_EXPOSED_COPPER_ANVIL.get());
            event.accept(ModBlocks.WAXED_CHIPPED_WEATHERED_COPPER_ANVIL.get());
            event.accept(ModBlocks.WAXED_CHIPPED_OXIDIZED_COPPER_ANVIL.get());
            event.accept(ModBlocks.WAXED_DAMAGED_COPPER_ANVIL.get());
            event.accept(ModBlocks.WAXED_DAMAGED_EXPOSED_COPPER_ANVIL.get());
            event.accept(ModBlocks.WAXED_DAMAGED_WEATHERED_COPPER_ANVIL.get());
            event.accept(ModBlocks.WAXED_DAMAGED_OXIDIZED_COPPER_ANVIL.get());
            event.accept(ModBlocks.WAXED_COPPER_LADDER.get());
            event.accept(ModBlocks.WAXED_EXPOSED_COPPER_LADDER.get());
            event.accept(ModBlocks.WAXED_WEATHERED_COPPER_LADDER.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_COPPER_LADDER.get());
            event.accept(ModBlocks.WAXED_COPPER_LAMP.get());
            event.accept(ModBlocks.WAXED_EXPOSED_COPPER_LAMP.get());
            event.accept(ModBlocks.WAXED_WEATHERED_COPPER_LAMP.get());
            event.accept(ModBlocks.WAXED_OXIDIZED_COPPER_LAMP.get());
        }
    }
}
