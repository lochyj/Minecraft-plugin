package wocplugin.wocplugin.Util;

import com.mongodb.client.MongoCollection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import wocplugin.wocplugin.WOCPlugin;

public class UtilManager {
    public static void SaveInventory(Inventory inventory, Player player, MongoCollection collection, String location) {
        // store in the database
        WOCPlugin.updateDB(
                player,
                "inventories",
                location,
                ItemStackStorage.InventoryTo64(inventory),
                collection
        );
    }
}
