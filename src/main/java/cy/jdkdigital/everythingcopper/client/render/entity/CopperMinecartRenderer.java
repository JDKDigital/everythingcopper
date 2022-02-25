package cy.jdkdigital.everythingcopper.client.render.entity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import org.jetbrains.annotations.NotNull;

public class CopperMinecartRenderer extends MinecartRenderer<AbstractMinecart> implements ICopperMinecartRenderer
{
    public CopperMinecartRenderer(EntityRendererProvider.Context context, ModelLayerLocation modelLayerLocation) {
        super(context, modelLayerLocation);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull AbstractMinecart minecart) {
        return textureLocation(minecart);
    }
}
