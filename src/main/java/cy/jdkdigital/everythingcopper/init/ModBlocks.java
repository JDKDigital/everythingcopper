package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, EverythingCopper.MODID);

    public static final DeferredHolder<Block, ? extends Block> WEATHERING_STATION = createBlock("weathering_station", () -> new WeatheringStation(BlockBehaviour.Properties.ofFullCopy(Blocks.SMITHING_TABLE)));

    public static final DeferredHolder<Block, ? extends Block> COPPER_BARS = createBlock("copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)));
    public static final DeferredHolder<Block, ? extends Block> EXPOSED_COPPER_BARS = createBlock("exposed_copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)));
    public static final DeferredHolder<Block, ? extends Block> WEATHERED_COPPER_BARS = createBlock("weathered_copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)));
    public static final DeferredHolder<Block, ? extends Block> OXIDIZED_COPPER_BARS = createBlock("oxidized_copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)));

    public static final DeferredHolder<Block, ? extends Block> COPPER_PRESSURE_PLATE = createBlock("copper_pressure_plate", () -> new CopperPressurePlate(WeatheringCopper.WeatherState.UNAFFECTED, 15, BlockBehaviour.Properties.ofFullCopy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)));
    public static final DeferredHolder<Block, ? extends Block> EXPOSED_COPPER_PRESSURE_PLATE = createBlock("exposed_copper_pressure_plate", () -> new CopperPressurePlate(WeatheringCopper.WeatherState.EXPOSED, 15, BlockBehaviour.Properties.ofFullCopy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)));
    public static final DeferredHolder<Block, ? extends Block> WEATHERED_COPPER_PRESSURE_PLATE = createBlock("weathered_copper_pressure_plate", () -> new CopperPressurePlate(WeatheringCopper.WeatherState.WEATHERED, 15, BlockBehaviour.Properties.ofFullCopy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)));
    public static final DeferredHolder<Block, ? extends Block> OXIDIZED_COPPER_PRESSURE_PLATE = createBlock("oxidized_copper_pressure_plate", () -> new CopperPressurePlate(WeatheringCopper.WeatherState.OXIDIZED, 15, BlockBehaviour.Properties.ofFullCopy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)));

    public static final DeferredHolder<Block, ? extends Block> COPPER_HOPPER = createBlock("copper_hopper", () -> new CopperHopper(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.HOPPER)));
    public static final DeferredHolder<Block, ? extends Block> EXPOSED_COPPER_HOPPER = createBlock("exposed_copper_hopper", () -> new CopperHopper(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.HOPPER)));
    public static final DeferredHolder<Block, ? extends Block> WEATHERED_COPPER_HOPPER = createBlock("weathered_copper_hopper", () -> new CopperHopper(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.HOPPER)));
    public static final DeferredHolder<Block, ? extends Block> OXIDIZED_COPPER_HOPPER = createBlock("oxidized_copper_hopper", () -> new CopperHopper(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.HOPPER)));

    public static final DeferredHolder<Block, ? extends Block> COPPER_LANTERN = createBlock("copper_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final DeferredHolder<Block, ? extends Block> EXPOSED_COPPER_LANTERN = createBlock("exposed_copper_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final DeferredHolder<Block, ? extends Block> WEATHERED_COPPER_LANTERN = createBlock("weathered_copper_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final DeferredHolder<Block, ? extends Block> OXIDIZED_COPPER_LANTERN = createBlock("oxidized_copper_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));

    public static final DeferredHolder<Block, ? extends Block> COPPER_SOUL_LANTERN = createBlock("copper_soul_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN)));
    public static final DeferredHolder<Block, ? extends Block> EXPOSED_COPPER_SOUL_LANTERN = createBlock("exposed_copper_soul_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN)));
    public static final DeferredHolder<Block, ? extends Block> WEATHERED_COPPER_SOUL_LANTERN = createBlock("weathered_copper_soul_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN)));
    public static final DeferredHolder<Block, ? extends Block> OXIDIZED_COPPER_SOUL_LANTERN = createBlock("oxidized_copper_soul_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN)));

    public static final DeferredHolder<Block, ? extends Block> COPPER_ANVIL = createBlock("copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> EXPOSED_COPPER_ANVIL = createBlock("exposed_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> WEATHERED_COPPER_ANVIL = createBlock("weathered_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> OXIDIZED_COPPER_ANVIL = createBlock("oxidized_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL)));

    public static final DeferredHolder<Block, ? extends Block> CHIPPED_COPPER_ANVIL = createBlock("chipped_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.CHIPPED_ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> CHIPPED_EXPOSED_COPPER_ANVIL = createBlock("chipped_exposed_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.CHIPPED_ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> CHIPPED_WEATHERED_COPPER_ANVIL = createBlock("chipped_weathered_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.CHIPPED_ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> CHIPPED_OXIDIZED_COPPER_ANVIL = createBlock("chipped_oxidized_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.CHIPPED_ANVIL)));

    public static final DeferredHolder<Block, ? extends Block> DAMAGED_COPPER_ANVIL = createBlock("damaged_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.DAMAGED_ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> DAMAGED_EXPOSED_COPPER_ANVIL = createBlock("damaged_exposed_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.DAMAGED_ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> DAMAGED_WEATHERED_COPPER_ANVIL = createBlock("damaged_weathered_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.DAMAGED_ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> DAMAGED_OXIDIZED_COPPER_ANVIL = createBlock("damaged_oxidized_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.DAMAGED_ANVIL)));

    public static final DeferredHolder<Block, ? extends Block> COPPER_CHAIN = createBlock("copper_chain", () -> new CopperChain(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
    public static final DeferredHolder<Block, ? extends Block> EXPOSED_COPPER_CHAIN = createBlock("exposed_copper_chain", () -> new CopperChain(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
    public static final DeferredHolder<Block, ? extends Block> WEATHERED_COPPER_CHAIN = createBlock("weathered_copper_chain", () -> new CopperChain(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
    public static final DeferredHolder<Block, ? extends Block> OXIDIZED_COPPER_CHAIN = createBlock("oxidized_copper_chain", () -> new CopperChain(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));

    public static final DeferredHolder<Block, ? extends Block> COPPER_RAIL = createBlock("copper_rail", () -> new CopperRail(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final DeferredHolder<Block, ? extends Block> EXPOSED_COPPER_RAIL = createBlock("exposed_copper_rail", () -> new CopperRail(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final DeferredHolder<Block, ? extends Block> WEATHERED_COPPER_RAIL = createBlock("weathered_copper_rail", () -> new CopperRail(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final DeferredHolder<Block, ? extends Block> OXIDIZED_COPPER_RAIL = createBlock("oxidized_copper_rail", () -> new CopperRail(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));

    public static final DeferredHolder<Block, ? extends Block> COPPER_LADDER = createBlock("copper_ladder", () -> new CopperLadder(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER).randomTicks()));
    public static final DeferredHolder<Block, ? extends Block> EXPOSED_COPPER_LADDER = createBlock("exposed_copper_ladder", () -> new CopperLadder(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER).randomTicks()));
    public static final DeferredHolder<Block, ? extends Block> WEATHERED_COPPER_LADDER = createBlock("weathered_copper_ladder", () -> new CopperLadder(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER).randomTicks()));
    public static final DeferredHolder<Block, ? extends Block> OXIDIZED_COPPER_LADDER = createBlock("oxidized_copper_ladder", () -> new CopperLadder(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER).randomTicks()));

    public static final DeferredHolder<Block, ? extends Block> COPPER_BUTTON = createBlock("copper_button", () -> new CopperButton(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON)));
    public static final DeferredHolder<Block, ? extends Block> EXPOSED_COPPER_BUTTON = createBlock("exposed_copper_button", () -> new CopperButton(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON)));
    public static final DeferredHolder<Block, ? extends Block> WEATHERED_COPPER_BUTTON = createBlock("weathered_copper_button", () -> new CopperButton(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON)));
    public static final DeferredHolder<Block, ? extends Block> OXIDIZED_COPPER_BUTTON = createBlock("oxidized_copper_button", () -> new CopperButton(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON)));

    public static final DeferredHolder<Block, ? extends Block> WAXED_COPPER_BARS = createBlock("waxed_copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_EXPOSED_COPPER_BARS = createBlock("waxed_exposed_copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_WEATHERED_COPPER_BARS = createBlock("waxed_weathered_copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_OXIDIZED_COPPER_BARS = createBlock("waxed_oxidized_copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)));

    public static final DeferredHolder<Block, ? extends Block> WAXED_COPPER_PRESSURE_PLATE = createBlock("waxed_copper_pressure_plate", () -> new WeightedPressurePlateBlock(15, BlockSetType.IRON, BlockBehaviour.Properties.ofFullCopy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_EXPOSED_COPPER_PRESSURE_PLATE = createBlock("waxed_exposed_copper_pressure_plate", () -> new WeightedPressurePlateBlock(15, BlockSetType.IRON, BlockBehaviour.Properties.ofFullCopy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_WEATHERED_COPPER_PRESSURE_PLATE = createBlock("waxed_weathered_copper_pressure_plate", () -> new WeightedPressurePlateBlock(15, BlockSetType.IRON, BlockBehaviour.Properties.ofFullCopy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_OXIDIZED_COPPER_PRESSURE_PLATE = createBlock("waxed_oxidized_copper_pressure_plate", () -> new WeightedPressurePlateBlock(15, BlockSetType.IRON, BlockBehaviour.Properties.ofFullCopy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)));

    public static final DeferredHolder<Block, ? extends Block> WAXED_COPPER_HOPPER = createBlock("waxed_copper_hopper", () -> new CopperHopper(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.HOPPER)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_EXPOSED_COPPER_HOPPER = createBlock("waxed_exposed_copper_hopper", () -> new CopperHopper(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.HOPPER)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_WEATHERED_COPPER_HOPPER = createBlock("waxed_weathered_copper_hopper", () -> new CopperHopper(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.HOPPER)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_OXIDIZED_COPPER_HOPPER = createBlock("waxed_oxidized_copper_hopper", () -> new CopperHopper(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.HOPPER)));

    public static final DeferredHolder<Block, ? extends Block> WAXED_COPPER_LANTERN = createBlock("waxed_copper_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_EXPOSED_COPPER_LANTERN = createBlock("waxed_exposed_copper_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_WEATHERED_COPPER_LANTERN = createBlock("waxed_weathered_copper_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_OXIDIZED_COPPER_LANTERN = createBlock("waxed_oxidized_copper_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));

    public static final DeferredHolder<Block, ? extends Block> WAXED_COPPER_SOUL_LANTERN = createBlock("waxed_copper_soul_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_EXPOSED_COPPER_SOUL_LANTERN = createBlock("waxed_exposed_copper_soul_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_WEATHERED_COPPER_SOUL_LANTERN = createBlock("waxed_weathered_copper_soul_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_OXIDIZED_COPPER_SOUL_LANTERN = createBlock("waxed_oxidized_copper_soul_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN)));

    public static final DeferredHolder<Block, ? extends Block> WAXED_COPPER_ANVIL = createBlock("waxed_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_EXPOSED_COPPER_ANVIL = createBlock("waxed_exposed_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_WEATHERED_COPPER_ANVIL = createBlock("waxed_weathered_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_OXIDIZED_COPPER_ANVIL = createBlock("waxed_oxidized_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL)));

    public static final DeferredHolder<Block, ? extends Block> WAXED_CHIPPED_COPPER_ANVIL = createBlock("waxed_chipped_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.CHIPPED_ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_CHIPPED_EXPOSED_COPPER_ANVIL = createBlock("waxed_chipped_exposed_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.CHIPPED_ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_CHIPPED_WEATHERED_COPPER_ANVIL = createBlock("waxed_chipped_weathered_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.CHIPPED_ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_CHIPPED_OXIDIZED_COPPER_ANVIL = createBlock("waxed_chipped_oxidized_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.CHIPPED_ANVIL)));

    public static final DeferredHolder<Block, ? extends Block> WAXED_DAMAGED_COPPER_ANVIL = createBlock("waxed_damaged_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.DAMAGED_ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_DAMAGED_EXPOSED_COPPER_ANVIL = createBlock("waxed_damaged_exposed_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.DAMAGED_ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_DAMAGED_WEATHERED_COPPER_ANVIL = createBlock("waxed_damaged_weathered_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.DAMAGED_ANVIL)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_DAMAGED_OXIDIZED_COPPER_ANVIL = createBlock("waxed_damaged_oxidized_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.DAMAGED_ANVIL)));

    public static final DeferredHolder<Block, ? extends Block> WAXED_COPPER_CHAIN = createBlock("waxed_copper_chain", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_EXPOSED_COPPER_CHAIN = createBlock("waxed_exposed_copper_chain", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_WEATHERED_COPPER_CHAIN = createBlock("waxed_weathered_copper_chain", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_OXIDIZED_COPPER_CHAIN = createBlock("waxed_oxidized_copper_chain", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));

    public static final DeferredHolder<Block, ? extends Block> WAXED_COPPER_RAIL = createBlock("waxed_copper_rail", () -> new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_EXPOSED_COPPER_RAIL = createBlock("waxed_exposed_copper_rail", () -> new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_WEATHERED_COPPER_RAIL = createBlock("waxed_weathered_copper_rail", () -> new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_OXIDIZED_COPPER_RAIL = createBlock("waxed_oxidized_copper_rail", () -> new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));

    public static final DeferredHolder<Block, ? extends Block> WAXED_COPPER_LADDER = createBlock("waxed_copper_ladder", () -> new CopperLadder(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER).randomTicks()));
    public static final DeferredHolder<Block, ? extends Block> WAXED_EXPOSED_COPPER_LADDER = createBlock("waxed_exposed_copper_ladder", () -> new CopperLadder(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER).randomTicks()));
    public static final DeferredHolder<Block, ? extends Block> WAXED_WEATHERED_COPPER_LADDER = createBlock("waxed_weathered_copper_ladder", () -> new CopperLadder(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER).randomTicks()));
    public static final DeferredHolder<Block, ? extends Block> WAXED_OXIDIZED_COPPER_LADDER = createBlock("waxed_oxidized_copper_ladder", () -> new CopperLadder(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER).randomTicks()));

    public static final DeferredHolder<Block, ? extends Block> WAXED_COPPER_BUTTON = createBlock("waxed_copper_button", () -> new CopperButton(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_EXPOSED_COPPER_BUTTON = createBlock("waxed_exposed_copper_button", () -> new CopperButton(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_WEATHERED_COPPER_BUTTON = createBlock("waxed_weathered_copper_button", () -> new CopperButton(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON)));
    public static final DeferredHolder<Block, ? extends Block> WAXED_OXIDIZED_COPPER_BUTTON = createBlock("waxed_oxidized_copper_button", () -> new CopperButton(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON)));

    public static DeferredHolder<Block, ? extends Block> createBlock(String name, Supplier<? extends Block> supplier) {
        return createBlock(name, supplier, true);
    }

    public static DeferredHolder<Block, ? extends Block> createBlock(String name, Supplier<? extends Block> supplier, boolean createItem) {
        DeferredHolder<Block, ? extends Block> block = BLOCKS.register(name, supplier);
        if (createItem) {
            ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        }
        return block;
    }

    private static boolean never(BlockState blockState, BlockGetter level, BlockPos pos) {
        return false;
    }

    protected static Boolean never(BlockState blockState, BlockGetter level, BlockPos pos, EntityType<?> entityType) {
        return false;
    }
}
