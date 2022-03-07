package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

public class ModTags
{
    public static final Tag<Item> WAXING_ITEMS = ItemTags.createOptional(new ResourceLocation(EverythingCopper.MODID, "waxing_items"));
}
