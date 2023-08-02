package com.jetpacker06.goballistic.event;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.content.gun.AbstractGunItem;
import com.jetpacker06.goballistic.packet.GBPacketHandling;
import com.jetpacker06.goballistic.packet.ClickedEmptyPacket;
import net.minecraft.core.BlockPos;
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
        Player pPlayer = event.getPlayer();
        GBPacketHandling.INSTANCE.sendToServer(new ClickedEmptyPacket(event.getItemStack()));

        activateGun(event.getPlayer(), event.getItemStack());
    }

    @SubscribeEvent
    public static void onLeftClick(PlayerInteractEvent.LeftClickBlock event) {
        if (activateGun(event.getPlayer(), event.getItemStack()))
            event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onLeftClick(AttackEntityEvent event) {

        if (activateGun(event.getPlayer(), event.getPlayer().getItemInHand(event.getPlayer().getUsedItemHand())))
            event.setCanceled(true);
    }

    public static boolean activateGun(Player player, ItemStack stack) {
        if (!(stack.getItem() instanceof AbstractGunItem gunItem)) {
            return false;
        }
        gunItem.onLeftClick(player.getLevel(), player, stack);
        return true;
    }
}
