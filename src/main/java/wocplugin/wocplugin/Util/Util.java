package wocplugin.wocplugin.Util;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import wocplugin.wocplugin.WOCPlugin;

import java.util.Objects;

public class Util {
    public static void SaveInventory(Inventory inventory, Player player, MongoCollection collection, String location) {
        // store in the database
        updateDB(
                player,
                "inventories",
                location,
                ItemStackStorage.InventoryTo64(inventory),
                collection
        );
    }

    public static Object returnDB(Player player, MongoCollection collection) {
        Bson query;
        query = new Document("_id", player.getUniqueId().toString());
        return collection.find(query).first();
    }

    public static boolean updateDB(Player player, String nest, String key, Object value, MongoCollection collection) {
        Bson updates;
        if (nest != null) {
            updates = new Document("_id", player.getUniqueId().toString())
                    .append(nest, new Document(key, value));
        } else {
            updates = new Document("_id", player.getUniqueId().toString())
                    .append(key, value);
        }


        Bson query = new Document("_id", player.getUniqueId().toString());

        UpdateOptions options = new UpdateOptions().upsert(true);

        boolean give;

        try {
            UpdateResult result = collection.updateOne(query,new Document("$set", updates), options);
            return true;
        } catch (MongoException me) {
            Bukkit.getLogger().info("Unable to update due to an error: " + me);
            return false;
        }
    }
    public static void interactCancel(PlayerInteractEvent event) {
        if (Objects.requireNonNull(event.getClickedBlock()).getType() != Material.AIR) {
            if (event.getClickedBlock().getType() == Material.ANVIL || event.getClickedBlock().getType() == Material.CHIPPED_ANVIL || event.getClickedBlock().getType() == Material.DAMAGED_ANVIL) {
                event.setCancelled(true);
            }
            else if (event.getClickedBlock().getType() == Material.IRON_TRAPDOOR || event.getClickedBlock().getType() == Material.ACACIA_TRAPDOOR || event.getClickedBlock().getType() == Material.BIRCH_TRAPDOOR || event.getClickedBlock().getType() == Material.DARK_OAK_TRAPDOOR || event.getClickedBlock().getType() == Material.JUNGLE_TRAPDOOR || event.getClickedBlock().getType() == Material.SPRUCE_TRAPDOOR) {
                event.setCancelled(true);
            }
            else if (event.getClickedBlock().getType() == Material.ACACIA_BUTTON || event.getClickedBlock().getType() == Material.STONE_BUTTON || event.getClickedBlock().getType() == Material.BIRCH_BUTTON || event.getClickedBlock().getType() == Material.DARK_OAK_BUTTON || event.getClickedBlock().getType() == Material.JUNGLE_BUTTON || event.getClickedBlock().getType() == Material.OAK_BUTTON || event.getClickedBlock().getType() == Material.SPRUCE_BUTTON) {
                event.setCancelled(true);
            }
        }
    }
    public static boolean playerExists(MongoDatabase db, Document playerName) {
        FindIterable<Document> iterable = db.getCollection("playerData")
                .find(playerName);
        return iterable.first() != null;
    }
    public  static  void playerDBInit(Player player, MongoCollection collection) {
        Document playerName = new Document("_id", player.getUniqueId().toString());
        Document playerData = new Document(playerName)
                .append("user_name", player.getName())
                .append("coins", 0)
                .append("kills", 0)
                .append("deaths", 0)
                .append("first_join", System.currentTimeMillis())
                .append("last_join", System.currentTimeMillis())
                .append("play_time", 0)
                .append("inventories", new Document("inventory", ItemStackStorage.InventoryTo64(player.getInventory())))
                .append("loot_chests", new Document());

        try {
            collection.insertOne(playerData);
            player.sendMessage("Welcome " + player.getName() + "!");
        } catch (MongoException e) {
            Bukkit.getLogger().info("Unable to update due to an error: " + e);
            player.sendMessage("Welcome " + player.getName() + "! " +
                    "Just to let you know, An internal error has occurred, meaning things might not work as expected.");
        }
    }
    public  static  void  playerDBUpdate(Player player, MongoCollection collection) {
        Bson updates = new Document("_id", player.getUniqueId().toString())
                .append("last_join", System.currentTimeMillis())
                .append("inventories", new Document("inventory", ItemStackStorage.InventoryTo64(player.getInventory())));

        Bson query = new Document("_id", player.getUniqueId().toString());

        UpdateOptions options = new UpdateOptions().upsert(true);

        try {
            collection.updateOne(query, new Document("$set", updates), options);
            player.sendMessage("Welcome back to the server " + player.getName() + "!");
        } catch (MongoException e) {
            Bukkit.getLogger().info("Unable to update due to an error: " + e);
            player.sendMessage("Welcome back to the server" + player.getName() + "! " +
                    "Just to let you know, An internal error has occurred, meaning things might not work as expected.");
        }
    }
    public static String convertToInvisibleString(String s) {
        String hidden = "";
        for (char c : s.toCharArray()) hidden += ChatColor.COLOR_CHAR+""+c;
        return hidden;
    }
}
