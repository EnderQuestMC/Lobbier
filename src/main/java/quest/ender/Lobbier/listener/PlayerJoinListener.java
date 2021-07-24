package quest.ender.Lobbier.listener;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import quest.ender.Lobbier.Lobbier;

import java.util.List;

public final class PlayerJoinListener implements Listener {
    private final @NotNull Lobbier lobbier;

    public PlayerJoinListener(final @NotNull Lobbier lobbier) {
        this.lobbier = lobbier;
    }

    @EventHandler
    public final void onPlayerJoinForSendToSpawn(final @NotNull PlayerJoinEvent playerJoinEvent) {
        if (this.lobbier.getConfig().getBoolean("send_to_spawn"))
            playerJoinEvent.getPlayer().teleportAsync(this.lobbier.getServer().getWorlds().get(0).getSpawnLocation());
    }

    @EventHandler
    public final void onPlayerJoinForOnJoinMessage(final @NotNull PlayerJoinEvent playerJoinEvent) {
        for (final @NotNull String lineOfMessage : this.lobbier.getConfig().getStringList("messages.on_join"))
            playerJoinEvent.getPlayer().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(PlaceholderAPI.setPlaceholders(playerJoinEvent.getPlayer(), lineOfMessage)));
    }
}
