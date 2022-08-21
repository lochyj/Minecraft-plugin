package wocplugin.wocplugin.handlers;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import wocplugin.wocplugin.GUI.MineMerchant;
import wocplugin.wocplugin.GUI.cave_loot_chest;

import java.util.Objects;
import java.util.UUID;

public class GUIHandler implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        if (event.getView().getTitle().equalsIgnoreCase("cave_loot_chest")) {
            event.setCancelled(true);
            cave_loot_chest.openGUI(player);
        }
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        Player player = (Player) event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        if ((event.getClickedBlock().getType() == Material.ANVIL || event.getClickedBlock().getType() == Material.CHIPPED_ANVIL || event.getClickedBlock().getType() == Material.DAMAGED_ANVIL)) {
            event.setCancelled(true);
        }
        else if (event.getClickedBlock().getType() == Material.IRON_TRAPDOOR || event.getClickedBlock().getType() == Material.ACACIA_TRAPDOOR || event.getClickedBlock().getType() == Material.BIRCH_TRAPDOOR || event.getClickedBlock().getType() == Material.DARK_OAK_TRAPDOOR || event.getClickedBlock().getType() == Material.JUNGLE_TRAPDOOR || event.getClickedBlock().getType() == Material.SPRUCE_TRAPDOOR) {
            event.setCancelled(true);
        }
        else if (event.getClickedBlock().getType() == Material.ACACIA_BUTTON || event.getClickedBlock().getType() == Material.STONE_BUTTON || event.getClickedBlock().getType() == Material.BIRCH_BUTTON || event.getClickedBlock().getType() == Material.DARK_OAK_BUTTON || event.getClickedBlock().getType() == Material.JUNGLE_BUTTON || event.getClickedBlock().getType() == Material.OAK_BUTTON || event.getClickedBlock().getType() == Material.SPRUCE_BUTTON) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        if (event.getView().getTitle() == "Shop") {
            event.setCancelled(true);
            if (event.getRawSlot() == 12) {
                player.performCommand("give @p minecraft:gold_pickaxe 1");
                player.performCommand("scoreboard players remove @p Money 1");
            }  else if (event.getRawSlot() == 14) {
                player.performCommand("clear @p minecraft:emerald 1");
                player.performCommand("scoreboard players add @p Money 1");
            }
        } else if (event.getView().getTitle() == "Mine Merchant") {
            event.setCancelled(true);
            if (event.getSlot() <= MineMerchant.prices.length && event.getInventory() == event.getClickedInventory()) {
                player.getInventory().addItem(MineMerchant.Give.getItem(event.getSlot()));
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 7, 1);
                player.sendMessage(ChatColor.GOLD + "You bought an item for " + MineMerchant.prices[event.getSlot()] + " coins!");
                Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(UUID.randomUUID()),"scoreboard players remove "+ player.getName() +" Money "+ MineMerchant.prices[event.getSlot()]);
            }
        } else if (event.getView().getTitle() == "cave_loot_chest") {
            event.setCancelled(false);
        }
    }
}
