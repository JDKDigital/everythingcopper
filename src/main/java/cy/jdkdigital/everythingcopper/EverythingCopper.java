package cy.jdkdigital.everythingcopper;

import cy.jdkdigital.everythingcopper.common.entity.CopperGolem;
import cy.jdkdigital.everythingcopper.event.EventHandler;
import cy.jdkdigital.everythingcopper.init.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod("everythingcopper")
public class EverythingCopper
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "everythingcopper";

    public EverythingCopper() {
        // TODO
        // cauldrons
        // bucket
        // dispenser behavior for minecart placement
        // fix minecart items
        // horse armor weathering and proper textures
        // iron/copper bar intersecting borders
        // less noise on rails
        // outline waxed copper blocks when holding wax
        // kiln for smelting non-blast and non-smoker items
        // wind chime?
        // lightning rod extension
        // depth meter
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEffects.EFFECTS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModLootModifiers.LOOT_SERIALIZERS.register(modEventBus);
        ModRecipeTypes.RECIPE_SERIALIZERS.register(modEventBus);
        ModSounds.SOUNDS.register(modEventBus);

        modEventBus.addListener(EventHandler::onEntityAttributeCreate);
        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::tabs);
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        DispenserBlock.registerBehavior(Items.CARVED_PUMPKIN, new CopperGolemDispenserBehavior());
        DispenserBlock.registerBehavior(Items.JACK_O_LANTERN, new CopperGolemDispenserBehavior());
    }

    public void tabs(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab().equals(CreativeModeTabs.COMBAT)) {
            event.accept(ModItems.COPPER_SWORD.get());
            event.accept(ModItems.COPPER_SHIELD.get());
            event.accept(ModItems.COPPER_HELMET.get());
            event.accept(ModItems.COPPER_CHESTPLATE.get());
            event.accept(ModItems.COPPER_LEGGINGS.get());
            event.accept(ModItems.COPPER_BOOTS.get());
            event.accept(ModItems.COPPER_HORSE_ARMOR.get());
        }
        if (event.getTab().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.accept(ModItems.COPPER_HOE.get());
            event.accept(ModItems.COPPER_AXE.get());
            event.accept(ModItems.COPPER_PICKAXE.get());
            event.accept(ModItems.COPPER_SHOVEL.get());
            event.accept(ModItems.COPPER_SHEARS.get());
        }
        if (event.getTab().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.accept(ModItems.COPPER_NUGGET.get());
        }
        if (event.getTab().equals(CreativeModeTabs.BUILDING_BLOCKS)) {
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
        }
        if (event.getTab().equals(CreativeModeTabs.REDSTONE_BLOCKS)) {
            event.accept(ModBlocks.COPPER_HOPPER.get());
            event.accept(ModBlocks.EXPOSED_COPPER_HOPPER.get());
            event.accept(ModBlocks.WEATHERED_COPPER_HOPPER.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_HOPPER.get());
            event.accept(ModBlocks.COPPER_RAIL.get());
            event.accept(ModBlocks.EXPOSED_COPPER_RAIL.get());
            event.accept(ModBlocks.WEATHERED_COPPER_RAIL.get());
            event.accept(ModBlocks.OXIDIZED_COPPER_RAIL.get());
        }
        if (event.getTab().equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
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
        }
    }

    static class CopperGolemDispenserBehavior extends OptionalDispenseItemBehavior {
        @Override
        public @NotNull ItemStack execute(BlockSource source, ItemStack stack) {
            Level level = source.getLevel();
            Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
            BlockPos blockpos = source.getPos().relative(direction);
            CarvedPumpkinBlock carvedpumpkinblock = (CarvedPumpkinBlock) Blocks.CARVED_PUMPKIN;
            if (level.isEmptyBlock(blockpos) && stack.getItem() instanceof BlockItem blockItem && (carvedpumpkinblock.canSpawnGolem(level, blockpos) || CopperGolem.canSpawnGolem(level, blockpos))) {
                if (!level.isClientSide) {
                    level.setBlock(blockpos, blockItem.getBlock().defaultBlockState(), Block.UPDATE_ALL);
                    level.gameEvent(null, GameEvent.BLOCK_PLACE, blockpos);
                    ForgeEventFactory.onBlockPlace(null, BlockSnapshot.create(level.dimension(), level, blockpos), direction);
                }

                stack.shrink(1);
                this.setSuccess(true);
            } else {
                this.setSuccess(ArmorItem.dispenseArmor(source, stack));
            }
            return stack;
        }
    }
}
