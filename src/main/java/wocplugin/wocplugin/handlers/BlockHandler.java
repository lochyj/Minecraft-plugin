package wocplugin.wocplugin.handlers;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;

public class BlockHandler implements Listener {
    public Plugin Gplugin;
    public BlockHandler(Plugin plugin) {
        Gplugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        // Check if the player is an admin / builder and exempt if they are
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        // Cancel the event if the player is not in creative mode
        event.setCancelled(true);
    }

    public Object getMetadata(Metadatable object, String key, Plugin plugin) {
        List<MetadataValue> values = object.getMetadata(key);
        for (MetadataValue value : values) {
            // Plugins are singleton objects, so using == is safe here
            if (value.getOwningPlugin() == plugin) {
                return value.value();
            }
        }
        return null;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();


        // Block replace delay for the mines
        int minesDelay = 200;

        // Block replace delay for the forest
        int forestDelay = 500;

        // Block replace delay for the farms
        int farmsDelay = 700;

        // Check if the player is an admin / builder and exempt if they are
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }

        // Cancel the event if the player is not in creative mode
        event.setCancelled(true);

        // Lists of used ores and stones
        List<Material> stone = List.of(Material.STONE, Material.COBBLESTONE, Material.TUFF, Material.ANDESITE);
        List<Material> ores = List.of(Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.GOLD_ORE, Material.IRON_ORE, Material.LAPIS_ORE, Material.REDSTONE_ORE, Material.COAL_ORE, Material.COPPER_ORE);

        // Lists of used woods and leaves
        List<Material> wood = List.of(Material.OAK_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.DARK_OAK_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG);
        List<Material> leaves = List.of(Material.OAK_LEAVES, Material.SPRUCE_LEAVES, Material.BIRCH_LEAVES, Material.DARK_OAK_LEAVES, Material.JUNGLE_LEAVES, Material.ACACIA_LEAVES);

        // Lists of used crops
        List<Material> crops = List.of(Material.WHEAT, Material.WHEAT_SEEDS, Material.BEETROOT_SEEDS, Material.MELON_SEEDS, Material.PUMPKIN_SEEDS, Material.PUMPKIN, Material.MELON, Material.BEETROOT);

        // Check if the block is in the Ice spikes biome ( Biome of the mines )
        if (block.getBiome().equals(Biome.ICE_SPIKES)) {

            // Check if the block has meta, if not then give it some
            if (!(block.hasMetadata(block.getLocation().toString()))) {
                block.setMetadata(block.getLocation().toString(), new FixedMetadataValue(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("WOCPlugin")), block.getType().toString()));
            }

            // Check if the block is a stone or ore
            if (((stone.contains(block.getType())) || (ores.contains(block.getType())))) {

                player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3, 2);

                // If lvl 1
                if (ores.contains(block.getType())) {
                    // Set lvl 2
                    event.getPlayer().getInventory().addItem(new ItemStack(block.getType(), 1));
                    block.setType(Material.STONE);
                }
                // If lvl 2
                else if (stone.contains(block.getType())) {
                    // Set lvl 3
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.STONE, 1));
                    block.setType(Material.BEDROCK);
                }
                new BukkitRunnable() {
                    public void run() {
                        // If lvl 2
                        if (block.getType() == Material.STONE) {
                            // To lvl 1
                            block.setType(Material.valueOf(block.getMetadata(block.getLocation().toString()).get(0).asString()));
                        }
                        // If lvl 3
                        else if (block.getType() == Material.BEDROCK) {
                            // To lvl 1
                            block.setType(Material.valueOf(block.getMetadata(block.getLocation().toString()).get(0).asString()));
                        }
                    }
                }.runTaskLater(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("WOCPlugin")), minesDelay);
            }
        } else if (block.getBiome().equals(Biome.GROVE)) {
            if (!(block.hasMetadata(block.getLocation().toString()))) {
                block.setMetadata(block.getLocation().toString(), new FixedMetadataValue(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("WOCPlugin")), block.getType().toString()));
            }

            if (((wood.contains(block.getType())) || (leaves.contains(block.getType())))) {
                if (wood.contains(block.getType())) {
                    event.getPlayer().getInventory().addItem(new ItemStack(block.getType(), 1));
                    block.setType(Material.AIR);
                }
                new BukkitRunnable() {
                    public void run() {
                        if (block.getType() == Material.AIR) {
                            block.setType(Material.valueOf(block.getMetadata(block.getLocation().toString()).get(0).asString()));
                        }
                    }
                }.runTaskLater(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("WOCPlugin")), forestDelay);
            }
        } else if (block.getBiome().equals(Biome.PLAINS)) {
            if (!(block.hasMetadata(block.getLocation().toString()))) {
                block.setMetadata(block.getLocation().toString(), new FixedMetadataValue(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("WOCPlugin")), block.getType().toString()));
            }

            if (crops.contains( block.getType() ) ) {
                event.getPlayer().getInventory().addItem(new ItemStack(block.getType(), 1));
                block.setType(Material.AIR);
                new BukkitRunnable() {
                    public void run() {
                        if (block.getType() == Material.AIR) {
                            block.setType(Material.valueOf(block.getMetadata(block.getLocation().toString()).get(0).asString()));
                        }
                    }
                }.runTaskLater(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("WOCPlugin")), farmsDelay);
            }
        }
    }
}
