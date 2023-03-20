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

public class FieldHoe {
    private static List<Material> crops = List.of(Material.WHEAT, Material.WHEAT_SEEDS, Material.BEETROOT_SEEDS, Material.MELON_SEEDS, Material.PUMPKIN_SEEDS, Material.PUMPKIN, Material.MELON, Material.BEETROOT);

    public static String identifier = "679FjoasFmoF98";

    private static ItemStack create() {
        ItemStack item = new ItemStack(Material.STONE_HOE, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§1§lField Hoe");
        List<String> lore = new ArrayList<>();
        //§7
        lore.add("A more advanced hoe that replaces broken crops and doesn't break baby crops and stems");
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
            player.playSound( player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 100, 1);
        }

    }

    public static ItemStack give() {
        return AddIdentifier.Add(create(), identifier);
    }
}
