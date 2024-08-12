package cn.milkycandy.serverinfoprovider.task;

import cn.milkycandy.serverinfoprovider.ProviderCore;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class GameTimeTask extends BukkitRunnable {

    private final ProviderCore plugin;

    public GameTimeTask(ProviderCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        long time = Objects.requireNonNull(Bukkit.getWorld("world")).getTime();
        plugin.getWebSocketService().broadcastGameTime(time);
    }
}