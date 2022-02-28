package cy.jdkdigital.everythingcopper.common.block;

import cy.jdkdigital.everythingcopper.util.WeatheringUtils;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface IWeatheringBlock extends WeatheringCopper
{
    static Optional<BlockState> getWaxed(BlockState blockState) {
        WeatheringUtils.buildWaxBlockMap();
        return Optional.ofNullable(WeatheringUtils.WAX_ON_BY_BLOCK.get().get(blockState.getBlock())).map((block) -> block.withPropertiesOf(blockState));
    }

    static Optional<BlockState> getUnwaxed(BlockState state) {
        WeatheringUtils.buildWaxBlockMap();
        return Optional.ofNullable(WeatheringUtils.WAX_OFF_BY_BLOCK.get().get(state.getBlock())).map((block) -> block.withPropertiesOf(state));
    }

    @Override
    default @NotNull Optional<BlockState> getNext(BlockState blockState) {
        return getNext(blockState.getBlock()).map((block) -> block.withPropertiesOf(blockState));
    }

    static Optional<Block> getNext(Block block) {
        WeatheringUtils.buildBlockMap();
        return Optional.ofNullable(WeatheringUtils.NEXT_BY_BLOCK.get().get(block));
    }

    static Optional<Block> getPrevious(Block block) {
        WeatheringUtils.buildBlockMap();
        return Optional.ofNullable(WeatheringUtils.PREVIOUS_BY_BLOCK.get().get(block));
    }

    static Optional<BlockState> getPrevious(BlockState state) {
        return getPrevious(state.getBlock()).map((block) -> block.withPropertiesOf(state));
    }

    default InteractionResult applyWax(BlockState blockState, Level level, BlockPos blockpos, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() instanceof HoneycombItem) {
            return IWeatheringBlock.getWaxed(blockState).map((waxedBlockState) -> {
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
                }
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                // Replace the opposite half of double blocks
                if (blockState.hasProperty(DoorBlock.HALF)) {
                    BlockPos replacedPosition = blockpos;
                    if (blockState.getValue(DoorBlock.HALF).equals(DoubleBlockHalf.UPPER)) {
                        replacedPosition = replacedPosition.below();
                    }
                    level.setBlock(replacedPosition, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                    level.setBlock(replacedPosition, waxedBlockState.setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER), Block.UPDATE_CLIENTS);
                    level.setBlock(replacedPosition.above(), waxedBlockState.setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL_IMMEDIATE);
                    level.levelEvent(player, 3003, replacedPosition, 0);
                    level.levelEvent(player, 3003, replacedPosition.above(), 0);
                } else {
                    level.setBlock(blockpos, waxedBlockState, Block.UPDATE_ALL_IMMEDIATE);
                    level.levelEvent(player, 3003, blockpos, 0);
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }).orElse(InteractionResult.PASS);
        }
        return InteractionResult.PASS;
    }
}
