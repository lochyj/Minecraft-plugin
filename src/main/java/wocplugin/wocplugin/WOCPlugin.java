package wocplugin.wocplugin;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import org.bukkit.plugin.java.JavaPlugin;

import wocplugin.wocplugin.CommandManager.*;
import wocplugin.wocplugin.handlers.*;



public final class WOCPlugin extends JavaPlugin {

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
        getCommand("enchanter").setExecutor(new Enchanter());

        // TODO: Fix this
        // EnchantmentWrapper.register();
    }

    @Override
    public void onDisable() {
        // Shutdown the mongodb connection
        mongoClient.close();
    }
}
