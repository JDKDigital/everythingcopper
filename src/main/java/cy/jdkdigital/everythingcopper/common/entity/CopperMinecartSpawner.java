package cy.jdkdigital.everythingcopper.common.entity;

import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.MinecartSpawner;
import net.minecraft.world.level.Level;

public class CopperMinecartSpawner extends MinecartSpawner implements IWeatheringEntity
{
    public CopperMinecartSpawner(EntityType<? extends MinecartSpawner> type, Level level) {
        super(type, level);
    }

    public CopperMinecartSpawner(Level level, double x, double y, double z) {
        super(level, x, y, z);
    }

    @Override
    public SynchedEntityData getSyncData() {
        return super.getEntityData();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        defineData();
    }

    @Override
    protected Component getTypeName() {
        String id = (isWaxed() ? "waxed_" : "") + getAge().name().toLowerCase() + "_spawner_copper_minecart";
        return Component.translatable(this.getType().getDescriptionId().replace("spawner_copper_minecart", id));
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        InteractionResult res = itemUse(this.level(), player.getItemInHand(hand), player, blockPosition());
        if (res.equals(InteractionResult.PASS)) {
            return super.interact(player, hand);
        }
        return res;
    }

    @Override
    public void tick() {
        if (tickCount%369 == 0 && canAge() && this.level().random.nextFloat() < 0.005688889F) {
            setAge(WeatheringUtils.nextState(getAge()));
        }
        super.tick();
    }

    @Override
    protected float getBlockSpeedFactor() {
        return super.getBlockSpeedFactor() * speedModifier();
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
}
