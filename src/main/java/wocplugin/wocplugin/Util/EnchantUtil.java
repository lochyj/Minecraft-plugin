package wocplugin.wocplugin.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class EnchantUtil {
    public static ItemStack addEnchantList(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        List<String> lore = new ArrayList<>(meta.getLore());
        lore.add(Util.convertToInvisibleString("%e%"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack addEnchant(ItemStack item, String enchant, int lvl) {
        if (item.getItemMeta() == null || item.getItemMeta().getLore() == null) return item;

        ItemMeta meta = item.getItemMeta();
        List<String> newLore = new ArrayList<>();

        String eB64 = Base64.getEncoder().encodeToString(("%" + enchant + ":" + lvl + "%").getBytes());


        Object[] metaArray = meta.getLore().toArray();
        for (int i = 0; i < metaArray.length; i++) {
            if (((String) metaArray[i]).contains(Util.convertToInvisibleString("%e%"))) {
                newLore.add((String) metaArray[i]);
                newLore.add(i + 1, ChatColor.YELLOW + enchant + " " + lvl);
                for (int j = i + 1; j < metaArray.length; j++) {
                    if (j == metaArray.length - 1) {
                        newLore.add((String)metaArray[j] + eB64);
                        break;
                    }
                    newLore.add((String) metaArray[j]);

                }
                break;
            }
            newLore.add((String) metaArray[i]);
        }
        Bukkit.getLogger().info("Set new lore");
        meta.setLore(newLore);
        item.setItemMeta(meta);
        return item;
    }
}
