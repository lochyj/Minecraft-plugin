package wocplugin.wocplugin.handlers;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import wocplugin.wocplugin.Util.Util;
import wocplugin.wocplugin.WOCPlugin;

public class PlayerEventHandler implements Listener {
    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if ((event.getAction() == Action.LEFT_CLICK_AIR) ||
            (event.getAction() == Action.LEFT_CLICK_BLOCK||
            (event.getAction() == Action.RIGHT_CLICK_AIR) ||
            (event.getAction() == Action.RIGHT_CLICK_BLOCK))) {
            if (event.getItem() != null) {
                new ItemEventHandler(event);
            }
        }

        // TODO: Make this a check for op and not game mode to allow interaction
        if (!(player.getGameMode() == GameMode.CREATIVE)) {
            Util.interactCancel(event);
        }

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        MongoCollection<Document> collection =  WOCPlugin.playerData;
        Document playerName = new Document("_id", event.getPlayer().getUniqueId().toString());

        if (!Util.playerExists(WOCPlugin.database, playerName)) {
            Util.playerDBInit(event.getPlayer(), collection);
        } else if (Util.playerExists(WOCPlugin.database, playerName)){
            Util.playerDBUpdate(event.getPlayer(), collection);
        }
    }
}
