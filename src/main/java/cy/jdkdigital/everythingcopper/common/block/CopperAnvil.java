package cy.jdkdigital.everythingcopper.common.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import cy.jdkdigital.everythingcopper.init.ModBlocks;
import cy.jdkdigital.everythingcopper.inventory.CopperAnvilMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class CopperAnvil extends AnvilBlock implements IWeatheringBlock
{
    private static final Component CONTAINER_TITLE = new TranslatableComponent("container.repair");
    private final WeatherState weatherState;
    public static Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK;

    private static void buildAnvilMap() {
        if (NEXT_BY_BLOCK == null) {
            NEXT_BY_BLOCK = Suppliers.memoize(() ->
                            ImmutableBiMap.<Block, Block>builder().put(
                                            ModBlocks.COPPER_ANVIL.get(),
                                            ModBlocks.CHIPPED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.CHIPPED_COPPER_ANVIL.get(),
                                            ModBlocks.DAMAGED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.EXPOSED_COPPER_ANVIL.get(),
                                            ModBlocks.CHIPPED_EXPOSED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.CHIPPED_EXPOSED_COPPER_ANVIL.get(),
                                            ModBlocks.DAMAGED_EXPOSED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.WEATHERED_COPPER_ANVIL.get(),
                                            ModBlocks.CHIPPED_WEATHERED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.CHIPPED_WEATHERED_COPPER_ANVIL.get(),
                                            ModBlocks.DAMAGED_WEATHERED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.OXIDIZED_COPPER_ANVIL.get(),
                                            ModBlocks.CHIPPED_OXIDIZED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.CHIPPED_OXIDIZED_COPPER_ANVIL.get(),
                                            ModBlocks.DAMAGED_OXIDIZED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.WAXED_COPPER_ANVIL.get(),
                                            ModBlocks.WAXED_CHIPPED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.WAXED_CHIPPED_COPPER_ANVIL.get(),
                                            ModBlocks.WAXED_DAMAGED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.WAXED_EXPOSED_COPPER_ANVIL.get(),
                                            ModBlocks.WAXED_CHIPPED_EXPOSED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.WAXED_CHIPPED_EXPOSED_COPPER_ANVIL.get(),
                                            ModBlocks.WAXED_DAMAGED_EXPOSED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.WAXED_WEATHERED_COPPER_ANVIL.get(),
                                            ModBlocks.WAXED_CHIPPED_WEATHERED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.WAXED_CHIPPED_WEATHERED_COPPER_ANVIL.get(),
                                            ModBlocks.WAXED_DAMAGED_WEATHERED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.WAXED_OXIDIZED_COPPER_ANVIL.get(),
                                            ModBlocks.WAXED_CHIPPED_OXIDIZED_COPPER_ANVIL.get()
                                    ).put(
                                            ModBlocks.WAXED_CHIPPED_OXIDIZED_COPPER_ANVIL.get(),
                                            ModBlocks.WAXED_DAMAGED_OXIDIZED_COPPER_ANVIL.get()
                                    )
                                    .build()
            );
        }
    }

    public CopperAnvil(WeatherState state, Properties properties) {
        super(properties);
        weatherState = state;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, Random random) {
        this.onRandomTick(blockState, level, blockPos, random);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return IWeatheringBlock.getNext(blockState.getBlock()).isPresent() || super.isRandomlyTicking(blockState);
    }

    @Override
    public WeatherState getAge() {
        return this.weatherState;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockpos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        InteractionResult res = applyWax(blockState, level, blockpos, player, hand);
        return res.equals(InteractionResult.PASS) ? super.use(blockState, level, blockpos, player, hand, hitResult) : res;
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState blockState, Level level, BlockPos pos) {
        return new SimpleMenuProvider((id, inventory, player) -> {
            return new CopperAnvilMenu(id, inventory, ContainerLevelAccess.create(level, pos));
        }, CONTAINER_TITLE);
    }

    @Nullable
    public static BlockState damage(BlockState blockState) {
        buildAnvilMap();
        Optional<BlockState> next = Optional.ofNullable(NEXT_BY_BLOCK.get().get(blockState.getBlock())).map((block) -> block.withPropertiesOf(blockState));
        return next.orElse(null);
    }
}
