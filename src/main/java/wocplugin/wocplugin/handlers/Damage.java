package wocplugin.wocplugin.handlers;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;

public class Damage implements Listener {

    public Plugin plugin;

    public Damage(Plugin plugin1) {
        plugin = plugin1;
    }

    @EventHandler
    public void EntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        final DecimalFormat df = new DecimalFormat("0.00");
        if (entity.getName().contains("Ghoul")) {
            Double health = Double.parseDouble(entity.getName().split("❤")[1]) -  event.getFinalDamage();
            health = Double.parseDouble(df.format(health));
            if (health <= 0) {
                health = 0.00;
            }
            entity.setCustomName(ChatColor.GRAY + "Ghoul"+ ChatColor.RED +" ❤" + health);
        }

        // Damage hologram start
        World world = event.getEntity().getWorld();
        Location location = (Location) event.getEntity().getLocation();
        ArmorStand hologram = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
        hologram.setVisible(false);
        hologram.setCustomName(ChatColor.RED + df.format(event.getFinalDamage()));
        hologram.setCustomNameVisible(true);
        hologram.setInvulnerable(true);
        hologram.setSmall(true);
        hologram.setGravity(false);
        new BukkitRunnable() {
            public void run() {
                hologram.remove();
            }
        }.runTaskLater(plugin, 30);
        // Damage hologram end

    }
}
