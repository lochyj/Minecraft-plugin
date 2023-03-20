package wocplugin.wocplugin.handlers;

import org.bukkit.event.player.PlayerInteractEvent;
import wocplugin.wocplugin.Items.Items.GardenHoe;

import java.util.List;

public class ItemEventHandler {
    public ItemEventHandler(PlayerInteractEvent event) {
        if (event.getItem() == null || event.getItem().getItemMeta() == null || event.getItem().getItemMeta().getLore() == null) return;
        List<String> itemLore = event.getItem().getItemMeta().getLore();

        if (itemLore.contains(GardenHoe.identifier)) {
            GardenHoe.function(event);
        }
    }
}
