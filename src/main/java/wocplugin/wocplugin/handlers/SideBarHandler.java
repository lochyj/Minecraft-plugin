package wocplugin.wocplugin.handlers;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public class SideBarHandler {

    public static ScoreboardManager manager = null;
    public static Player player = null;

    public SideBarHandler(Player player) {
        SideBarHandler.player = player;
        manager = player.getServer().getScoreboardManager();
        assert manager != null;
        Scoreboard board = manager.getNewScoreboard();
        board.registerNewObjective("", Criteria.DUMMY, "");
        // create a new score
        Score score = Objects.requireNonNull(board.getObjective("")).getScore("");
        // set the score
        score.setScore(0);
        // set the scoreboard to be a sidebar
        Objects.requireNonNull(board.getObjective("")).setDisplaySlot(DisplaySlot.SIDEBAR);
        // set the scoreboard
        player.setScoreboard(board);
    }

    public void update() {
        // Update the players sidebar
    }
}
