package wocplugin.wocplugin.handlers;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import wocplugin.wocplugin.GUI.MineMerchant;
import wocplugin.wocplugin.GUI.cave_loot_chest;
import wocplugin.wocplugin.WOCPlugin;

import java.util.Objects;
import java.util.UUID;

public class GUIHandler implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        // Object data = WOCPlugin.returnDB(player, WOCPlugin.playerData);
        if (event.getView().getTitle().equalsIgnoreCase("cave_loot_chest")) {
            event.setCancelled(true);
            cave_loot_chest.openGUI(player);
            WOCPlugin.updateDB(
                    player,
                    "loot_chests",
                    event.getPlayer().getTargetBlock(null, 100).getLocation().toString(),
                    System.currentTimeMillis(),
                    WOCPlugin.playerData);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        if (event.getView().getTitle().equals("Shop")) {
            event.setCancelled(true);
            if (event.getRawSlot() == 12) {
                player.performCommand("give @p minecraft:gold_pickaxe 1");
                player.performCommand("scoreboard players remove @p Money 1");
            }  else if (event.getRawSlot() == 14) {
                player.performCommand("clear @p minecraft:emerald 1");
                player.performCommand("scoreboard players add @p Money 1");
            }
        } else if (event.getView().getTitle().equals("Mine Merchant")) {
            event.setCancelled(true);
            if (event.getSlot() <= MineMerchant.prices.length && event.getInventory() == event.getClickedInventory()) {
                player.getInventory().addItem(MineMerchant.Give.getItem(event.getSlot()));
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 7, 1);
                player.sendMessage(ChatColor.GOLD + "You bought an item for " + MineMerchant.prices[event.getSlot()] + " coins!");
                Bukkit.getServer().dispatchCommand(Objects.requireNonNull(Bukkit.getPlayer(UUID.randomUUID())),"scoreboard players remove "+ player.getName() +" Money "+ MineMerchant.prices[event.getSlot()]);
            }
        }
    }
}
