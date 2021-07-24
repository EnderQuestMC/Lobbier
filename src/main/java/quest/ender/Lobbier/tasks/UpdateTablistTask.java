package quest.ender.Lobbier.tasks;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import quest.ender.Lobbier.Lobbier;

import java.util.List;

public final class UpdateTablistTask extends BukkitRunnable {
    final @NotNull Lobbier lobbier;

    public UpdateTablistTask(final @NotNull Lobbier lobbier) {
        this.lobbier = lobbier;
    }

    @Override
    public final void run() {
        for (final @NotNull Player player : this.lobbier.getServer().getOnlinePlayers()) {
            @NotNull Component headerComponent = Component.empty();
            @NotNull Component footerComponent = Component.empty();

            final @NotNull List<@NotNull String> headerLines = this.lobbier.getConfig().getStringList("tab_list.header");
            for (final @NotNull String lineOfMessage : headerLines) {
                headerComponent = headerComponent.append(LegacyComponentSerializer.legacyAmpersand().deserialize(PlaceholderAPI.setPlaceholders(player, lineOfMessage)));
                if (headerLines.indexOf(lineOfMessage) + 1 != headerLines.size())
                    headerComponent = headerComponent.append(Component.newline());
            }

            final @NotNull List<@NotNull String> footerLines = this.lobbier.getConfig().getStringList("tab_list.footer");
            for (final @NotNull String lineOfMessage : footerLines) {
                footerComponent = footerComponent.append(LegacyComponentSerializer.legacyAmpersand().deserialize(PlaceholderAPI.setPlaceholders(player, lineOfMessage)));
                if (footerLines.indexOf(lineOfMessage) + 1 != footerLines.size())
                    footerComponent = footerComponent.append(Component.newline());
            }

            player.sendPlayerListHeaderAndFooter(headerComponent, footerComponent);
        }
    }
}
