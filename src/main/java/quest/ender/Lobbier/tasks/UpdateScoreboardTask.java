package quest.ender.Lobbier.tasks;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import quest.ender.Lobbier.Lobbier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class UpdateScoreboardTask extends BukkitRunnable {
    final @NotNull Lobbier lobbier;

    public UpdateScoreboardTask(final @NotNull Lobbier lobbier) {
        this.lobbier = lobbier;
    }

    @Override
    public final void run() {
        final @Nullable String scoreboardTitle = this.lobbier.getConfig().getString("scoreboard.title");
        final @NotNull List<@NotNull String> scoreboardEntries = this.lobbier.getConfig().getStringList("scoreboard.content");

        if (scoreboardTitle != null && scoreboardEntries.size() > 0) {
            for (final @NotNull Player player : this.lobbier.getServer().getOnlinePlayers()) {
                final @NotNull Component titleComponent = LegacyComponentSerializer.legacyAmpersand().deserialize(PlaceholderAPI.setPlaceholders(player, scoreboardTitle));
                final @NotNull ArrayList<@NotNull Component> scoreboardEntryComponents = new ArrayList<>();

                for (final @NotNull String lineOfText : scoreboardEntries)
                    scoreboardEntryComponents.add(LegacyComponentSerializer.legacyAmpersand().deserialize(PlaceholderAPI.setPlaceholders(player, lineOfText)));

                final @NotNull Scoreboard targetScoreboard = this.lobbier.getServer().getScoreboardManager().getNewScoreboard(); // This shouldn't cause a memory leak... right?

                final @NotNull Objective primaryObjective = targetScoreboard.registerNewObjective("primary", "dummy", titleComponent, RenderType.INTEGER);
                primaryObjective.setDisplaySlot(DisplaySlot.SIDEBAR);

                Collections.reverse(scoreboardEntryComponents);
                for (final @NotNull Component component : scoreboardEntryComponents) {
                    final @NotNull String serializedComponent = LegacyComponentSerializer.legacySection().serialize(component);

                    if (serializedComponent.length() < 40)
                        primaryObjective.getScore(serializedComponent).setScore(scoreboardEntryComponents.indexOf(component));
                    else
                        this.lobbier.getLogger().warning("Line \"" + serializedComponent + "\" is longer than the limit of 40 characters.");
                }

                player.setScoreboard(targetScoreboard); // Scoreboard is ready. Send it!
            }
        }
    }
}
