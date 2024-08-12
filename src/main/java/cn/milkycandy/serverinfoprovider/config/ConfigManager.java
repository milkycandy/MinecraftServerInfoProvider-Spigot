package cn.milkycandy.serverinfoprovider.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {

    private final JavaPlugin plugin;
    private FileConfiguration config;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
    }

    public int getWebSocketPort() {
        return config.getInt("websocket-port", 23333);
    }

}