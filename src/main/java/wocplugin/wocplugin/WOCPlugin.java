package wocplugin.wocplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import wocplugin.wocplugin.CommandManager.Spawn;
import wocplugin.wocplugin.CommandManager.Warp;
import wocplugin.wocplugin.Entities.Ghoul;
import wocplugin.wocplugin.Items.ItemManager;
import wocplugin.wocplugin.CommandManager.Fly;
import wocplugin.wocplugin.CommandManager.Wand;
import wocplugin.wocplugin.handlers.*;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

public final class WOCPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        ItemManager.init();
        getServer().getPluginManager().registerEvents(new playerHandler(), this);
        getServer().getPluginManager().registerEvents(new BlockHandler(this), this);
        getServer().getPluginManager().registerEvents(new GUIHandler(), this);
        getServer().getPluginManager().registerEvents(new Damage(this), this);
        getCommand("fly").setExecutor(new Fly());
        getCommand("wand").setExecutor(new Wand());
        getCommand("warp").setExecutor(new Warp());
        getCommand("spawn").setExecutor(new Spawn());
    }



    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Shutting down!");
    }
}
