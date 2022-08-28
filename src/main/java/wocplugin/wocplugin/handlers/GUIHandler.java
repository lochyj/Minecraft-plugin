package wocplugin.wocplugin.handlers;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import wocplugin.wocplugin.GUI.EnderchestHandler;
import wocplugin.wocplugin.GUI.MineMerchant;
import wocplugin.wocplugin.GUI.cave_loot_chest;
import wocplugin.wocplugin.Util.ItemStackStorage;
import wocplugin.wocplugin.WOCPlugin;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class GUIHandler implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) throws IOException {
        Player player = (Player) event.getPlayer();

        if (event.getView().getTitle().equalsIgnoreCase("Ender Chest")) {
            event.setCancelled(true);
            new EnderchestHandler(player);
        }
        // Rest of the inventories
        else if (event.getView().getTitle().equalsIgnoreCase("cave_loot_chest")) {
            Object data = WOCPlugin.returnDB(player, WOCPlugin.playerData);
            event.setCancelled(true);
            // check if the time is up
            if (data != null) {
                // This checks if it has been a day 86400000 = 1 day in ms
                if (System.currentTimeMillis() - (long) data > 86400000) {
                    WOCPlugin.updateDB(
                            player,
                            "loot_chests",
                            event.getPlayer().getTargetBlock(null, 100).getLocation().toString(),
                            System.currentTimeMillis(),
                            WOCPlugin.playerData);
                    cave_loot_chest.openGUI(player);
                } else {
                    // 3600000 = 1 hour in ms
                    // 86400000 = 1 day in ms
                    player.sendMessage(ChatColor.RED + "You have to wait"+ ((86400000 - (System.currentTimeMillis() - (long) data)) / 3600000) + "hours before you can open this chest again");
                }
            } else {
                WOCPlugin.updateDB(
                        player,
                        "loot_chests",
                        event.getPlayer().getTargetBlock(null, 100).getLocation().toString(),
                        System.currentTimeMillis(),
                        WOCPlugin.playerData);
                cave_loot_chest.openGUI(player);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        MongoCollection collection = WOCPlugin.playerData;

        if (event.getView().getTitle().equalsIgnoreCase("Ender Chest")) {
            Bson updates = new Document("_id", event.getPlayer().getUniqueId().toString())
                    .append("inventories", new Document("ender_chest", ItemStackStorage.InventoryTo64(event.getPlayer().getInventory())));

            Bson query = new Document("_id", event.getPlayer().getUniqueId().toString());

            UpdateOptions options = new UpdateOptions().upsert(true);

            try {
                UpdateResult result = collection.updateOne(query,new Document("$set", updates), options);
            } catch (MongoException me) {
                Bukkit.getLogger().info("Unable to update due to error: " + me);
                event.getPlayer().sendMessage("This means the items you put in your Ender Chest could not be saved");
            }
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
