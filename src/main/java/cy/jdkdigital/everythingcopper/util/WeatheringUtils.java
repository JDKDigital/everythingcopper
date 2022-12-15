package cy.jdkdigital.everythingcopper.util;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import cy.jdkdigital.everythingcopper.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import java.util.function.Supplier;

public class WeatheringUtils
{
    public static Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK;
    public static Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK;
    public static Supplier<BiMap<Block, Block>> WAX_ON_BY_BLOCK;
    public static Supplier<BiMap<Block, Block>> WAX_OFF_BY_BLOCK;

    public static void buildBlockMap() {
        if (NEXT_BY_BLOCK == null) {
            NEXT_BY_BLOCK = Suppliers.memoize(() ->
                    ImmutableBiMap.<Block, Block>builder().put(
                                    ModBlocks.COPPER_DOOR.get(),
                                    ModBlocks.EXPOSED_COPPER_DOOR.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_DOOR.get(),
                                    ModBlocks.WEATHERED_COPPER_DOOR.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_DOOR.get(),
                                    ModBlocks.OXIDIZED_COPPER_DOOR.get()
                            ).put(
                                    ModBlocks.COPPER_BARS.get(),
                                    ModBlocks.EXPOSED_COPPER_BARS.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_BARS.get(),
                                    ModBlocks.WEATHERED_COPPER_BARS.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_BARS.get(),
                                    ModBlocks.OXIDIZED_COPPER_BARS.get()
                            ).put(
                                    ModBlocks.COPPER_TRAPDOOR.get(),
                                    ModBlocks.EXPOSED_COPPER_TRAPDOOR.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_TRAPDOOR.get(),
                                    ModBlocks.WEATHERED_COPPER_TRAPDOOR.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_TRAPDOOR.get(),
                                    ModBlocks.OXIDIZED_COPPER_TRAPDOOR.get()
                            ).put(
                                    ModBlocks.COPPER_PRESSURE_PLATE.get(),
                                    ModBlocks.EXPOSED_COPPER_PRESSURE_PLATE.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_PRESSURE_PLATE.get(),
                                    ModBlocks.WEATHERED_COPPER_PRESSURE_PLATE.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_PRESSURE_PLATE.get(),
                                    ModBlocks.OXIDIZED_COPPER_PRESSURE_PLATE.get()
                            ).put(
                                    ModBlocks.COPPER_HOPPER.get(),
                                    ModBlocks.EXPOSED_COPPER_HOPPER.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_HOPPER.get(),
                                    ModBlocks.WEATHERED_COPPER_HOPPER.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_HOPPER.get(),
                                    ModBlocks.OXIDIZED_COPPER_HOPPER.get()
                            ).put(
                                    ModBlocks.COPPER_LANTERN.get(),
                                    ModBlocks.EXPOSED_COPPER_LANTERN.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_LANTERN.get(),
                                    ModBlocks.WEATHERED_COPPER_LANTERN.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_LANTERN.get(),
                                    ModBlocks.OXIDIZED_COPPER_LANTERN.get()
                            ).put(
                                    ModBlocks.COPPER_SOUL_LANTERN.get(),
                                    ModBlocks.EXPOSED_COPPER_SOUL_LANTERN.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_SOUL_LANTERN.get(),
                                    ModBlocks.WEATHERED_COPPER_SOUL_LANTERN.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_SOUL_LANTERN.get(),
                                    ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN.get()
                            ).put(
                                    ModBlocks.COPPER_RAIL.get(),
                                    ModBlocks.EXPOSED_COPPER_RAIL.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_RAIL.get(),
                                    ModBlocks.WEATHERED_COPPER_RAIL.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_RAIL.get(),
                                    ModBlocks.OXIDIZED_COPPER_RAIL.get()
                            ).put(
                                    ModBlocks.COPPER_LADDER.get(),
                                    ModBlocks.EXPOSED_COPPER_LADDER.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_LADDER.get(),
                                    ModBlocks.WEATHERED_COPPER_LADDER.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_LADDER.get(),
                                    ModBlocks.OXIDIZED_COPPER_LADDER.get()
                            ).put(
                                    ModBlocks.COPPER_ANVIL.get(),
                                    ModBlocks.EXPOSED_COPPER_ANVIL.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_ANVIL.get(),
                                    ModBlocks.WEATHERED_COPPER_ANVIL.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_ANVIL.get(),
                                    ModBlocks.OXIDIZED_COPPER_ANVIL.get()
                            ).put(
                                    ModBlocks.COPPER_CHAIN.get(),
                                    ModBlocks.EXPOSED_COPPER_CHAIN.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_CHAIN.get(),
                                    ModBlocks.WEATHERED_COPPER_CHAIN.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_CHAIN.get(),
                                    ModBlocks.OXIDIZED_COPPER_CHAIN.get()
                            ).put(
                                    ModBlocks.COPPER_BUTTON.get(),
                                    ModBlocks.EXPOSED_COPPER_BUTTON.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_BUTTON.get(),
                                    ModBlocks.WEATHERED_COPPER_BUTTON.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_BUTTON.get(),
                                    ModBlocks.OXIDIZED_COPPER_BUTTON.get()
                            )
                            .build()
            );
            PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());
        }
    }

    public static void buildWaxBlockMap() {
        if (WAX_ON_BY_BLOCK == null) {
            WAX_ON_BY_BLOCK = Suppliers.memoize(() ->
                    ImmutableBiMap.<Block, Block>builder().put(
                                    ModBlocks.COPPER_DOOR.get(),
                                    ModBlocks.WAXED_COPPER_DOOR.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_DOOR.get(),
                                    ModBlocks.WAXED_EXPOSED_COPPER_DOOR.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_DOOR.get(),
                                    ModBlocks.WAXED_WEATHERED_COPPER_DOOR.get()
                            ).put(
                                    ModBlocks.OXIDIZED_COPPER_DOOR.get(),
                                    ModBlocks.WAXED_OXIDIZED_COPPER_DOOR.get()
                            ).put(
                                    ModBlocks.COPPER_BARS.get(),
                                    ModBlocks.WAXED_COPPER_BARS.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_BARS.get(),
                                    ModBlocks.WAXED_EXPOSED_COPPER_BARS.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_BARS.get(),
                                    ModBlocks.WAXED_WEATHERED_COPPER_BARS.get()
                            ).put(
                                    ModBlocks.OXIDIZED_COPPER_BARS.get(),
                                    ModBlocks.WAXED_OXIDIZED_COPPER_BARS.get()
                            ).put(
                                    ModBlocks.COPPER_TRAPDOOR.get(),
                                    ModBlocks.WAXED_COPPER_TRAPDOOR.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_TRAPDOOR.get(),
                                    ModBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_TRAPDOOR.get(),
                                    ModBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR.get()
                            ).put(
                                    ModBlocks.OXIDIZED_COPPER_TRAPDOOR.get(),
                                    ModBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR.get()
                            ).put(
                                    ModBlocks.COPPER_PRESSURE_PLATE.get(),
                                    ModBlocks.WAXED_COPPER_PRESSURE_PLATE.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_PRESSURE_PLATE.get(),
                                    ModBlocks.WAXED_EXPOSED_COPPER_PRESSURE_PLATE.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_PRESSURE_PLATE.get(),
                                    ModBlocks.WAXED_WEATHERED_COPPER_PRESSURE_PLATE.get()
                            ).put(
                                    ModBlocks.OXIDIZED_COPPER_PRESSURE_PLATE.get(),
                                    ModBlocks.WAXED_OXIDIZED_COPPER_PRESSURE_PLATE.get()
                            ).put(
                                    ModBlocks.COPPER_HOPPER.get(),
                                    ModBlocks.WAXED_COPPER_HOPPER.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_HOPPER.get(),
                                    ModBlocks.WAXED_EXPOSED_COPPER_HOPPER.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_HOPPER.get(),
                                    ModBlocks.WAXED_WEATHERED_COPPER_HOPPER.get()
                            ).put(
                                    ModBlocks.OXIDIZED_COPPER_HOPPER.get(),
                                    ModBlocks.WAXED_OXIDIZED_COPPER_HOPPER.get()
                            ).put(
                                    ModBlocks.COPPER_LANTERN.get(),
                                    ModBlocks.WAXED_COPPER_LANTERN.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_LANTERN.get(),
                                    ModBlocks.WAXED_EXPOSED_COPPER_LANTERN.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_LANTERN.get(),
                                    ModBlocks.WAXED_WEATHERED_COPPER_LANTERN.get()
                            ).put(
                                    ModBlocks.OXIDIZED_COPPER_LANTERN.get(),
                                    ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN.get()
                            ).put(
                                    ModBlocks.COPPER_SOUL_LANTERN.get(),
                                    ModBlocks.WAXED_COPPER_SOUL_LANTERN.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_SOUL_LANTERN.get(),
                                    ModBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_SOUL_LANTERN.get(),
                                    ModBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN.get()
                            ).put(
                                    ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN.get(),
                                    ModBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN.get()
                            ).put(
                                    ModBlocks.COPPER_RAIL.get(),
                                    ModBlocks.WAXED_COPPER_RAIL.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_RAIL.get(),
                                    ModBlocks.WAXED_EXPOSED_COPPER_RAIL.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_RAIL.get(),
                                    ModBlocks.WAXED_WEATHERED_COPPER_RAIL.get()
                            ).put(
                                    ModBlocks.OXIDIZED_COPPER_RAIL.get(),
                                    ModBlocks.WAXED_OXIDIZED_COPPER_RAIL.get()
                            ).put(
                                    ModBlocks.COPPER_LADDER.get(),
                                    ModBlocks.WAXED_COPPER_LADDER.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_LADDER.get(),
                                    ModBlocks.WAXED_EXPOSED_COPPER_LADDER.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_LADDER.get(),
                                    ModBlocks.WAXED_WEATHERED_COPPER_LADDER.get()
                            ).put(
                                    ModBlocks.OXIDIZED_COPPER_LADDER.get(),
                                    ModBlocks.WAXED_OXIDIZED_COPPER_LADDER.get()
                            ).put(
                                    ModBlocks.COPPER_ANVIL.get(),
                                    ModBlocks.WAXED_COPPER_ANVIL.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_ANVIL.get(),
                                    ModBlocks.WAXED_EXPOSED_COPPER_ANVIL.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_ANVIL.get(),
                                    ModBlocks.WAXED_WEATHERED_COPPER_ANVIL.get()
                            ).put(
                                    ModBlocks.OXIDIZED_COPPER_ANVIL.get(),
                                    ModBlocks.WAXED_OXIDIZED_COPPER_ANVIL.get()
                            ).put(
                                    ModBlocks.COPPER_CHAIN.get(),
                                    ModBlocks.WAXED_COPPER_CHAIN.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_CHAIN.get(),
                                    ModBlocks.WAXED_EXPOSED_COPPER_CHAIN.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_CHAIN.get(),
                                    ModBlocks.WAXED_WEATHERED_COPPER_CHAIN.get()
                            ).put(
                                    ModBlocks.OXIDIZED_COPPER_CHAIN.get(),
                                    ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN.get()
                            ).put(
                                    ModBlocks.COPPER_BUTTON.get(),
                                    ModBlocks.WAXED_COPPER_BUTTON.get()
                            ).put(
                                    ModBlocks.EXPOSED_COPPER_BUTTON.get(),
                                    ModBlocks.WAXED_EXPOSED_COPPER_BUTTON.get()
                            ).put(
                                    ModBlocks.WEATHERED_COPPER_BUTTON.get(),
                                    ModBlocks.WAXED_WEATHERED_COPPER_BUTTON.get()
                            ).put(
                                    ModBlocks.OXIDIZED_COPPER_BUTTON.get(),
                                    ModBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get()
                            )
                            .build()
            );
            WAX_OFF_BY_BLOCK = Suppliers.memoize(() -> WAX_ON_BY_BLOCK.get().inverse());
        }
    }

    public static String nextState(String state) {
        return switch (state) {
            case "unaffected" -> "exposed";
            case "exposed" -> "weathered";
            default -> "oxidized";
        };
    }

    public static String prevState(String state) {
        return switch (state) {
            case "oxidized" -> "weathered";
            case "weathered" -> "exposed";
            default -> "unaffected";
        };
    }

    public static WeatheringCopper.WeatherState nextState(WeatheringCopper.WeatherState state) {
        return switch (state) {
            case UNAFFECTED -> WeatheringCopper.WeatherState.EXPOSED;
            case EXPOSED -> WeatheringCopper.WeatherState.WEATHERED;
            default -> WeatheringCopper.WeatherState.OXIDIZED;
        };
    }

    public static WeatheringCopper.WeatherState prevState(WeatheringCopper.WeatherState state) {
        return switch (state) {
            case OXIDIZED -> WeatheringCopper.WeatherState.WEATHERED;
            case WEATHERED -> WeatheringCopper.WeatherState.EXPOSED;
            default -> WeatheringCopper.WeatherState.UNAFFECTED;
        };
    }

    public static void handleAxeEvent(LevelAccessor level, BlockState nextBlockState, BlockPos position) {
        BlockPos bottomPosition = position;
        boolean wasBottomUse = true;
        if (nextBlockState.getValue(DoorBlock.HALF).equals(DoubleBlockHalf.UPPER)) {
            bottomPosition = bottomPosition.below();
            wasBottomUse = false;
        }

        level.levelEvent(null, 3005, wasBottomUse ? bottomPosition.above() : bottomPosition, 0);

        level.setBlock(bottomPosition, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);

        level.setBlock(bottomPosition, nextBlockState.setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER), Block.UPDATE_CLIENTS);
        level.setBlock(bottomPosition.above(), nextBlockState.setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL_IMMEDIATE);
    }
}
