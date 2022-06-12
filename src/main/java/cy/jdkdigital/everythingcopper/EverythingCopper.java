package cy.jdkdigital.everythingcopper;

import cy.jdkdigital.everythingcopper.common.entity.CopperGolem;
import cy.jdkdigital.everythingcopper.event.EventHandler;
import cy.jdkdigital.everythingcopper.init.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.eventbus.api.IEventBus;
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

        MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        DefaultDispenseItemBehavior copperGolemAssemble = new OptionalDispenseItemBehavior()
        {
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
        };
        DispenserBlock.registerBehavior(Items.CARVED_PUMPKIN, copperGolemAssemble);
        DispenserBlock.registerBehavior(Items.JACK_O_LANTERN, copperGolemAssemble);
    }

    public void onServerStarting(AddReloadListenerEvent event) {
//        event.addListener(CopperShieldRenderer.INSTANCE);
    }
}
