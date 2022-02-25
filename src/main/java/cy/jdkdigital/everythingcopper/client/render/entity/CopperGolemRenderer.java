package cy.jdkdigital.everythingcopper.client.render.entity;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.entity.CopperGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IronGolemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.IronGolem;
import org.jetbrains.annotations.NotNull;

public class CopperGolemRenderer extends IronGolemRenderer
{
    private static final ResourceLocation TEXTURE_UNAFFECTED = new ResourceLocation(EverythingCopper.MODID, "textures/entity/copper_golem/unaffected.png");
    private static final ResourceLocation TEXTURE_EXPOSED = new ResourceLocation(EverythingCopper.MODID, "textures/entity/copper_golem/exposed.png");
    private static final ResourceLocation TEXTURE_WEATHERED = new ResourceLocation(EverythingCopper.MODID, "textures/entity/copper_golem/weathered.png");
    private static final ResourceLocation TEXTURE_OXIDIZED = new ResourceLocation(EverythingCopper.MODID, "textures/entity/copper_golem/oxidized.png");

    public CopperGolemRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull IronGolem golem) {
        if (golem instanceof CopperGolem copperGolem) {
            return switch (copperGolem.getAge()) {
                case EXPOSED -> TEXTURE_EXPOSED;
                case WEATHERED -> TEXTURE_WEATHERED;
                case OXIDIZED -> TEXTURE_OXIDIZED;
                default -> TEXTURE_UNAFFECTED;
            };
        }
        return super.getTextureLocation(golem);
    }
}
