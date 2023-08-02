package com.jetpacker06.goballistic.register;

import com.jetpacker06.goballistic.GoBallistic;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GBSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, GoBallistic.MOD_ID);

    public static final RegistryObject<SoundEvent> RIFLE_SHOT = register("rifle_shot");
    public static final RegistryObject<SoundEvent> SHOTGUN_SHOT = register("shotgun_shot");
    public static final RegistryObject<SoundEvent> FLINTLOCK_SHOT = register("flintlock_shot");
    public static final RegistryObject<SoundEvent> REVOLVER_SHOT = register("revolver_shot");

    public static RegistryObject<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(GoBallistic.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
