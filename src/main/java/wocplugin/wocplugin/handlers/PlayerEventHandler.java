package wocplugin.wocplugin.handlers;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import wocplugin.wocplugin.Util;

public class PlayerEventHandler implements Listener {
    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if ((event.getAction() == Action.LEFT_CLICK_AIR) ||
            (event.getAction() == Action.LEFT_CLICK_BLOCK||
            (event.getAction() == Action.RIGHT_CLICK_AIR) ||
            (event.getAction() == Action.RIGHT_CLICK_BLOCK))) {
            if (event.getItem() != null) {
                new ItemEventHandler(event);
            }
        }

        // TODO: Make this a check for op and not game mode to allow interaction
        if (!(player.getGameMode() == GameMode.CREATIVE)) {
            Util.interactCancel(event);
        }

    }

}
