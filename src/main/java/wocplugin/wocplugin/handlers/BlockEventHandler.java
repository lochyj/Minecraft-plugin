package wocplugin.wocplugin.handlers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;

public class BlockEventHandler implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!(player.getGameMode() == GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        // Block replace delays
        int minesDelay = 200;
        int forestDelay = 500;
        int farmsDelay = 700;

        // Check if the player is an admin / builder and exempt if they are
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }

        event.setCancelled(true);

        // List of all breakable blocks
        List<Material> stone = List.of(Material.STONE, Material.COBBLESTONE, Material.TUFF, Material.ANDESITE);
        List<Material> ores = List.of(Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.GOLD_ORE, Material.IRON_ORE, Material.LAPIS_ORE, Material.REDSTONE_ORE, Material.COAL_ORE, Material.COPPER_ORE);
        List<Material> wood = List.of(Material.OAK_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.DARK_OAK_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG);
        List<Material> leaves = List.of(Material.OAK_LEAVES, Material.SPRUCE_LEAVES, Material.BIRCH_LEAVES, Material.DARK_OAK_LEAVES, Material.JUNGLE_LEAVES, Material.ACACIA_LEAVES);
        List<Material> crops = List.of(Material.WHEAT, Material.WHEAT_SEEDS, Material.BEETROOT_SEEDS, Material.MELON_SEEDS, Material.PUMPKIN_SEEDS, Material.PUMPKIN, Material.MELON, Material.BEETROOT);

        // Check if the block has meta, if it doesn't, give it some.
        if (!(block.hasMetadata(block.getLocation().toString()))) {
            block.setMetadata(block.getLocation().toString(), new FixedMetadataValue(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("WOCPlugin")), block.getType().toString()));
        }

        // Biome checks
        if (block.getBiome().equals(Biome.ICE_SPIKES)) {
            // Checks if the biome is the mines biome
            mines(block, minesDelay, ores, stone, player);
        } else if (block.getBiome().equals(Biome.GROVE)) {
            // Checks if the biome is the forest biome
            forest(block, forestDelay, wood, leaves, player);
        } else if (block.getBiome().equals(Biome.PLAINS)) {
            // Checks if the biome is the farms biome
            farms(block, farmsDelay, crops, player);
        }
    }

    public static void farms(Block block, int delay,  List<Material> crops, Player player) {
        if (crops.contains( block.getType() ) ) {
            player.getInventory().addItem(new ItemStack(block.getType(), 1));
            block.setType(Material.AIR);
            new BukkitRunnable() {
                public void run() {
                    if (block.getType() == Material.AIR) {
                        block.setType(Material.valueOf(block.getMetadata(block.getLocation().toString()).get(0).asString()));
                    }
                }
            }.runTaskLater(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("WOCPlugin")), delay);
        }
    }

    public static void forest(Block block, int delay, List<Material> wood, List<Material> leaves, Player player) {
        if ( wood.contains(block.getType()) ||  leaves.contains(block.getType()) ){
            if (wood.contains(block.getType())) {
                player.getInventory().addItem(new ItemStack(block.getType(), 1));
                block.setType(Material.AIR);
            }
            new BukkitRunnable() {
                public void run() {
                    if (block.getType() == Material.AIR) {
                        block.setType(Material.valueOf(block.getMetadata(block.getLocation().toString()).get(0).asString()));
                    }
                }
            }.runTaskLater(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("WOCPlugin")), delay);
        }
    }

    public static void mines(Block block, int delay, List<Material> ores, List<Material> stone, Player player) {
        if (((stone.contains(block.getType())) || (ores.contains(block.getType())))) {
            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3, 2);
            if (ores.contains(block.getType())) {
                player.getInventory().addItem(new ItemStack(block.getType(), 1));
                block.setType(Material.STONE);
            }
            else if (stone.contains(block.getType())) {
                player.getInventory().addItem(new ItemStack(Material.STONE, 1));
                block.setType(Material.BEDROCK);
            }
            new BukkitRunnable() {
                public void run() {
                    if (block.getType() == Material.STONE) {
                        block.setType(Material.valueOf(block.getMetadata(block.getLocation().toString()).get(0).asString()));
                    }
                    else if (block.getType() == Material.BEDROCK) {
                        block.setType(Material.valueOf(block.getMetadata(block.getLocation().toString()).get(0).asString()));
                    }
                }
            }.runTaskLater(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("WOCPlugin")), delay);
        }
    }
}
