package cy.jdkdigital.everythingcopper.common.entity;

import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockMaterialPredicate;
import net.minecraft.world.level.material.Material;

public class CopperGolem extends IronGolem implements IWeatheringEntity
{
    private static BlockPattern patternBase;
    private static BlockPattern patternFull;

    public CopperGolem(EntityType<? extends IronGolem> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public SynchedEntityData getSyncData() {
        return super.getEntityData();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        getSyncData().define(DATA_WEATHER_STATE, WeatheringCopper.WeatherState.UNAFFECTED.name());
        getSyncData().define(DATA_WAXED, false);
    }

    @Override
    protected Component getTypeName() {
        String id = (isWaxed() ? "waxed_" : "") + getAge().name().toLowerCase() + "_copper_golem";
        return Component.translatable(this.getType().getDescriptionId().replace("copper_golem", id));
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.COPPER_INGOT)) {
            float f = this.getHealth();
            this.heal(25.0F);
            if (this.getHealth() == f) {
                return InteractionResult.PASS;
            } else {
                float f1 = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;
                this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, f1);
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void tick() {
        if (tickCount%369 == 0 && canAge() && level.random.nextFloat() < 0.005688889F) {
            setAge(WeatheringUtils.nextState(getAge()));
        }
        super.tick();
    }

    @Override
    public float getSpeed() {
        return super.getSpeed() * speedModifier();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        saveWeatheredState(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        loadWeatheredState(tag);
    }

    public static boolean canSpawnGolem(LevelReader level, BlockPos pos) {
        return getOrCreateCopperGolemBase().find(level, pos) != null;
    }

    private static BlockPattern getOrCreateCopperGolemBase() {
        if (patternBase == null) {
            patternBase = BlockPatternBuilder.start().aisle("~ ~", "###", "~#~").where('#', BlockInWorld.hasState((state) -> {
                return state != null && (state.is(Blocks.COPPER_BLOCK) || state.is(Blocks.EXPOSED_COPPER) || state.is(Blocks.WEATHERED_COPPER) || state.is(Blocks.OXIDIZED_COPPER) || state.is(Blocks.WAXED_COPPER_BLOCK) || state.is(Blocks.WAXED_EXPOSED_COPPER) || state.is(Blocks.WAXED_WEATHERED_COPPER) || state.is(Blocks.WAXED_OXIDIZED_COPPER));
            })).where('~', BlockInWorld.hasState(BlockMaterialPredicate.forMaterial(Material.AIR))).build();
        }

        return patternBase;
    }

    public static BlockPattern getOrCreateCopperGolemFull() {
        if (patternFull == null) {
            patternFull = BlockPatternBuilder.start().aisle("~^~", "###", "~#~").where('^', BlockInWorld.hasState((state) -> {
                return state != null && (state.is(Blocks.CARVED_PUMPKIN) || state.is(Blocks.JACK_O_LANTERN));
            })).where('#', BlockInWorld.hasState((state) -> {
                return state != null && (state.is(Blocks.COPPER_BLOCK) || state.is(Blocks.EXPOSED_COPPER) || state.is(Blocks.WEATHERED_COPPER) || state.is(Blocks.OXIDIZED_COPPER) || state.is(Blocks.WAXED_COPPER_BLOCK) || state.is(Blocks.WAXED_EXPOSED_COPPER) || state.is(Blocks.WAXED_WEATHERED_COPPER) || state.is(Blocks.WAXED_OXIDIZED_COPPER));
            })).where('~', BlockInWorld.hasState(BlockMaterialPredicate.forMaterial(Material.AIR))).build();
        }
        return patternFull;
    }
}
