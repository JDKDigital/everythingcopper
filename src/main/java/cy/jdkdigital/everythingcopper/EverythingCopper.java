package cy.jdkdigital.everythingcopper;

import cy.jdkdigital.everythingcopper.common.entity.CopperGolem;
import cy.jdkdigital.everythingcopper.event.EventHandler;
import cy.jdkdigital.everythingcopper.init.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("everythingcopper")
public class EverythingCopper
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "everythingcopper";

    public EverythingCopper(IEventBus modEventBus, ModContainer modContainer) {
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
        // lightning rod extension
        // depth meter

        ModEffects.EFFECTS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModLootModifiers.LOOT_SERIALIZERS.register(modEventBus);
        ModRecipeTypes.RECIPE_SERIALIZERS.register(modEventBus);
        ModDataComponents.DATA_COMPONENTS.register(modEventBus);
        ModArmorMaterials.ARMOR_MATERIALS.register(modEventBus);

        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(EventHandler::onEntityAttributeCreate);
        modEventBus.addListener(EventHandler::buildContents);
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        DispenserBlock.registerBehavior(Items.CARVED_PUMPKIN, new CopperGolemDispenserBehavior());
        DispenserBlock.registerBehavior(Items.JACK_O_LANTERN, new CopperGolemDispenserBehavior());
    }

    static class CopperGolemDispenserBehavior extends OptionalDispenseItemBehavior {
        @Override
        protected ItemStack execute(BlockSource pBlockSource, ItemStack pItem) {
            Level level = pBlockSource.level();
            Direction direction = pBlockSource.state().getValue(DispenserBlock.FACING);
            BlockPos blockpos = pBlockSource.pos().relative(direction);
            CarvedPumpkinBlock carvedpumpkinblock = (CarvedPumpkinBlock) Blocks.CARVED_PUMPKIN;
            if (level.isEmptyBlock(blockpos) && pItem.getItem() instanceof BlockItem blockItem && (carvedpumpkinblock.canSpawnGolem(level, blockpos) || CopperGolem.canSpawnGolem(level, blockpos))) {
                if (!level.isClientSide) {
                    level.setBlock(blockpos, blockItem.getBlock().defaultBlockState(), Block.UPDATE_ALL);
                    level.gameEvent(null, GameEvent.BLOCK_PLACE, blockpos);
//                    ForgeEventFactory.onBlockPlace(null, BlockSnapshot.create(level.dimension(), level, blockpos), direction);
                }

                pItem.shrink(1);
                this.setSuccess(true);
            } else {
                this.setSuccess(ArmorItem.dispenseArmor(pBlockSource, pItem));
            }
            return pItem;
        }
    }
}
