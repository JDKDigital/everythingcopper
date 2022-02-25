package cy.jdkdigital.everythingcopper.client.render.entity;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.entity.IWeatheringEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import org.jetbrains.annotations.NotNull;

public interface ICopperMinecartRenderer
{
    ResourceLocation TEXTURE_UNAFFECTED = new ResourceLocation(EverythingCopper.MODID, "textures/entity/copper_minecart/unaffected.png");
    ResourceLocation TEXTURE_EXPOSED = new ResourceLocation(EverythingCopper.MODID, "textures/entity/copper_minecart/exposed.png");
    ResourceLocation TEXTURE_WEATHERED = new ResourceLocation(EverythingCopper.MODID, "textures/entity/copper_minecart/weathered.png");
    ResourceLocation TEXTURE_OXIDIZED = new ResourceLocation(EverythingCopper.MODID, "textures/entity/copper_minecart/oxidized.png");

    default ResourceLocation textureLocation(@NotNull AbstractMinecart minecart) {
        if (minecart instanceof IWeatheringEntity weatherableMinecart) {
            return switch (weatherableMinecart.getAge()) {
                case EXPOSED -> TEXTURE_EXPOSED;
                case WEATHERED -> TEXTURE_WEATHERED;
                case OXIDIZED -> TEXTURE_OXIDIZED;
                default -> TEXTURE_UNAFFECTED;
            };
        }
        return TEXTURE_UNAFFECTED;
    }
}
