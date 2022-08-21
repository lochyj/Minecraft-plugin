package wocplugin.wocplugin.handlers;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import wocplugin.wocplugin.GUI.MineMerchant;
import wocplugin.wocplugin.Items.ItemManager;


public class playerHandler implements Listener {

    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(ItemManager.wand.getItemMeta())) {
                    Player player = event.getPlayer();
                    MineMerchant.openGUI(player);
                }
            }
        }

        if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(ItemManager.Sheepwand.getItemMeta())) {
                    Player player = event.getPlayer();
                    NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Mine merchant");
                    npc.spawn(player.getLocation());

                } else if (event.getItem().getItemMeta().equals(ItemManager.aspectofthevoid.getItemMeta())) {
                    Player player = event.getPlayer();
                    player.teleport(player.getLocation().add(player.getLocation().getDirection().normalize().multiply(12)));
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 8, 2);
                    player.setVelocity(new Vector(0, 0, 0));
                    player.setFallDistance(0);
                    player.sendMessage("§l§7Zoop!");
                }

            }
        }

    if ((event.getAction() == Action.LEFT_CLICK_AIR) || (event.getAction() == Action.LEFT_CLICK_BLOCK|| (event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK))) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(ItemManager.shortBow.getItemMeta())) {
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
                        //arrw.setGlowing(true);
                        arrw.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                        arrw.setBounce(false);
                        new BukkitRunnable() {
                            public void run() {
                                arrw.remove();
                            }
                        }.runTaskLater(Bukkit.getPluginManager().getPlugin("WOCPlugin"), 30);
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 8, 1);
                } else if (event.getItem().getItemMeta().equals(ItemManager.mobTester.getItemMeta())) {
                    Player player = event.getPlayer();
                    Location spawnLocation = player.getLocation().add(2, 0, 2);
                    World world = player.getWorld();
                    Zombie zombie = (Zombie) world.spawnEntity(spawnLocation, EntityType.ZOMBIE);
                    double health = zombie.getHealth();
                    zombie.setCustomName(ChatColor.GRAY + "Ghoul" + ChatColor.RED + " ❤" + health);
                    zombie.setTarget(player);
                    zombie.setCustomNameVisible(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Nothing yet!
        event.getPlayer().sendMessage("§1Welcome to the server!");
    }

}
