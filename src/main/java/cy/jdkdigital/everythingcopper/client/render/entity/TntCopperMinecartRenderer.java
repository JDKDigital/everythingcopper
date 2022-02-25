package cy.jdkdigital.everythingcopper.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import org.jetbrains.annotations.NotNull;

public class TntCopperMinecartRenderer extends TntMinecartRenderer implements ICopperMinecartRenderer
{
    public TntCopperMinecartRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull MinecartTNT minecart) {
        return textureLocation(minecart);
    }
}
