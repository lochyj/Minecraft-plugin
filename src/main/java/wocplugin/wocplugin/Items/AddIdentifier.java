package wocplugin.wocplugin.Items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import wocplugin.wocplugin.Util.EnchantUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddIdentifier {
    public static ItemStack Add(ItemStack item, String identifier) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        List<String> lore = new ArrayList<>();
        lore.add (identifier);
        lore.addAll(Objects.requireNonNull(meta.getLore()));
        meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);

        item = EnchantUtil.addEnchantList(item);

        return item;
    }
}
