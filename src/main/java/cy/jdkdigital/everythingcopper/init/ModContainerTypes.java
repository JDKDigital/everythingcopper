package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import cy.jdkdigital.everythingcopper.common.container.WeatheringStationMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainerTypes
{
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, EverythingCopper.MODID);

    public static final RegistryObject<MenuType<WeatheringStationMenu>> WEATHERING_STATION = CONTAINER_TYPES.register("weathering_station", () ->
            IForgeMenuType.create(WeatheringStationMenu::new)
    );
}
