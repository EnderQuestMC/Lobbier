package quest.ender.Lobbier;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import quest.ender.Lobbier.listener.PlayerJoinListener;
import quest.ender.Lobbier.tasks.UpdateScoreboardTask;
import quest.ender.Lobbier.tasks.UpdateTablistTask;

public final class Lobbier extends JavaPlugin {
    private final @NotNull UpdateScoreboardTask updateScoreboardTask = new UpdateScoreboardTask(this);
    private final @NotNull UpdateTablistTask updateTablistTask = new UpdateTablistTask(this);

    @Override
    public final void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        this.updateScoreboardTask.runTaskTimer(this, 10L, 10L);
        this.updateTablistTask.runTaskTimer(this, 10L, 10L);
    }

    @Override
    public final void onDisable() {
        if (!this.updateScoreboardTask.isCancelled())
            this.updateScoreboardTask.cancel();
        if (!this.updateTablistTask.isCancelled())
            this.updateTablistTask.cancel();
    }
}
