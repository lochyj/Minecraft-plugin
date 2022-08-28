package wocplugin.wocplugin.CommandManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wocplugin.wocplugin.GUI.EnderchestHandler;

import java.io.IOException;

public class enderchest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command!");
            return true;
        }

        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("enderchest")) {
            try {
                new EnderchestHandler(player);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
