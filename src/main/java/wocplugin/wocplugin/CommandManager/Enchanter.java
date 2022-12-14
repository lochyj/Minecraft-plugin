package wocplugin.wocplugin.CommandManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;
import wocplugin.wocplugin.Util.EnchantUtil;

public class Enchanter implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command!");
            return true;
        }

        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("enchanter")) {
            ItemStack item = EnchantUtil.addEnchant(player.getInventory().getItemInMainHand(), args[0],  Integer.parseInt(args[1]));
            player.getInventory().setItemInMainHand(item);
            player.updateInventory();
        }
        return true;
    }
}
