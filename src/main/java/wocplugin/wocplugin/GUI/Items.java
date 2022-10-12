package wocplugin.wocplugin.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import wocplugin.wocplugin.Items.Items.Dagger;
import wocplugin.wocplugin.Items.Items.ShortBow;
import wocplugin.wocplugin.Items.Items.WarpWand;

public class Items {
    public static Inventory inventory = Bukkit.createInventory(null, 27, "Items");

    public static boolean openGUI(Player player) {

        ItemStack filler = new ItemStack(Material.BARRIER, 1);

        inventory.setItem(0, WarpWand.give());
        inventory.setItem(1, Dagger.give());
        inventory.setItem(2, ShortBow.give());
        inventory.setItem(3, new ItemStack(Material.COMMAND_BLOCK, 1));
        inventory.setItem(4, filler);
        inventory.setItem(5, filler);
        inventory.setItem(6, filler);
        inventory.setItem(7, filler);
        inventory.setItem(8, filler);
        // -
        inventory.setItem(9, filler);
        inventory.setItem(10, filler);
        inventory.setItem(11, filler);
        inventory.setItem(12, filler);
        inventory.setItem(13, filler);
        inventory.setItem(14, filler);
        inventory.setItem(15, filler);
        inventory.setItem(16, filler);
        inventory.setItem(17, filler);
        // -
        inventory.setItem(18, filler);
        inventory.setItem(19, filler);
        inventory.setItem(20, filler);
        inventory.setItem(21, filler);
        inventory.setItem(22, filler);
        inventory.setItem(23, filler);
        inventory.setItem(24, filler);
        inventory.setItem(25, filler);
        inventory.setItem(26, filler);

        player.openInventory(inventory);
        return true;
    }
}
