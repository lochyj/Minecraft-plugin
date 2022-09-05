package wocplugin.wocplugin.handlers;

import org.bukkit.event.player.PlayerInteractEvent;

import wocplugin.wocplugin.Items.Items.ShortBow;
import wocplugin.wocplugin.Items.Items.WarpWand;

import java.util.List;
import java.util.Objects;

public class ItemEventHandler {
    public ItemEventHandler(PlayerInteractEvent event) {
        if (event.getItem() == null || event.getItem().getItemMeta() == null || event.getItem().getItemMeta().getLore() == null) return;
        List<String> itemLore = event.getItem().getItemMeta().getLore();
        // TODO: Optimise this
        if (itemLore.contains(WarpWand.identifier)) {
            WarpWand.function(event);
        } else if (itemLore.contains(ShortBow.identifier)) {
            ShortBow.function(event);
        }
    }
}
