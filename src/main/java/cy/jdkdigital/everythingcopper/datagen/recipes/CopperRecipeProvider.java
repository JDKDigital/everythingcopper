package cy.jdkdigital.everythingcopper.datagen.recipes;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.init.ModBlocks;
import cy.jdkdigital.everythingcopper.init.ModItems;
import cy.jdkdigital.everythingcopper.init.ModTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class CopperRecipeProvider extends RecipeProvider
{
    public CopperRecipeProvider(PackOutput generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // Blocks
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_ANVIL.get(), 1)
                .pattern("BBB")
                .pattern(" i ")
                .pattern("iii")
                .define('B', Tags.Items.STORAGE_BLOCKS_COPPER)
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_BLOCK, Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_BARS.get(), 16)
                .pattern("iii")
                .pattern("iii")
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_BUTTON.get(), 1)
                .pattern("nnn")
                .pattern("nnn")
                .define('n', ModTags.COPPER_NUGGET)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_NUGGET.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_CHAIN.get(), 1)
                .pattern("n")
                .pattern("i")
                .pattern("n")
                .define('i', Tags.Items.INGOTS_COPPER)
                .define('n', ModTags.COPPER_NUGGET)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_NUGGET.get(), Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_CHIME.get(), 1)
                .pattern("lll")
                .pattern("isi")
                .pattern("ibi")
                .define('l', Items.DARK_OAK_PLANKS)
                .define('i', Tags.Items.INGOTS_COPPER)
                .define('s', Tags.Items.STRING)
                .define('b', Items.DARK_OAK_BUTTON)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DARK_OAK_PLANKS, Items.COPPER_INGOT, Items.STRING))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_DOOR.get(), 3)
                .pattern("ii")
                .pattern("ii")
                .pattern("ii")
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_HOPPER.get(), 1)
                .pattern("i i")
                .pattern("iCi")
                .pattern(" i ")
                .define('i', Tags.Items.INGOTS_COPPER)
                .define('C', Tags.Items.CHESTS)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_LADDER.get(), 16)
                .pattern("i i")
                .pattern("iii")
                .pattern("i i")
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_LANTERN.get(), 1)
                .pattern("nnn")
                .pattern("ntn")
                .pattern("nnn")
                .define('t', Items.TORCH)
                .define('n', ModTags.COPPER_NUGGET)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_NUGGET.get(), Items.TORCH))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_PRESSURE_PLATE.get())
                .pattern("ii")
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_RAIL.get(), 16)
                .pattern("i i")
                .pattern("isi")
                .pattern("i i")
                .define('s', Tags.Items.RODS_WOODEN)
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_SOUL_LANTERN.get(), 1)
                .pattern("nnn")
                .pattern("ntn")
                .pattern("nnn")
                .define('t', Items.SOUL_TORCH)
                .define('n', ModTags.COPPER_NUGGET)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_NUGGET.get(), Items.SOUL_TORCH))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_TRAPDOOR.get())
                .pattern("ii")
                .pattern("ii")
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        // Items
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_AXE.get())
                .pattern("ii")
                .pattern("is")
                .pattern(" s")
                .define('s', Tags.Items.RODS_WOODEN)
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_SWORD.get())
                .pattern("i")
                .pattern("i")
                .pattern("s")
                .define('s', Tags.Items.RODS_WOODEN)
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_SHOVEL.get())
                .pattern("i")
                .pattern("s")
                .pattern("s")
                .define('s', Tags.Items.RODS_WOODEN)
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_PICKAXE.get())
                .pattern("iii")
                .pattern(" s ")
                .pattern(" s ")
                .define('s', Tags.Items.RODS_WOODEN)
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_HOE.get())
                .pattern("ii")
                .pattern(" s")
                .pattern(" s")
                .define('s', Tags.Items.RODS_WOODEN)
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_SHEARS.get())
                .pattern("i ")
                .pattern(" i")
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_SHIELD.get())
                .pattern("wiw")
                .pattern("www")
                .pattern(" w ")
                .define('w', ItemTags.PLANKS)
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_HELMET.get())
                .pattern("iii")
                .pattern("i i")
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_CHESTPLATE.get())
                .pattern("i i")
                .pattern("iii")
                .pattern("iii")
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_LEGGINGS.get())
                .pattern("iii")
                .pattern("i i")
                .pattern("i i")
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_BOOTS.get())
                .pattern("i i")
                .pattern("i i")
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_MINECART.get())
                .pattern("i i")
                .pattern("iii")
                .define('i', Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.COPPER_INGOT)
                .pattern("nnn")
                .pattern("nnn")
                .pattern("nnn")
                .define('n', ModTags.COPPER_NUGGET)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_NUGGET.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.COPPER_NUGGET.get(), 9)
                .requires(Tags.Items.INGOTS_COPPER)
                .group(EverythingCopper.MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        // Waxing
        /// I'm too bored for that
    }
}
