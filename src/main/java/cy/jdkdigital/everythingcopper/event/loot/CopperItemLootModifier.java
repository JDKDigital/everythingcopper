package cy.jdkdigital.everythingcopper.event.loot;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import cy.jdkdigital.everythingcopper.init.ModDataComponents;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class CopperItemLootModifier extends LootModifier
{
    public static final Supplier<MapCodec<CopperItemLootModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.mapCodec(inst -> codecStart(inst)
            .and(ItemStack.CODEC.fieldOf("addition").forGetter(m -> m.addition))
            .apply(inst, CopperItemLootModifier::new)
    ));

    private final ItemStack addition;

    protected CopperItemLootModifier(LootItemCondition[] conditionsIn, ItemStack addition) {
        super(conditionsIn);
        this.addition = addition;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (context.getRandom().nextBoolean()) {
            ItemStack loot = addition.copy();

            // Randomized tarnish level
            String state = switch (context.getRandom().nextInt(3)) {
                case 0 -> WeatheringCopper.WeatherState.EXPOSED.getSerializedName();
                case 1 -> WeatheringCopper.WeatherState.WEATHERED.getSerializedName();
                default -> WeatheringCopper.WeatherState.OXIDIZED.getSerializedName();
            };

            loot.set(ModDataComponents.OXIDATION_STATE, state);

            generatedLoot.add(loot);
        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
