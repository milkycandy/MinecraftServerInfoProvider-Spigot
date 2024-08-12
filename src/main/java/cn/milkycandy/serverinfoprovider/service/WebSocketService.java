package cn.milkycandy.serverinfoprovider.service;

import cn.milkycandy.serverinfoprovider.ProviderCore;
import cn.milkycandy.serverinfoprovider.util.JsonUtil;
import org.bukkit.Bukkit;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Objects;

public class WebSocketService extends WebSocketServer {

    private final ProviderCore plugin;

    public WebSocketService(ProviderCore plugin) {
        super(new InetSocketAddress(plugin.getConfigManager().getWebSocketPort()));
        this.plugin = plugin;
        start();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        sendAllInfo(conn);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {

    }

    @Override
    public void onMessage(WebSocket conn, String message) {

    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        plugin.getLogger().warning("[信息提供器]WebSocket 错误: " + ex.getMessage());
    }

    @Override
    public void onStart() {
        plugin.getLogger().info("[信息提供器]WebSocket 服务器已开放在端口： " + getPort());
    }

    @Override
    public void stop() throws InterruptedException {
        super.stop();
    }

    public void sendAllInfo(WebSocket conn) {
        conn.send(JsonUtil.toJson(createPlayerListsMessage()));
        conn.send(JsonUtil.toJson(createWeatherMessage()));
    }

    public void broadcastPlayerLists() {
        broadcast(JsonUtil.toJson(createPlayerListsMessage()));
    }

    public void broadcastWeather(String weather) {
        broadcast(JsonUtil.toJson(createWeatherMessage(weather)));
    }

    public void broadcastGameTime(long time) {
        Map<String, Object> message = Map.of(
                "type", "gameTime",
                "data", time
        );
        broadcast(JsonUtil.toJson(message));
    }

    private Map<String, Object> createPlayerListsMessage() {
        return Map.of(
                "type", "playerLists",
                "online", plugin.getPlayerService().getOnlinePlayers(),
                "offline", plugin.getPlayerService().getOfflinePlayers()
        );
    }

    private Map<String, Object> createWeatherMessage() {
        String weather = Objects.requireNonNull(Bukkit.getWorld("world")).hasStorm() ? "rainy" : "clear";
        return createWeatherMessage(weather);
    }

    private Map<String, Object> createWeatherMessage(String weather) {
        return Map.of(
                "type", "weather",
                "data", weather
        );
    }
}