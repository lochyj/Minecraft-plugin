package wocplugin.wocplugin.handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
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
import wocplugin.wocplugin.WOCPlugin;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

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

        // Block replace delay
        int delay = 200;

        // Check if the player is an admin / builder and exempt if they are
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        // Cancel the event if the player is not in creative mode
        event.setCancelled(true);

        // Lists of used ores and stones
        List<Material> stone = List.of(Material.STONE, Material.COBBLESTONE, Material.TUFF, Material.ANDESITE);
        List<Material> ores = List.of(Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.GOLD_ORE, Material.IRON_ORE, Material.LAPIS_ORE, Material.REDSTONE_ORE, Material.COAL_ORE, Material.COPPER_ORE);

        // Check if the block is in the Ice spikes biome ( Biome of the mines )
        if (block.getBiome() == Biome.ICE_SPIKES) {

            // Check if the block has meta, if not then give it some
            if (!(block.hasMetadata(block.getLocation().toString()))) {
                block.setMetadata(block.getLocation().toString(), new FixedMetadataValue(Bukkit.getPluginManager().getPlugin("WOCPlugin"), block.getType().toString()));
            }

            // Check if the block is a stone or ore
            if (((stone.contains(block.getType())) || (ores.contains(block.getType())))) {

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
                }.runTaskLater(Bukkit.getPluginManager().getPlugin("WOCPlugin"), delay);
            }
        }
    }
}
