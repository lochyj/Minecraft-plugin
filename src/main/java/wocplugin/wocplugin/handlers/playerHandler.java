package wocplugin.wocplugin.handlers;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import wocplugin.wocplugin.GUI.MineMerchant;
import wocplugin.wocplugin.Items.ItemManager;
import wocplugin.wocplugin.Util.ItemStackStorage;
import wocplugin.wocplugin.WOCPlugin;

import java.util.*;
import java.util.concurrent.TimeUnit;


public class playerHandler implements Listener {

    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (Objects.equals(event.getItem().getItemMeta(), ItemManager.wand.getItemMeta())) {
                    Player player = event.getPlayer();
                    MineMerchant.openGUI(player);
                }
            }
        }

        if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if (event.getItem() != null) {
                if (Objects.equals(event.getItem().getItemMeta(), ItemManager.Sheepwand.getItemMeta())) {
                    Player player = event.getPlayer();
                    NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Mine merchant");
                    npc.spawn(player.getLocation());

                } else if (Objects.equals(event.getItem().getItemMeta(), ItemManager.aspectofthevoid.getItemMeta())) {
                    Player player = event.getPlayer();
                    List<Integer> blocks = new java.util.ArrayList<>(Collections.singletonList(12));
                    for (int i = 12; i > 0; i--) {
                        Location loc = player.getLocation().add(player.getLocation().getDirection().normalize().multiply(i));
                        if (loc.getBlock().getType() == Material.AIR) {
                            blocks.add(i);
                        }
                        if (i == 1) {
                            // if there is missing blocks, teleport the player to the nearest one
                            if (blocks.size() > 0) {
                                player.teleport(player.getLocation().add(player.getLocation().getDirection().normalize().multiply(blocks.get(0))));
                                player.setVelocity(new Vector(0, 0, 0));
                                player.setFallDistance(0);
                            }
                        }
                    }
                }
            }
        }

    if ((event.getAction() == Action.LEFT_CLICK_AIR) || (event.getAction() == Action.LEFT_CLICK_BLOCK|| (event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK))) {
            if (event.getItem() != null) {
                if (Objects.equals(event.getItem().getItemMeta(), ItemManager.shortBow.getItemMeta())) {
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
                        }.runTaskLater(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("WOCPlugin")), 30);
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 8, 1);
                }
                else if (Objects.equals(event.getItem().getItemMeta(), ItemManager.mobTester.getItemMeta())) {
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
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        if (event.getClickedBlock().getType() != null) {
            if (event.getClickedBlock().getType() == Material.ANVIL || event.getClickedBlock().getType() == Material.CHIPPED_ANVIL || event.getClickedBlock().getType() == Material.DAMAGED_ANVIL) {
                event.setCancelled(true);
            }
            else if (event.getClickedBlock().getType() == Material.IRON_TRAPDOOR || event.getClickedBlock().getType() == Material.ACACIA_TRAPDOOR || event.getClickedBlock().getType() == Material.BIRCH_TRAPDOOR || event.getClickedBlock().getType() == Material.DARK_OAK_TRAPDOOR || event.getClickedBlock().getType() == Material.JUNGLE_TRAPDOOR || event.getClickedBlock().getType() == Material.SPRUCE_TRAPDOOR) {
                event.setCancelled(true);
            }
            else if (event.getClickedBlock().getType() == Material.ACACIA_BUTTON || event.getClickedBlock().getType() == Material.STONE_BUTTON || event.getClickedBlock().getType() == Material.BIRCH_BUTTON || event.getClickedBlock().getType() == Material.DARK_OAK_BUTTON || event.getClickedBlock().getType() == Material.JUNGLE_BUTTON || event.getClickedBlock().getType() == Material.OAK_BUTTON || event.getClickedBlock().getType() == Material.SPRUCE_BUTTON) {
                event.setCancelled(true);
            }
        }
    }

    public static boolean activityExists(MongoDatabase db, Document doc) {
        FindIterable<Document> iterable = db.getCollection("playerData")
                .find(doc);
        return iterable.first() != null;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Database init for the player

        MongoCollection<Document> collection =  WOCPlugin.playerData;

        Document playerName = new Document("_id", event.getPlayer().getUniqueId().toString());


        if (!activityExists(WOCPlugin.database, playerName)) {
            Document player = new Document(playerName)
                    .append("user_name", event.getPlayer().getName())
                    .append("coins", 0)
                    .append("kills", 0)
                    .append("deaths", 0)
                    .append("first_join", System.currentTimeMillis())
                    .append("last_join", System.currentTimeMillis())
                    .append("play_time", 0)
                    .append("inventories", new Document("inventory", ItemStackStorage.InventoryTo64(event.getPlayer().getInventory())))
                    .append("loot_chests", new Document());



            try {
                collection.insertOne(player);
                event.getPlayer().sendMessage("Welcome " + event.getPlayer().getName() + "!");
            } catch (MongoException me) {
                Bukkit.getLogger().info("Unable to update due to an error: " + me);
                event.getPlayer().sendMessage("Welcome " + event.getPlayer().getName() + "! " +
                        "Just to let you know, An internal error has occurred, meaning things might not work as expected.");
            }


        } else {
            Bson updates = new Document("_id", event.getPlayer().getUniqueId().toString())
                    .append("last_join", System.currentTimeMillis())
                    .append("inventories", new Document("inventory", ItemStackStorage.InventoryTo64(event.getPlayer().getInventory())));

            Bson query = new Document("_id", event.getPlayer().getUniqueId().toString());

            UpdateOptions options = new UpdateOptions().upsert(true);

            try {
                UpdateResult result = collection.updateOne(query,new Document("$set", updates), options);
                event.getPlayer().sendMessage("Welcome back to the server " + event.getPlayer().getName() + "!");
            } catch (MongoException me) {
                Bukkit.getLogger().info("Unable to update due to an error: " + me);
                event.getPlayer().sendMessage("Welcome back to the server" + event.getPlayer().getName() + "! " +
                        "Just to let you know, An internal error has occurred, meaning things might not work as expected.");
            }
        }
    }

}
