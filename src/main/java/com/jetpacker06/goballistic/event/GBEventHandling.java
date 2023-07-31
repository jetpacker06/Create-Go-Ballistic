package com.jetpacker06.goballistic.event;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.content.gun.AbstractGunItem;
import com.jetpacker06.goballistic.packet.GBPacketHandling;
import com.jetpacker06.goballistic.packet.GunfireEmptyPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GoBallistic.MOD_ID)
@SuppressWarnings("unused")
public class GBEventHandling {
    @SubscribeEvent
    public static void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        System.out.println("Clicked nothing");
        Player pPlayer = event.getPlayer();

        GBPacketHandling.INSTANCE.sendToServer(new GunfireEmptyPacket(event.getItemStack()));

        activateGun(event.getPlayer(), event.getItemStack());
        System.out.println("\n\n");
    }

    @SubscribeEvent
    public static void onLeftClick(PlayerInteractEvent.LeftClickBlock event) {
        System.out.println("Clicked block");
        System.out.println("   Is server: " + (event.getPlayer().getLevel() instanceof ServerLevel));
        if (activateGun(event.getPlayer(), event.getItemStack()))
            event.setCanceled(true);
        System.out.println("\n\n");
    }

    @SubscribeEvent
    public static void onLeftClick(AttackEntityEvent event) {
        System.out.println("Clicked entity");
        System.out.println("   Is server: " + (event.getPlayer().getLevel() instanceof ServerLevel));
        if (activateGun(event.getPlayer(), event.getPlayer().getItemInHand(event.getPlayer().getUsedItemHand())))
            event.setCanceled(true);
        System.out.println("\n\n");
    }

    public static boolean activateGun(Player player, ItemStack stack) {
        if (!(stack.getItem() instanceof AbstractGunItem gunItem)) {
            return false;
        }
        gunItem.onLeftClick(player.getLevel(), player, stack);
        return true;
    }
}
