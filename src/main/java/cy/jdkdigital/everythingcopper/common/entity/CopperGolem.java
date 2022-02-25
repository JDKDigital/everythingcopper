package cy.jdkdigital.everythingcopper.common.entity;

import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class CopperGolem extends IronGolem implements IWeatheringEntity
{
    public CopperGolem(EntityType<? extends IronGolem> entityType, Level level) {
        super(entityType, level);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        defineData();
    }

    @Override
    protected Component getTypeName() {
        String id = (isWaxed() ? "waxed_" : "") + getAge().name().toLowerCase() + "_copper_golem";
        return new TranslatableComponent(this.getType().getDescriptionId().replace("copper_golem", id));
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
                this.gameEvent(GameEvent.MOB_INTERACT, this.eyeBlockPosition());
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
        } else {
            return itemUse(level, itemStack, player, blockPosition());
        }
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
}
