package cy.jdkdigital.everythingcopper.common.entity;

import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WeatheringCopper;

public interface IWeatheringEntity
{
    EntityDataAccessor<String> DATA_WEATHER_STATE = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.STRING);
    EntityDataAccessor<Boolean> DATA_WAXED = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.BOOLEAN);

    SynchedEntityData getSyncData();

    default void defineData() {
        getSyncData().define(DATA_WEATHER_STATE, WeatheringCopper.WeatherState.UNAFFECTED.name());
        getSyncData().define(DATA_WAXED, false);
    }

    default WeatheringCopper.WeatherState getAge() {
        return WeatheringCopper.WeatherState.valueOf(getSyncData().get(DATA_WEATHER_STATE));
    }

    default void setAge(WeatheringCopper.WeatherState age) {
        getSyncData().set(DATA_WEATHER_STATE, age.name());
    }

    default boolean isWaxed() {
        return getSyncData().get(DATA_WAXED);
    }

    default void setWaxed(boolean waxed) {
        getSyncData().set(DATA_WAXED, waxed);
    }

    default void saveWeatheredState(CompoundTag tag) {
        tag.putString("weathering", getSyncData().get(DATA_WEATHER_STATE));
        tag.putBoolean("waxed", getSyncData().get(DATA_WAXED));
    }

    default void loadWeatheredState(CompoundTag tag) {
        if (tag.contains("weathering") && !tag.getString("weathering").isEmpty()) {
            setAge(WeatheringCopper.WeatherState.valueOf(tag.getString("weathering")));
        }
        if (tag.contains("waxed")) {
            getSyncData().set(DATA_WAXED, tag.getBoolean("waxed"));
        }
    }

    default InteractionResult itemUse(Level level, ItemStack itemStack, Player player, BlockPos pos) {
        if (!isWaxed() && itemStack.getItem() instanceof HoneycombItem) {
            if (!level.isClientSide()) {
                setWaxed(true);
            }
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
            level.levelEvent(player, 3003, pos.above(), 0);
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else if (itemStack.getItem() instanceof AxeItem) {
            if (isWaxed()) {
                if (!level.isClientSide()) {
                    setWaxed(false);
                }
                level.playSound(player, pos.above(), SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3004, pos.above(), 0);
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else if (!getAge().equals(WeatheringCopper.WeatherState.UNAFFECTED)) {
                if (!level.isClientSide()) {
                    setAge(WeatheringUtils.prevState(getAge()));
                }
                level.playSound(player, pos.above(), SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3005, pos.above(), 0);
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }

    default float speedModifier() {
        float speedModifier = 1.0F;
        switch (getAge()) {
            case UNAFFECTED -> speedModifier = 1.1F;
            case WEATHERED -> speedModifier = 0.9F;
            case OXIDIZED -> speedModifier = 0.8F;
        }
        return speedModifier;
    }

    default boolean canAge() {
        return !isWaxed() && !getAge().equals(WeatheringCopper.WeatherState.OXIDIZED);
    }
}
