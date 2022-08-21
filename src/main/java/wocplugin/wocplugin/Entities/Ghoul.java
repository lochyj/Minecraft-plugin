package wocplugin.wocplugin.Entities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

public class Ghoul {
    public Ghoul(Location location) {
        World world = location.getWorld();
        Zombie zombie = (Zombie) world.spawnEntity(location, EntityType.ZOMBIE);
        double health = zombie.getHealth();
        zombie.setCustomName(ChatColor.GRAY + "Ghoul" + ChatColor.RED + " ❤" + health);
        zombie.setCustomNameVisible(true);
        zombie.setAdult();
    }

}
