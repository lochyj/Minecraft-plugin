package wocplugin.wocplugin.handlers;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class EventHandler implements Listener {
    public EventHandler(Plugin plugin) {
        getServer().getPluginManager().registerEvents(new PlayerEventHandler(), plugin);
        getServer().getPluginManager().registerEvents(new BlockEventHandler(), plugin);
        getServer().getPluginManager().registerEvents(new DamageEventHandler(), plugin);
    }
}
