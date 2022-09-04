package wocplugin.wocplugin.handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;

public class DamageEventHandler implements Listener {

    @EventHandler
    public void EntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        final DecimalFormat df = new DecimalFormat("0.0");
        if (entity.getName().contains("Ghoul")) {
            Double health = Double.parseDouble(entity.getName().split("❤")[1]) -  event.getFinalDamage();
            health = Double.parseDouble(df.format(health));
            if (health <= 0) {
                health = 0.0;
            }
            entity.setCustomName(ChatColor.GRAY + "Ghoul"+ ChatColor.RED +" ❤" + health);
        }
        createDamageHologram(event, df);

    }

    public static void createDamageHologram(EntityDamageEvent event, DecimalFormat df) {
        World world = event.getEntity().getWorld();
        Location location = event.getEntity().getLocation();
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
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("WOCPlugin"), 30);
    }
}
