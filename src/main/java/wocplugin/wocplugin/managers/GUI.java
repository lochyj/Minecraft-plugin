package wocplugin.wocplugin.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import wocplugin.wocplugin.Items.ItemSmith;

public class GUI {

    public static ItemSmith smithy = new ItemSmith();
    public static Inventory inventory = Bukkit.createInventory(null, 27, "Shop");

    public GUI() {

    }

    public static boolean openGUI(Player player) {

        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);

        inventory.setItem(0, filler);
        inventory.setItem(1, filler);
        inventory.setItem(2, filler);
        inventory.setItem(3, filler);
        inventory.setItem(4, filler);
        inventory.setItem(5, filler);
        inventory.setItem(6, filler);
        inventory.setItem(7, filler);
        inventory.setItem(8, filler);

        inventory.setItem(9, filler);
        inventory.setItem(10, filler);
        inventory.setItem(11, filler);
        inventory.setItem(12, new ItemStack(Material.EMERALD, 1));
        inventory.setItem(13, filler);
        inventory.setItem(14, new ItemStack(Material.REDSTONE, 1));
        inventory.setItem(15, filler);
        inventory.setItem(16, filler);
        inventory.setItem(17, filler);

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
