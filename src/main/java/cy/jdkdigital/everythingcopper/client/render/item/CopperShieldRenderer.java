package cy.jdkdigital.everythingcopper.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import cy.jdkdigital.everythingcopper.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BannerPattern;

import java.util.List;

public class CopperShieldRenderer extends BlockEntityWithoutLevelRenderer
{
    public static final CopperShieldRenderer INSTANCE = new CopperShieldRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());

    private final EntityModelSet modelSet;
    private ShieldModel shieldModel;

    public CopperShieldRenderer(BlockEntityRenderDispatcher renderDispatcher, EntityModelSet modelSet) {
        super(renderDispatcher, modelSet);
        this.modelSet = modelSet;
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        this.shieldModel = new ShieldModel(this.modelSet.bakeLayer(ModelLayers.SHIELD));
    }

//    @Override
//    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack matrixStack, MultiBufferSource buffer, int packedLightIn, int packedUV) {
//        if (stack.is(ModItems.COPPER_SHIELD.get())) {
//            boolean flag = BlockItem.getBlockEntityData(stack) != null;
//            matrixStack.pushPose();
//            matrixStack.scale(1.0F, -1.0F, -1.0F);
//            Material material = flag ? ModelBakery.SHIELD_BASE : ModelBakery.NO_PATTERN_SHIELD;
//            VertexConsumer vertexconsumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(buffer, this.shieldModel.renderType(material.atlasLocation()), true, stack.hasFoil()));
//            this.shieldModel.handle().render(matrixStack, vertexconsumer, packedLightIn, packedUV, 1.0F, 1.0F, 1.0F, 1.0F);
//            if (flag) {
//                List<Pair<BannerPattern, DyeColor>> list = BannerBlockEntity.createPatterns(ShieldItem.getColor(stack), BannerBlockEntity.getItemPatterns(stack));
//                BannerRenderer.renderPatterns(matrixStack, buffer, packedLightIn, packedUV, this.shieldModel.plate(), material, false, list, stack.hasFoil());
//            } else {
//                this.shieldModel.plate().render(matrixStack, vertexconsumer, packedLightIn, packedUV, 1.0F, 1.0F, 1.0F, 1.0F);
//            }
//
//            matrixStack.popPose();
//        }
//    }
}
