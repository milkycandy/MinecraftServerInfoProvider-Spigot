package cn.milkycandy.serverinfoprovider;

import cn.milkycandy.serverinfoprovider.config.ConfigManager;
import cn.milkycandy.serverinfoprovider.listener.PlayerListener;
import cn.milkycandy.serverinfoprovider.listener.WeatherListener;
import cn.milkycandy.serverinfoprovider.service.PlayerService;
import cn.milkycandy.serverinfoprovider.service.WebSocketService;
import cn.milkycandy.serverinfoprovider.task.GameTimeTask;
import org.bukkit.plugin.java.JavaPlugin;

public class ProviderCore extends JavaPlugin {

    private ConfigManager configManager;
    private PlayerService playerService;
    private WebSocketService webSocketService;

    @Override
    public void onEnable() {
        // Initialize config
        configManager = new ConfigManager(this);
        configManager.loadConfig();

        // Initialize services
        playerService = new PlayerService(this);
        webSocketService = new WebSocketService(this);

        // Register event listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new WeatherListener(this), this);

        // Start game time task
        new GameTimeTask(this).runTaskTimer(this, 0L, 1200L);

        getLogger().info("[信息提供器]信息提供插件已启动！");
    }

    @Override
    public void onDisable() {
        try {
            webSocketService.stop();
        } catch (InterruptedException e) {
            getLogger().warning("[信息提供器]停止 WebSocket 服务时发生中断异常: " + e.getMessage());
            // 恢复中断状态
            Thread.currentThread().interrupt();
        }
        getLogger().info("[信息提供器]信息提供插件已停止！");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public PlayerService getPlayerService() {
        return playerService;
    }

    public WebSocketService getWebSocketService() {
        return webSocketService;
    }
}