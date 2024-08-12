package cn.milkycandy.serverinfoprovider.listener;

import cn.milkycandy.serverinfoprovider.ProviderCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener {

    private final ProviderCore plugin;

    public WeatherListener(ProviderCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (event.getWorld().getName().equals("world")) {
            plugin.getWebSocketService().broadcastWeather(event.toWeatherState() ? "rainy" : "clear");
        }
    }
}