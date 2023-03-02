package cy.jdkdigital.everythingcopper.common.container;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import cy.jdkdigital.everythingcopper.EverythingCopper;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class WeatheringStationScreen extends AbstractContainerScreen<WeatheringStationMenu>
{
    private static final ResourceLocation GUI_FLUID = new ResourceLocation(EverythingCopper.MODID, "textures/gui/container/weathering_station.png");

    public WeatheringStationScreen(WeatheringStationMenu screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(@Nonnull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(@Nonnull PoseStack poseStack, int mouseX, int mouseY) {
        this.font.draw(poseStack, this.title, 8.0F, 6.0F, 4210752);
        this.font.draw(poseStack, this.playerInventoryTitle, 8.0F, (float) (this.getYSize() - 96 + 2), 4210752);

        List<FormattedCharSequence> tooltipList = new ArrayList<>();
        this.menu.blockEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).ifPresent(handler -> {
            FluidStack fluidStack = handler.getFluidInTank(0);

            // Fluid level tooltip
            if (isHovering(26, 16, 16, 54, mouseX, mouseY)) {
                if (fluidStack.getAmount() > 0) {
                    tooltipList.add(new TranslatableComponent(EverythingCopper.MODID + ".screen.fluid_level", new TranslatableComponent(fluidStack.getTranslationKey()).getString(), fluidStack.getAmount() + "mB").getVisualOrderText());
                } else {
                    tooltipList.add(new TranslatableComponent(EverythingCopper.MODID + ".screen.empty").getVisualOrderText());
                }
            }
        });

        if (this.menu.isRunning()) {
            if (isHovering(81, 38, 13, 13, mouseX, mouseY)) {
                tooltipList.add(new TranslatableComponent(EverythingCopper.MODID + ".screen.fuel_time", Math.max(0, (200 - this.menu.blockEntity.progress))/20).getVisualOrderText());
            }
        }
        renderTooltip(poseStack, tooltipList, mouseX - getGuiLeft(), mouseY - getGuiTop());
    }

    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        RenderSystem.setShaderTexture(0, GUI_FLUID);
        blit(poseStack, getGuiLeft(), getGuiTop(), 0, 0, this.getXSize(), this.getYSize());

        // Burn progress
        if (this.menu.isRunning()) {
            int progress = this.menu.getLitProgress();
            this.blit(poseStack, getGuiLeft() + 81, getGuiTop() + 50 - progress, 176, 12 - progress, 14, progress);
        }

        // Draw fluid tank
        this.menu.blockEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).ifPresent(handler -> {
            FluidStack fluidStack = handler.getFluidInTank(0);
            if (fluidStack.getAmount() > 0) {
                FluidContainerUtil.renderFluidTank(poseStack, this, fluidStack, handler.getTankCapacity(0), 26, 16, 16, 54, 0);
            }
        });
    }
}
