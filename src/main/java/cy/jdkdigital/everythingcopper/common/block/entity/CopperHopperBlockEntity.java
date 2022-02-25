package cy.jdkdigital.everythingcopper.common.block.entity;

import cy.jdkdigital.everythingcopper.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CopperHopperBlockEntity extends HopperBlockEntity {
    public CopperHopperBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.COPPER_HOPPER.get();
    }
}
