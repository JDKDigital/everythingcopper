package cy.jdkdigital.everythingcopper.common.block;

import cy.jdkdigital.everythingcopper.common.block.entity.CopperHopperBlockEntity;
import cy.jdkdigital.everythingcopper.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class CopperHopper extends HopperBlock implements IWeatheringBlock
{
    private final WeatherState weatherState;

    public CopperHopper(WeatherState state, Properties properties) {
        super(properties);
        weatherState = state;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        this.changeOverTime(blockState, level, blockPos, random);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return WeatheringCopper.getNext(blockState.getBlock()).isPresent() || super.isRandomlyTicking(blockState);
    }

    @Override
    public WeatherState getAge() {
        return this.weatherState;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new CopperHopperBlockEntity(pos, blockState);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> entityType) {
        return level.isClientSide ? null : createTickerHelper(entityType, ModBlockEntities.COPPER_HOPPER.get(), HopperBlockEntity::pushItemsTick);
    }

    @Override
    public void changeOverTime(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        float f = 0.05688889F;
        if (pRandom.nextFloat() < 0.05688889F) {
            this.getNextState(pState, pLevel, pPos, pRandom).ifPresent(newState -> {
                BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
                if (blockEntity instanceof CopperHopperBlockEntity hopperBlockEntity) {
                    CompoundTag tag = hopperBlockEntity.saveWithoutMetadata(pLevel.registryAccess());
                    hopperBlockEntity.clearContent();

                    pLevel.setBlockAndUpdate(pPos, newState);
                    pLevel.getBlockEntity(pPos).loadWithComponents(tag, pLevel.registryAccess());
                }
            });
        }
    }
}
