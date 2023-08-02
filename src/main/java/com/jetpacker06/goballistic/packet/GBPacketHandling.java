package com.jetpacker06.goballistic.packet;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.event.GBEventHandling;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class GBPacketHandling {
    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(GoBallistic.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void init() {
        int id = 0;
        INSTANCE.registerMessage(
                ++id,
                ClickedEmptyPacket.class,
                (packet, buf) -> buf.writeItemStack(packet.gunStack(), true),
                buf -> new ClickedEmptyPacket(buf.readItem()),
                (packet, ctx) -> ctx.get().enqueueWork(() -> GBEventHandling.activateGun(ctx.get().getSender(), packet.gunStack()))
        );
    }
}
