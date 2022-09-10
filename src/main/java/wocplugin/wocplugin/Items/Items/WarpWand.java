package wocplugin.wocplugin.Items.Items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import wocplugin.wocplugin.Items.AddIdentifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WarpWand {
    public static String identifier = "OpdqfgWnfiO";

    private static ItemStack create() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§1§lAspect of the Void");
        List<String> lore = new ArrayList<>();
        lore.add("§7The best teleportation weapon in existence");
        lore.add("§e§lRight Click:");
        lore.add("§7Teleport: Teleports you 12 blocks in the direction you are looking");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack give() {
        return AddIdentifier.Add(create(), identifier);
    }

    public static void function(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getEyeLocation();
        Vector dir = loc.getDirection();
        for (int i = 1; i < 13; i++) {
            loc.add(dir);
            if (loc.getBlock().getType() != Material.AIR) {
                loc.subtract(dir);
                player.teleport(loc);
                player.setVelocity(new Vector(0, 0, 0));
                player.setFallDistance(0);
                break;
            } else if (i == 12) {
                player.teleport(loc);
                player.setVelocity(new Vector(0, 0, 0));
                player.setFallDistance(0);
                break;
            }
        }
        player.playSound(player.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT, 10, 2);

//        for (int i = 12; i > 0; i--) {
//            Location loc = player.getLocation().add(player.getLocation().getDirection().normalize().multiply(i));
//            if (loc.getBlock().getType() == Material.AIR) {
//                blocks.add(i);
//            }
//            if (i == 1) {
//                if (blocks.size() > 0) {
//                    player.teleport(player.getLocation().add(player.getLocation().getDirection().normalize().multiply(blocks.get(0))));
//                    player.setVelocity(new Vector(0, 0, 0));
//                    player.setFallDistance(0);
//                }
//            }
//        }
    }
}
