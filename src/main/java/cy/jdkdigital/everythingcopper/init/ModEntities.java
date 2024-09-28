package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.entity.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, EverythingCopper.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<CopperGolem>> COPPER_GOLEM = createEntity("copper_golem", EntityType.Builder.of(CopperGolem::new, MobCategory.MISC).sized(1.4F, 2.7F).clientTrackingRange(10));

    public static final DeferredHolder<EntityType<?>, EntityType<CopperMinecart>> COPPER_MINECART = createEntity("copper_minecart", EntityType.Builder.<CopperMinecart>of(CopperMinecart::new, MobCategory.MISC).sized(0.98F, 0.7F).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<CopperMinecartChest>> CHEST_COPPER_MINECART = createEntity("chest_copper_minecart", EntityType.Builder.<CopperMinecartChest>of(CopperMinecartChest::new, MobCategory.MISC).sized(0.98F, 0.7F).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<CopperMinecartCommandBlock>> COMMAND_BLOCK_COPPER_MINECART = createEntity("command_block_copper_minecart", EntityType.Builder.<CopperMinecartCommandBlock>of(CopperMinecartCommandBlock::new, MobCategory.MISC).sized(0.98F, 0.7F).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<CopperMinecartFurnace>> FURNACE_COPPER_MINECART = createEntity("furnace_copper_minecart", EntityType.Builder.<CopperMinecartFurnace>of(CopperMinecartFurnace::new, MobCategory.MISC).sized(0.98F, 0.7F).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<CopperMinecartHopper>> HOPPER_COPPER_MINECART = createEntity("hopper_copper_minecart", EntityType.Builder.<CopperMinecartHopper>of(CopperMinecartHopper::new, MobCategory.MISC).sized(0.98F, 0.7F).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<CopperMinecartSpawner>> SPAWNER_COPPER_MINECART = createEntity("spawner_copper_minecart", EntityType.Builder.<CopperMinecartSpawner>of(CopperMinecartSpawner::new, MobCategory.MISC).sized(0.98F, 0.7F).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<CopperMinecartTNT>> TNT_COPPER_MINECART = createEntity("tnt_copper_minecart", EntityType.Builder.<CopperMinecartTNT>of(CopperMinecartTNT::new, MobCategory.MISC).sized(0.98F, 0.7F).clientTrackingRange(8));

    public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> createEntity(String name, EntityType.Builder<E> builder) {
        DeferredHolder<EntityType<?>, EntityType<E>> entity = ENTITIES.register(name, () -> builder.build(EverythingCopper.MODID + ":" + name));

        return entity;
    }
}
