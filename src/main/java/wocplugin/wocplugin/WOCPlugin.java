package wocplugin.wocplugin;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.conversions.Bson;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import wocplugin.wocplugin.CommandManager.*;
import wocplugin.wocplugin.Enchants.EnchantmentWrapper;
import wocplugin.wocplugin.handlers.*;



public final class WOCPlugin extends JavaPlugin {

    // Make this available to all classes
    public MongoClient mongoClient = null;
    public static MongoDatabase database = null;
    public static MongoCollection<Document> playerData = null;

    @Override
    public void onEnable() {

        // Init DB
        mongoClient = new MongoClient();
        database = mongoClient.getDatabase("MCServer");
        playerData = database.getCollection("playerData");

        // Register events
        getServer().getPluginManager().registerEvents(new EventHandler(this), this);

        // Register commands
        getCommand("fly").setExecutor(new Fly());
        getCommand("wand").setExecutor(new Wand());
        getCommand("warp").setExecutor(new Warp());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("enderchest").setExecutor(new enderchest());

        // TODO: Fix this
        // EnchantmentWrapper.register();
    }

    @Override
    public void onDisable() {
        // Shutdown the mongodb connection
        mongoClient.close();
    }
}
