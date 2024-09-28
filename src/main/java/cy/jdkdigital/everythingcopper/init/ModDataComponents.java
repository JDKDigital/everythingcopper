package cy.jdkdigital.everythingcopper.init;

import com.mojang.serialization.Codec;
import cy.jdkdigital.everythingcopper.EverythingCopper;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModDataComponents
{
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, EverythingCopper.MODID);

    public static final Supplier<DataComponentType<String>> OXIDATION_STATE = DATA_COMPONENTS.register("oxidation_state", () -> DataComponentType.<String>builder().persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8).build());
    public static final Supplier<DataComponentType<Boolean>> WAXED = DATA_COMPONENTS.register("waxed", () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());

    public static void register() {
    }
}
