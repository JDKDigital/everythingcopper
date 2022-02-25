package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EverythingCopper.MODID);

    public static final RegistryObject<Block> COPPER_DOOR = createBlock("copper_door", () -> new CopperDoor(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.IRON_DOOR)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> EXPOSED_COPPER_DOOR = createBlock("exposed_copper_door", () -> new CopperDoor(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.copy(Blocks.IRON_DOOR)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WEATHERED_COPPER_DOOR = createBlock("weathered_copper_door", () -> new CopperDoor(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.copy(Blocks.IRON_DOOR)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> OXIDIZED_COPPER_DOOR = createBlock("oxidized_copper_door", () -> new CopperDoor(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.copy(Blocks.IRON_DOOR)), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> COPPER_BARS = createBlock("copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.IRON_BARS)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> EXPOSED_COPPER_BARS = createBlock("exposed_copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.copy(Blocks.IRON_BARS)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WEATHERED_COPPER_BARS = createBlock("weathered_copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.copy(Blocks.IRON_BARS)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> OXIDIZED_COPPER_BARS = createBlock("oxidized_copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.copy(Blocks.IRON_BARS)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> COPPER_TRAPDOOR = createBlock("copper_trapdoor", () -> new CopperTrapDoor(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.IRON_TRAPDOOR)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> EXPOSED_COPPER_TRAPDOOR = createBlock("exposed_copper_trapdoor", () -> new CopperTrapDoor(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.copy(Blocks.IRON_TRAPDOOR)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WEATHERED_COPPER_TRAPDOOR = createBlock("weathered_copper_trapdoor", () -> new CopperTrapDoor(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.copy(Blocks.IRON_TRAPDOOR)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> OXIDIZED_COPPER_TRAPDOOR = createBlock("oxidized_copper_trapdoor", () -> new CopperTrapDoor(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.copy(Blocks.IRON_TRAPDOOR)), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> COPPER_PRESSURE_PLATE = createBlock("copper_pressure_plate", () -> new CopperPressurePlate(WeatheringCopper.WeatherState.UNAFFECTED, 15, BlockBehaviour.Properties.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> EXPOSED_COPPER_PRESSURE_PLATE = createBlock("exposed_copper_pressure_plate", () -> new CopperPressurePlate(WeatheringCopper.WeatherState.EXPOSED, 15, BlockBehaviour.Properties.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WEATHERED_COPPER_PRESSURE_PLATE = createBlock("weathered_copper_pressure_plate", () -> new CopperPressurePlate(WeatheringCopper.WeatherState.WEATHERED, 15, BlockBehaviour.Properties.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> OXIDIZED_COPPER_PRESSURE_PLATE = createBlock("oxidized_copper_pressure_plate", () -> new CopperPressurePlate(WeatheringCopper.WeatherState.OXIDIZED, 15, BlockBehaviour.Properties.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> COPPER_HOPPER = createBlock("copper_hopper", () -> new CopperHopper(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 4.8F).sound(SoundType.METAL).noOcclusion()), null);
    public static final RegistryObject<Block> EXPOSED_COPPER_HOPPER = createBlock("exposed_copper_hopper", () -> new CopperHopper(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 4.8F).sound(SoundType.METAL).noOcclusion()), null);
    public static final RegistryObject<Block> WEATHERED_COPPER_HOPPER = createBlock("weathered_copper_hopper", () -> new CopperHopper(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 4.8F).sound(SoundType.METAL).noOcclusion()), null);
    public static final RegistryObject<Block> OXIDIZED_COPPER_HOPPER = createBlock("oxidized_copper_hopper", () -> new CopperHopper(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 4.8F).sound(SoundType.METAL).noOcclusion()), null);

//    public static final RegistryObject<Block> COPPER_CAULDRON = createBlock("copper_cauldron", () -> new CopperCauldron(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.CAULDRON)), CreativeModeTab.TAB_MISC);
//    public static final RegistryObject<Block> EXPOSED_COPPER_CAULDRON = createBlock("exposed_copper_cauldron", () -> new CopperCauldron(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.copy(Blocks.CAULDRON)), CreativeModeTab.TAB_MISC);
//    public static final RegistryObject<Block> WEATHERED_COPPER_CAULDRON = createBlock("weathered_copper_cauldron", () -> new CopperCauldron(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.copy(Blocks.CAULDRON)), CreativeModeTab.TAB_MISC);
//    public static final RegistryObject<Block> OXIDIZED_COPPER_CAULDRON = createBlock("oxidized_copper_cauldron", () -> new CopperCauldron(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.copy(Blocks.CAULDRON)), CreativeModeTab.TAB_MISC);
//
//    public static final RegistryObject<Block> COPPER_WATER_CAULDRON = createBlock("copper_water_cauldron", () -> new LayeredCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.RAIN, CauldronInteraction.WATER), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> EXPOSED_COPPER_WATER_CAULDRON = createBlock("exposed_copper_water_cauldron", () -> new LayeredCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.RAIN, CauldronInteraction.WATER), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> WEATHERED_COPPER_WATER_CAULDRON = createBlock("weathered_copper_water_cauldron", () -> new LayeredCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.RAIN, CauldronInteraction.WATER), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> OXIDIZED_COPPER_WATER_CAULDRON = createBlock("oxidized_copper_water_cauldron", () -> new LayeredCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.RAIN, CauldronInteraction.WATER), CreativeModeTab.TAB_MISC, false);
//
//    public static final RegistryObject<Block> COPPER_LAVA_CAULDRON = createBlock("copper_lava_cauldron", () -> new LavaCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).lightLevel((p_152690_) -> 15)), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> EXPOSED_COPPER_LAVA_CAULDRON = createBlock("exposed_copper_lava_cauldron", () -> new LavaCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).lightLevel((p_152690_) -> 15)), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> WEATHERED_COPPER_LAVA_CAULDRON = createBlock("weathered_copper_lava_cauldron", () -> new LavaCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).lightLevel((p_152690_) -> 15)), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> OXIDIZED_COPPER_LAVA_CAULDRON = createBlock("oxidized_copper_lava_cauldron", () -> new LavaCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).lightLevel((p_152690_) -> 15)), CreativeModeTab.TAB_MISC, false);
//
//    public static final RegistryObject<Block> COPPER_POWDER_SNOW_CAULDRON = createBlock("copper_powder_snow_cauldron", () -> new PowderSnowCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.SNOW, CauldronInteraction.POWDER_SNOW), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> EXPOSED_COPPER_POWDER_SNOW_CAULDRON = createBlock("exposed_opper_powder_snow_cauldron", () -> new PowderSnowCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.SNOW, CauldronInteraction.POWDER_SNOW), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> WEATHERED_COPPER_POWDER_SNOW_CAULDRON = createBlock("weathered_copper_powder_snow_cauldron", () -> new PowderSnowCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.SNOW, CauldronInteraction.POWDER_SNOW), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> OXIDIZED_COPPER_POWDER_SNOW_CAULDRON = createBlock("oxidized_copper_powder_snow_cauldron", () -> new PowderSnowCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.SNOW, CauldronInteraction.POWDER_SNOW), CreativeModeTab.TAB_MISC, false);

    public static final RegistryObject<Block> COPPER_LANTERN = createBlock("copper_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 15).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> EXPOSED_COPPER_LANTERN = createBlock("exposed_copper_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 15).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WEATHERED_COPPER_LANTERN = createBlock("weathered_copper_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 15).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> OXIDIZED_COPPER_LANTERN = createBlock("oxidized_copper_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 15).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> COPPER_SOUL_LANTERN = createBlock("copper_soul_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 10).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> EXPOSED_COPPER_SOUL_LANTERN = createBlock("exposed_copper_soul_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 10).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WEATHERED_COPPER_SOUL_LANTERN = createBlock("weathered_copper_soul_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 10).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> OXIDIZED_COPPER_SOUL_LANTERN = createBlock("oxidized_copper_soul_lantern", () -> new CopperLantern(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 10).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> COPPER_ANVIL = createBlock("copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 1200.0F).sound(SoundType.ANVIL)), null); // DECORATION
    public static final RegistryObject<Block> EXPOSED_COPPER_ANVIL = createBlock("exposed_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 1200.0F).sound(SoundType.ANVIL)), null);
    public static final RegistryObject<Block> WEATHERED_COPPER_ANVIL = createBlock("weathered_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 1200.0F).sound(SoundType.ANVIL)), null);
    public static final RegistryObject<Block> OXIDIZED_COPPER_ANVIL = createBlock("oxidized_copper_anvil", () -> new CopperAnvil(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 1200.0F).sound(SoundType.ANVIL)), null);

    public static final RegistryObject<Block> COPPER_CHAIN = createBlock("copper_chain", () -> new CopperChain(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.of(Material.METAL, MaterialColor.NONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.CHAIN).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> EXPOSED_COPPER_CHAIN = createBlock("exposed_copper_chain", () -> new CopperChain(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.of(Material.METAL, MaterialColor.NONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.CHAIN).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WEATHERED_COPPER_CHAIN = createBlock("weathered_copper_chain", () -> new CopperChain(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.of(Material.METAL, MaterialColor.NONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.CHAIN).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> OXIDIZED_COPPER_CHAIN = createBlock("oxidized_copper_chain", () -> new CopperChain(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.of(Material.METAL, MaterialColor.NONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.CHAIN).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> COPPER_RAIL = createBlock("copper_rail", () -> new CopperRail(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL)), null);
    public static final RegistryObject<Block> EXPOSED_COPPER_RAIL = createBlock("exposed_copper_rail", () -> new CopperRail(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL)), null);
    public static final RegistryObject<Block> WEATHERED_COPPER_RAIL = createBlock("weathered_copper_rail", () -> new CopperRail(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL)), null);
    public static final RegistryObject<Block> OXIDIZED_COPPER_RAIL = createBlock("oxidized_copper_rail", () -> new CopperRail(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL)), null);

    public static final RegistryObject<Block> WAXED_COPPER_DOOR = createBlock("waxed_copper_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_DOOR)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_DOOR = createBlock("waxed_exposed_copper_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_DOOR)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_DOOR = createBlock("waxed_weathered_copper_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_DOOR)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_DOOR = createBlock("waxed_oxidized_copper_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_DOOR)), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> WAXED_COPPER_BARS = createBlock("waxed_copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.IRON_BARS)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_BARS = createBlock("waxed_exposed_copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.copy(Blocks.IRON_BARS)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_BARS = createBlock("waxed_weathered_copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.copy(Blocks.IRON_BARS)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_BARS = createBlock("waxed_oxidized_copper_bars", () -> new CopperBars(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.copy(Blocks.IRON_BARS)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> WAXED_COPPER_TRAPDOOR = createBlock("waxed_copper_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_TRAPDOOR)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_TRAPDOOR = createBlock("waxed_exposed_copper_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_TRAPDOOR)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_TRAPDOOR = createBlock("waxed_weathered_copper_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_TRAPDOOR)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_TRAPDOOR = createBlock("waxed_oxidized_copper_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_TRAPDOOR)), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> WAXED_COPPER_PRESSURE_PLATE = createBlock("waxed_copper_pressure_plate", () -> new WeightedPressurePlateBlock(15, BlockBehaviour.Properties.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_PRESSURE_PLATE = createBlock("waxed_exposed_copper_pressure_plate", () -> new WeightedPressurePlateBlock(15, BlockBehaviour.Properties.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_PRESSURE_PLATE = createBlock("waxed_weathered_copper_pressure_plate", () -> new WeightedPressurePlateBlock(15, BlockBehaviour.Properties.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_PRESSURE_PLATE = createBlock("waxed_oxidized_copper_pressure_plate", () -> new WeightedPressurePlateBlock(15, BlockBehaviour.Properties.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> WAXED_COPPER_HOPPER = createBlock("waxed_copper_hopper", () -> new HopperBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 4.8F).sound(SoundType.METAL).noOcclusion()), null);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_HOPPER = createBlock("waxed_exposed_copper_hopper", () -> new HopperBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 4.8F).sound(SoundType.METAL).noOcclusion()), null);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_HOPPER = createBlock("waxed_weathered_copper_hopper", () -> new HopperBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 4.8F).sound(SoundType.METAL).noOcclusion()), null);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_HOPPER = createBlock("waxed_oxidized_copper_hopper", () -> new HopperBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 4.8F).sound(SoundType.METAL).noOcclusion()), null);

//    public static final RegistryObject<Block> WAXED_COPPER_CAULDRON = createBlock("waxed_copper_cauldron", () -> new CauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON)), CreativeModeTab.TAB_MISC);
//    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_CAULDRON = createBlock("waxed_exposed_copper_cauldron", () -> new CauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON)), CreativeModeTab.TAB_MISC);
//    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_CAULDRON = createBlock("waxed_weathered_copper_cauldron", () -> new CauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON)), CreativeModeTab.TAB_MISC);
//    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_CAULDRON = createBlock("waxed_oxidized_copper_cauldron", () -> new CauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON)), CreativeModeTab.TAB_MISC);
//
//    public static final RegistryObject<Block> WAXED_COPPER_WATER_CAULDRON = createBlock("waxed_copper_water_cauldron", () -> new LayeredCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.RAIN, CauldronInteraction.WATER), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_WATER_CAULDRON = createBlock("waxed_exposed_copper_water_cauldron", () -> new LayeredCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.RAIN, CauldronInteraction.WATER), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_WATER_CAULDRON = createBlock("waxed_weathered_copper_water_cauldron", () -> new LayeredCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.RAIN, CauldronInteraction.WATER), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_WATER_CAULDRON = createBlock("waxed_oxidized_copper_water_cauldron", () -> new LayeredCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.RAIN, CauldronInteraction.WATER), CreativeModeTab.TAB_MISC, false);
//
//    public static final RegistryObject<Block> WAXED_COPPER_LAVA_CAULDRON = createBlock("waxed_copper_lava_cauldron", () -> new LavaCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).lightLevel((p_152690_) -> 15)), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_LAVA_CAULDRON = createBlock("waxed_exposed_copper_lava_cauldron", () -> new LavaCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).lightLevel((p_152690_) -> 15)), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_LAVA_CAULDRON = createBlock("waxed_weathered_copper_lava_cauldron", () -> new LavaCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).lightLevel((p_152690_) -> 15)), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_LAVA_CAULDRON = createBlock("waxed_oxidized_copper_lava_cauldron", () -> new LavaCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).lightLevel((p_152690_) -> 15)), CreativeModeTab.TAB_MISC, false);
//
//    public static final RegistryObject<Block> WAXED_COPPER_POWDER_SNOW_CAULDRON = createBlock("waxed_copper_powder_snow_cauldron", () -> new PowderSnowCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.SNOW, CauldronInteraction.POWDER_SNOW), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_POWDER_SNOW_CAULDRON = createBlock("waxed_exposed_opper_powder_snow_cauldron", () -> new PowderSnowCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.SNOW, CauldronInteraction.POWDER_SNOW), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_POWDER_SNOW_CAULDRON = createBlock("waxed_weathered_copper_powder_snow_cauldron", () -> new PowderSnowCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.SNOW, CauldronInteraction.POWDER_SNOW), CreativeModeTab.TAB_MISC, false);
//    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_POWDER_SNOW_CAULDRON = createBlock("waxed_oxidized_copper_powder_snow_cauldron", () -> new PowderSnowCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.SNOW, CauldronInteraction.POWDER_SNOW), CreativeModeTab.TAB_MISC, false);

    public static final RegistryObject<Block> WAXED_COPPER_LANTERN = createBlock("waxed_copper_lantern", () -> new LanternBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 15).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_LANTERN = createBlock("waxed_exposed_copper_lantern", () -> new LanternBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 15).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_LANTERN = createBlock("waxed_weathered_copper_lantern", () -> new LanternBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 15).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_LANTERN = createBlock("waxed_oxidized_copper_lantern", () -> new LanternBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 15).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> WAXED_COPPER_SOUL_LANTERN = createBlock("waxed_copper_soul_lantern", () -> new LanternBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 10).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_SOUL_LANTERN = createBlock("waxed_exposed_copper_soul_lantern", () -> new LanternBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 10).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_SOUL_LANTERN = createBlock("waxed_weathered_copper_soul_lantern", () -> new LanternBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 10).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_SOUL_LANTERN = createBlock("waxed_oxidized_copper_soul_lantern", () -> new LanternBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((blockState) -> 10).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> WAXED_COPPER_ANVIL = createBlock("waxed_copper_anvil", () -> new AnvilBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 1200.0F).sound(SoundType.ANVIL)), null);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_ANVIL = createBlock("waxed_exposed_copper_anvil", () -> new AnvilBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 1200.0F).sound(SoundType.ANVIL)), null);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_ANVIL = createBlock("waxed_weathered_copper_anvil", () -> new AnvilBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 1200.0F).sound(SoundType.ANVIL)), null);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_ANVIL = createBlock("waxed_oxidized_copper_anvil", () -> new AnvilBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 1200.0F).sound(SoundType.ANVIL)), null);

    public static final RegistryObject<Block> WAXED_COPPER_CHAIN = createBlock("waxed_copper_chain", () -> new ChainBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.NONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.CHAIN).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_CHAIN = createBlock("waxed_exposed_copper_chain", () -> new ChainBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.NONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.CHAIN).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_CHAIN = createBlock("waxed_weathered_copper_chain", () -> new ChainBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.NONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.CHAIN).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_CHAIN = createBlock("waxed_oxidized_copper_chain", () -> new ChainBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.NONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.CHAIN).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> WAXED_COPPER_RAIL = createBlock("waxed_copper_rail", () -> new RailBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL)), null);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_RAIL = createBlock("waxed_exposed_copper_rail", () -> new RailBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL)), null);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_RAIL = createBlock("waxed_weathered_copper_rail", () -> new RailBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL)), null);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_RAIL = createBlock("waxed_oxidized_copper_rail", () -> new RailBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL)), null);

    public static <B extends Block> RegistryObject<B> createBlock(String name, Supplier<? extends B> supplier, CreativeModeTab itemGroup) {
        return createBlock(name, supplier, itemGroup, true);
    }

    public static <B extends Block> RegistryObject<B> createBlock(String name, Supplier<? extends B> supplier, CreativeModeTab itemGroup, boolean createItem) {
        RegistryObject<B> block = BLOCKS.register(name, supplier);
        if (createItem) {
            Item.Properties properties = new Item.Properties().tab(itemGroup);
            ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
        }
        return block;
    }
}
