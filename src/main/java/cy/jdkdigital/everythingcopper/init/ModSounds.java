package cy.jdkdigital.everythingcopper.init;

import cy.jdkdigital.everythingcopper.EverythingCopper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EverythingCopper.MODID);

    public static final RegistryObject<SoundEvent> CHIME = SOUNDS.register("block.copper_chime.chime", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(EverythingCopper.MODID, "block.copper_chime.chime")));
}
