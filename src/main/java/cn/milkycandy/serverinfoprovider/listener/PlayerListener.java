package cn.milkycandy.serverinfoprovider.listener;

import cn.milkycandy.serverinfoprovider.ProviderCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerListener implements Listener {

    private final ProviderCore plugin;

    public PlayerListener(ProviderCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.getPlayerService().updatePlayer(event.getPlayer());
        plugin.getWebSocketService().broadcastPlayerLists();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getPlayerService().setPlayerOffline(event.getPlayer().getUniqueId());
        plugin.getWebSocketService().broadcastPlayerLists();
    }

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        plugin.getPlayerService().updatePlayer(event.getPlayer());
        plugin.getWebSocketService().broadcastPlayerLists();
    }
}