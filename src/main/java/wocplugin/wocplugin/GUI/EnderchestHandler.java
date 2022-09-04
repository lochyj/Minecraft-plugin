package wocplugin.wocplugin.GUI;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import wocplugin.wocplugin.Util.ItemStackStorage;
import wocplugin.wocplugin.Util.Util;
import wocplugin.wocplugin.WOCPlugin;

import java.io.IOException;
import java.util.Arrays;

public class EnderchestHandler implements Listener {

    public EnderchestHandler(Player player) throws IOException {
        openGUI(player);
    }

    public static void openGUI(Player player) throws IOException {
        Inventory inventory;

        MongoCollection<Document> collection = WOCPlugin.playerData;

        Document playerData = collection.find(
                new org.bson.Document("_id", player.getUniqueId().toString())
        ).first();

        if (playerData == null) {
            player.sendMessage(ChatColor.RED + "There was an error");
            return;
        }

        Document inventories = (Document) playerData.get("inventories");

        Object arryObj = inventories.get("ender_chest");

        if (arryObj == null) {
            player.sendMessage(ChatColor.RED + "There was an error, Enderchest is null");
            player.sendMessage(ChatColor.RED + inventories.toString());

            Util.updateDB(
                    player,
                    "inventories",
                    "ender_chest",
                    ItemStackStorage.InventoryTo64(Bukkit.createInventory(player, 27, "Ender Chest")),
                    WOCPlugin.playerData
            );

            return;
        }

        String arry = arryObj.toString();
        inventory = ItemStackStorage.InventoryFrom64(arry, "Ender Chest", player);
        player.sendMessage(Arrays.toString(inventory.getContents()));
        player.openInventory(inventory);

    }

}
