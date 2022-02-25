package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.block.entity.CopperHopperBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, EverythingCopper.MODID);

    public static final RegistryObject<BlockEntityType<CopperHopperBlockEntity>> COPPER_HOPPER = BLOCK_ENTITIES.register("copper_hopper", () ->
            BlockEntityType.Builder.of(CopperHopperBlockEntity::new,
                    ModBlocks.COPPER_HOPPER.get(), ModBlocks.EXPOSED_COPPER_HOPPER.get(), ModBlocks.WEATHERED_COPPER_HOPPER.get(), ModBlocks.OXIDIZED_COPPER_HOPPER.get(),
                    ModBlocks.WAXED_COPPER_HOPPER.get(), ModBlocks.WAXED_EXPOSED_COPPER_HOPPER.get(), ModBlocks.WAXED_WEATHERED_COPPER_HOPPER.get(), ModBlocks.WAXED_OXIDIZED_COPPER_HOPPER.get()
            ).build(null)
    );
}
