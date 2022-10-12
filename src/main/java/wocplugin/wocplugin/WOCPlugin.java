package wocplugin.wocplugin;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import wocplugin.wocplugin.CommandManager.*;
import wocplugin.wocplugin.handlers.*;



public final class WOCPlugin extends JavaPlugin implements Listener {

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
        getServer().getPluginManager().registerEvents(this, this);

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

    @org.bukkit.event.EventHandler
    public void onPlayerFireArrow(ProjectileLaunchEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            event.setCancelled(true);
        }
    }


}
