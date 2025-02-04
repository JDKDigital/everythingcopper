package cy.jdkdigital.everythingcopper.util;

import net.minecraft.world.level.block.WeatheringCopper;

public class WeatheringUtils
{
    public static String nextState(String state) {
        return switch (state) {
            case "unaffected" -> "exposed";
            case "exposed" -> "weathered";
            default -> "oxidized";
        };
    }

    public static String prevState(String state) {
        return switch (state) {
            case "oxidized" -> "weathered";
            case "weathered" -> "exposed";
            default -> "unaffected";
        };
    }

    public static WeatheringCopper.WeatherState nextState(WeatheringCopper.WeatherState state) {
        return switch (state) {
            case UNAFFECTED -> WeatheringCopper.WeatherState.EXPOSED;
            case EXPOSED -> WeatheringCopper.WeatherState.WEATHERED;
            default -> WeatheringCopper.WeatherState.OXIDIZED;
        };
    }

    public static WeatheringCopper.WeatherState prevState(WeatheringCopper.WeatherState state) {
        return switch (state) {
            case OXIDIZED -> WeatheringCopper.WeatherState.WEATHERED;
            case WEATHERED -> WeatheringCopper.WeatherState.EXPOSED;
            default -> WeatheringCopper.WeatherState.UNAFFECTED;
        };
    }
}
