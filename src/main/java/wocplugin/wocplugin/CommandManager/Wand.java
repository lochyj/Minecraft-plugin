package wocplugin.wocplugin.CommandManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wocplugin.wocplugin.Items.Items.Dagger;
import wocplugin.wocplugin.Items.Items.ShortBow;
import wocplugin.wocplugin.Items.Items.WarpWand;

public class Wand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command!");
            return true;
        }

        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("wand")) {
            if (args[0].equalsIgnoreCase("tp")) {
                player.getInventory().addItem(WarpWand.give());
            } else if (args[0].equalsIgnoreCase("bow")) {
                player.getInventory().addItem(ShortBow.give());
            } else if (args[0].equalsIgnoreCase("dagger")) {
                player.getInventory().addItem(Dagger.give());
            }
        }
        return true;
    }
}
