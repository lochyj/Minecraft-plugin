package wocplugin.wocplugin.Entities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class Ball {

    public static ArmorStand armorStand;

    public Ball(Location location) {
        World world = location.getWorld();
        ArmorStand stand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
        stand.setCustomNameVisible(false);
        stand.setGravity(false);
        stand.setSmall(true);
        stand.setVisible(false);
        stand.setHelmet(new ItemStack(Material.BEACON));
        stand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.ADDING_OR_CHANGING);
        stand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
        stand.setNoDamageTicks(999999999);
        armorStand = stand;
    }
    public static void Hit(Player player) {
        armorStand.setVelocity(player.getLocation().getDirection().multiply(1.5));
    }
}
