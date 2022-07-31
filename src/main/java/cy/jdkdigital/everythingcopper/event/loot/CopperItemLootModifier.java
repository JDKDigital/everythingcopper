package cy.jdkdigital.everythingcopper.event.loot;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class CopperItemLootModifier extends LootModifier
{
    public static final Supplier<Codec<CopperItemLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("addition").forGetter(m -> m.addition))
            .apply(inst, CopperItemLootModifier::new)
    ));

    private final Item addition;

    protected CopperItemLootModifier(LootItemCondition[] conditionsIn, Item addition) {
        super(conditionsIn);
        this.addition = addition;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (context.getRandom().nextBoolean()) {
            ItemStack loot = new ItemStack(addition, 1);

            // Randomized tarnish level
            CompoundTag tag = loot.getOrCreateTag();
            switch (context.getRandom().nextInt(3)) {
                case 0 -> tag.putString("state", "exposed");
                case 1 -> tag.putString("state", "weathered");
                default -> tag.putString("state", "oxidized");
            }

            generatedLoot.add(loot);
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
