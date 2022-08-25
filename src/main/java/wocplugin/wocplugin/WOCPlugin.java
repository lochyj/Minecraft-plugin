package wocplugin.wocplugin;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import wocplugin.wocplugin.CommandManager.Spawn;
import wocplugin.wocplugin.CommandManager.Warp;
import wocplugin.wocplugin.Items.ItemManager;
import wocplugin.wocplugin.CommandManager.Fly;
import wocplugin.wocplugin.CommandManager.Wand;
import wocplugin.wocplugin.handlers.*;

import java.util.Arrays;


public final class WOCPlugin extends JavaPlugin {

    // Make this available to all classes
    public MongoClient mongoClient = null;
    public static MongoDatabase database = null;
    public static MongoCollection<Document> playerData = null;

    @Override
    public void onEnable() {
        // Generate custom items
        ItemManager.init();

        // Init DB
        mongoClient = new MongoClient();
        database = mongoClient.getDatabase("MCServer");
        playerData = database.getCollection("playerData");


        // Register events
        getServer().getPluginManager().registerEvents(new playerHandler(), this);
        getServer().getPluginManager().registerEvents(new BlockHandler(this), this);
        getServer().getPluginManager().registerEvents(new GUIHandler(), this);
        getServer().getPluginManager().registerEvents(new Damage(this), this);

        // Register commands
        getCommand("fly").setExecutor(new Fly());
        getCommand("wand").setExecutor(new Wand());
        getCommand("warp").setExecutor(new Warp());
        getCommand("spawn").setExecutor(new Spawn());


    }

    public static Object returnDB(Player player, MongoCollection collection) {
        Bson query = new Document("_id", player.getUniqueId().toString());

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

    @Override
    public void onDisable() {
        // Plugin shutdown logic (Nothing yet!)
        Bukkit.getLogger().info("Shutting down!");
        mongoClient.close();
    }
}
