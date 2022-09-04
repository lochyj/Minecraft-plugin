package wocplugin.wocplugin.handlers;

import org.bukkit.event.player.PlayerInteractEvent;

import wocplugin.wocplugin.Items.Items.ShortBow;
import wocplugin.wocplugin.Items.Items.WarpWand;

public class ItemEventHandler {
    public ItemEventHandler(PlayerInteractEvent event) {
        if (event.getItem().getItemMeta().getLore().contains(WarpWand.identifier)) {
            WarpWand.function(event);
        } else if (event.getItem().getItemMeta().getLore().contains(ShortBow.identifier)) {
            ShortBow.function(event);
        }
    }
}
