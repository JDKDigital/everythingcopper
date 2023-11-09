package cy.jdkdigital.everythingcopper.common.block;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.List;

public class CopperLamp extends RedstoneLampBlock implements IWeatheringBlock
{
    public static final BooleanProperty FULL_BRIGHT = BooleanProperty.create("full_bright");

    private final WeatherState weatherState;

    public CopperLamp(WeatherState state, Properties properties) {
        super(properties);
        weatherState = state;
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false).setValue(BlockStateProperties.POWERED, false).setValue(FULL_BRIGHT, false));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean hasSignal = context.getLevel().hasNeighborSignal(context.getClickedPos());
        return this.defaultBlockState().setValue(LIT, hasSignal).setValue(BlockStateProperties.POWERED, hasSignal);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        this.onRandomTick(blockState, level, blockPos, random);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return IWeatheringBlock.getNext(blockState.getBlock()).isPresent();
    }

    @Override
    public WeatherState getAge() {
        return this.weatherState;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return state.getValue(LIT) ? 15 : 0;
    }

    @Override
    public void tick(BlockState blockState, ServerLevel level, BlockPos blockpos, RandomSource rand) {
        level.setBlock(blockpos, blockState.setValue(LIT, !blockState.getValue(LIT)), Block.UPDATE_ALL);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockpos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (player.getItemInHand(hand).is(Items.REDSTONE_TORCH)) {
            level.setBlock(blockpos, blockState.setValue(LIT, !blockState.getValue(LIT)), Block.UPDATE_ALL);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        if (player.getItemInHand(hand).is(Tags.Items.DUSTS_GLOWSTONE)) {
            level.setBlock(blockpos, blockState.setValue(FULL_BRIGHT, true), Block.UPDATE_ALL);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        InteractionResult res = applyWax(blockState, level, blockpos, player, hand);
        return res.equals(InteractionResult.PASS) ? super.use(blockState, level, blockpos, player, hand, hitResult) : res;
    }

    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos blockpos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            boolean hasSignal = level.hasNeighborSignal(blockpos);
            boolean hasSignalFrom = level.hasSignal(fromPos, getDirection(blockpos, fromPos));
            boolean hadSignalBefore = blockState.getValue(BlockStateProperties.POWERED);

            EverythingCopper.LOGGER.info("neighborChanged hasSignal:" + hasSignal + " hasFromDir:" + hasSignalFrom + " hadSignalBefore:" + hadSignalBefore);
            level.setBlockAndUpdate(blockpos, blockState.setValue(BlockStateProperties.POWERED, hasSignal));
            if (!hadSignalBefore && hasSignalFrom) {
                level.scheduleTick(blockpos, this, 4);
            }
        }
    }

    private Direction getDirection(BlockPos pos1, BlockPos pos2) {
        if (pos1.getY() < pos2.getY()) {
            return Direction.UP;
        }
        if (pos1.getY() > pos2.getY()) {
            return Direction.DOWN;
        }
        if (pos1.getX() < pos2.getX()) {
            return Direction.SOUTH;
        }
        if (pos1.getX() > pos2.getX()) {
            return Direction.WEST;
        }
        if (pos1.getZ() < pos2.getZ()) {
            return Direction.EAST;
        }
        // pos1.getZ() > pos2.getZ()
        return Direction.NORTH;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(LIT).add(FULL_BRIGHT).add(BlockStateProperties.POWERED);
    }

    public static int lightLevel(BlockState state, int lightLevel) {
        return state.getValue(LIT) ? (state.getValue(FULL_BRIGHT) ? 15 : lightLevel) : 0;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> toolTips, TooltipFlag extended) {
        super.appendHoverText(stack, level, toolTips, extended);
        toolTips.add(Component.translatable("everythingcopper.bulb.tip1").withStyle(ChatFormatting.GOLD));
        toolTips.add(Component.translatable("everythingcopper.bulb.tip2").withStyle(ChatFormatting.GOLD));
    }
}
