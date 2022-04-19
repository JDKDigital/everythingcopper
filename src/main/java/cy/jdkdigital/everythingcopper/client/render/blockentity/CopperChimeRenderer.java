package cy.jdkdigital.everythingcopper.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.block.entity.CopperChimeBlockEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.WeatheringCopper;

public class CopperChimeRenderer implements BlockEntityRenderer<CopperChimeBlockEntity> {
    public static final ModelLayerLocation COPPER_CHIME_MAIN_LAYER = new ModelLayerLocation(new ResourceLocation(EverythingCopper.MODID, "main"), "main");

    public static final Material COPPER_BLOCK_SOURCE = new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation("block/copper_block"));
    public static final Material EXPOSED_COPPER_BLOCK_SOURCE = new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation("block/exposed_copper"));
    public static final Material WEATHERED_COPPER_BLOCK_SOURCE = new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation("block/weathered_copper"));
    public static final Material OXIDIZED_COPPER_BLOCK_SOURCE = new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation("block/oxidized_copper"));

    private final ModelPart base;
    private final ModelPart chime1;
//    private final ModelPart chime2;
//    private final ModelPart chime3;
//    private final ModelPart chime4;
//    private final ModelPart chime5;

    public CopperChimeRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart modelpart = context.bakeLayer(COPPER_CHIME_MAIN_LAYER);
        this.base = modelpart.getChild("base");
        this.chime1 = this.base.getChild("1");
//        this.chime2 = modelpart.getChild("2");
//        this.chime3 = modelpart.getChild("3");
//        this.chime4 = modelpart.getChild("4");
//        this.chime5 = modelpart.getChild("5");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition base = partdefinition.addOrReplaceChild("base",
                CubeListBuilder.create().texOffs(0, 0)
                        .addBox(0, -4, 0, 2, 8, 2),
                PartPose.ZERO);

        base.addOrReplaceChild("1",
                CubeListBuilder.create().texOffs(1, 1)
                        .addBox(0, -4, 0, 2, 8, 2),
                PartPose.offsetAndRotation(12, 8, 10, 0,0, 0));

//        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("bell_body",
//                CubeListBuilder.create().texOffs(0, 0)
//                        .addBox(-3.0F, -6.0F, -3.0F, 6.0F, 7.0F, 6.0F),
//                PartPose.offset(8.0F, 12.0F, 8.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void render(CopperChimeBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay) {
        float f = (float)blockEntity.ticks + partialTicks;
        float f1 = 0.0F;
        float f2 = 0.0F;
        if (blockEntity.shaking) {
            float f3 = Mth.sin(f / (float)Math.PI) / (4.0F + f / 3.0F);
            if (blockEntity.clickDirection == Direction.NORTH) {
                f1 = -f3;
            } else if (blockEntity.clickDirection == Direction.SOUTH) {
                f1 = f3;
            } else if (blockEntity.clickDirection == Direction.EAST) {
                f2 = -f3;
            } else if (blockEntity.clickDirection == Direction.WEST) {
                f2 = f3;
            }
        }

        this.chime1.xRot = f1;
        this.chime1.zRot = f2;
        this.chime1.yRot = blockEntity.facing.equals(Direction.NORTH) ? 90 : 0;

        VertexConsumer vertexconsumer;
        switch (blockEntity.weatherState) {
            case EXPOSED -> vertexconsumer = EXPOSED_COPPER_BLOCK_SOURCE.buffer(bufferSource, RenderType::entitySolid);
            case WEATHERED -> vertexconsumer = WEATHERED_COPPER_BLOCK_SOURCE.buffer(bufferSource, RenderType::entitySolid);
            case OXIDIZED -> vertexconsumer = OXIDIZED_COPPER_BLOCK_SOURCE.buffer(bufferSource, RenderType::entitySolid);
            default -> vertexconsumer = COPPER_BLOCK_SOURCE.buffer(bufferSource, RenderType::entitySolid);
        }

        this.chime1.render(poseStack, vertexconsumer, light, overlay);
    }
}
