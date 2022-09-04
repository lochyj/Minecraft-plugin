package wocplugin.wocplugin.Items.Items;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import wocplugin.wocplugin.Items.AddIdentifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ShortBow {
    public static String identifier = "pGdqGgOn6Yn";

    private static ItemStack create() {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§1§lShort Bow");
        List<String> lore = new ArrayList<>();
        lore.add("§7The best teleportation weapon in existence");
        lore.add("§e§lRight Click:");
        lore.add("§7Teleport: Teleports you 12 blocks in the direction you are looking");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack give() {
        return AddIdentifier.Add(create(), identifier);
    }

    public static void function(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        player.getInventory().remove(Material.ARROW);
        Vector arrowvector = player.getLocation().getDirection();
        int arrows = 1;
        for (int i = 0; i < arrows; i++) {
            Location location = player.getEyeLocation().add(player.getEyeLocation().getDirection().normalize().multiply(1.5));
            Arrow arrw = (Arrow)player.getWorld().spawnArrow(location, arrowvector, 3, 1);
            arrw.setShooter(player);
            arrw.setGravity(false);
            arrw.setDamage(1000);
            arrw.setKnockbackStrength(0);
            arrw.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
            arrw.setBounce(false);
            new BukkitRunnable() {
                public void run() {
                    arrw.remove();
                }
            }.runTaskLater(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("WOCPlugin")), 30);
        }
        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 8, 1);
    }
}
