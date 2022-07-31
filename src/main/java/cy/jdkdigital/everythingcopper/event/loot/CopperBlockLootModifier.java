package cy.jdkdigital.everythingcopper.event.loot;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import cy.jdkdigital.everythingcopper.common.block.IWeatheringBlock;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class CopperBlockLootModifier extends LootModifier
{
    public static final Supplier<Codec<CopperBlockLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).and(
            inst.group(
                    ForgeRegistries.ITEMS.getCodec().fieldOf("addition").forGetter(m -> m.addition),
                    Codec.INT.fieldOf("count").forGetter(m -> m.count)
            )).apply(inst, CopperBlockLootModifier::new)));

    private final Item addition;
    private final int count;

    protected CopperBlockLootModifier(LootItemCondition[] conditionsIn, Item addition, Integer count) {
        super(conditionsIn);
        this.addition = addition;
        this.count = count;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        // Randomized tarnish level
        if (context.getRandom().nextBoolean() && addition instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            for (int i = 0; i < context.getRandom().nextInt(5); i++) {
                block = IWeatheringBlock.getNext(block).orElse(block);
            }
            ItemStack loot = new ItemStack(block.asItem(), count);
            generatedLoot.add(loot);
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
