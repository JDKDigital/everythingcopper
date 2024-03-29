package cy.jdkdigital.everythingcopper.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import cy.jdkdigital.everythingcopper.common.block.entity.WeatheringStationBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class WeatheringStationRenderer implements BlockEntityRenderer<WeatheringStationBlockEntity> {

    public WeatheringStationRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(WeatheringStationBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay) {
        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemHandler -> {
            ItemStack input = itemHandler.getStackInSlot(WeatheringStationBlockEntity.SLOT_INPUT);
            ItemStack output = itemHandler.getStackInSlot(WeatheringStationBlockEntity.SLOT_OUTPUT);

            if (!input.isEmpty()) {
                renderItem(blockEntity.getLevel(), input, true, !output.isEmpty(), poseStack, bufferSource, light, overlay);
            }
            if (!output.isEmpty()) {
                renderItem(blockEntity.getLevel(), output, false, !input.isEmpty(), poseStack, bufferSource, light, overlay);
            }
        });
    }

    private void renderItem(Level level, ItemStack output, boolean isInput, boolean multiItems, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay) {
        poseStack.pushPose();
        if (output.getItem() instanceof BlockItem blockItem) {
            if (multiItems) {
                poseStack.scale(0.5F, 0.5F, 0.5F);
                if (isInput) {
                    poseStack.translate(0.3D, 0.6D, 0.3D);
                } else {
                    poseStack.translate(0.75D, 0.7D, 0.75D);
                }
            } else {
                poseStack.scale(0.625F, 0.625F, 0.625F);
                poseStack.translate(0.3D, 0.4D, 0.3D);
            }
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(blockItem.getBlock().defaultBlockState(), poseStack, bufferSource, light, overlay, ModelData.EMPTY, null);
        } else {
            if (multiItems) {
                poseStack.translate(0.5D, 0.6375D, 0.5D);
                if (isInput) {
                    poseStack.mulPose(Axis.YP.rotationDegrees(90F));
                }
                poseStack.scale(0.7F, 0.7F, 0.7F);
            } else {
                poseStack.translate(0.5D, 0.6375D, 0.5D);
                poseStack.scale(0.7F, 0.7F, 0.7F);
            }
            Minecraft.getInstance().getItemRenderer().renderStatic(output, ItemDisplayContext.FIXED, light, overlay, poseStack, bufferSource, level, 0);
        }
        poseStack.popPose();
    }
}
