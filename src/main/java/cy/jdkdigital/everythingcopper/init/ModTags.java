package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags
{
    public static final TagKey<Block> NEEDS_COPPER_TOOL = BlockTags.create(ResourceLocation.fromNamespaceAndPath(EverythingCopper.MODID, "needs_copper_tool"));
    public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = BlockTags.create(ResourceLocation.fromNamespaceAndPath(EverythingCopper.MODID, "incorrect_for_copper_tool"));
    public static final TagKey<Item> WAXING_ITEMS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(EverythingCopper.MODID, "waxing_items"));
    public static final TagKey<Item> COPPER_NUGGET = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "nuggets/copper"));
}
