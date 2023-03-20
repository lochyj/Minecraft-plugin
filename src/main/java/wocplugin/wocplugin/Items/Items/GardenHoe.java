package wocplugin.wocplugin.Items.Items;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static wocplugin.wocplugin.Util.sendPlayerMessage;

public class GardenHoe {

    private static List<Material> crops = List.of(Material.WHEAT, Material.WHEAT_SEEDS, Material.BEETROOT_SEEDS, Material.MELON_SEEDS, Material.PUMPKIN_SEEDS, Material.PUMPKIN, Material.MELON, Material.BEETROOT);

    public static String identifier = "hgHZGpNn64t";

    private static ItemStack create() {
        ItemStack item = new ItemStack(Material.WOODEN_HOE, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§1§lGarden Hoe");
        List<String> lore = new ArrayList<>();
        //§7
        lore.add("A basic hoe that doesn't break baby crops and stems");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }

    public static void function(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        Block block = event.getClickedBlock();

        if (crops.contains(block)) {
            sendPlayerMessage(player, "Broken the block");
        }

    }

    public static ItemStack give() {
        return AddIdentifier.Add(create(), identifier);
    }

}
