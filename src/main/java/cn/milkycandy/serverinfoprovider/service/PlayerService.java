package cn.milkycandy.serverinfoprovider.service;

import cn.milkycandy.serverinfoprovider.ProviderCore;
import cn.milkycandy.serverinfoprovider.model.PlayerData;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerService {

    private final ProviderCore plugin;
    private final Map<UUID, PlayerData> playerDataMap = new ConcurrentHashMap<>();

    public PlayerService(ProviderCore plugin) {
        this.plugin = plugin;
    }

    public void updatePlayer(Player player) {

        PlayerData playerData = new PlayerData(
                player.getUniqueId(),
                player.getName(),
                player.getWorld().getName(),
                System.currentTimeMillis(),
                true
        );
        playerDataMap.put(player.getUniqueId(), playerData);

        printPlayerDataMap(); // 调试用，打印当前玩家数据
    }

    // 更新离线玩家状态
    public void setPlayerOffline(UUID playerId) {
        PlayerData playerData = playerDataMap.get(playerId);
        if (playerData != null) {
            playerData.setOnline(false);
            playerData.setLastSeen(System.currentTimeMillis());
            playerDataMap.put(playerId, playerData);
        }
    }

    public List<PlayerData> getOnlinePlayers() {
        List<PlayerData> onlinePlayers = new ArrayList<>();
        for (PlayerData playerData : playerDataMap.values()) {
            if (playerData.isOnline()) {
                onlinePlayers.add(playerData);
            }
        }
        return onlinePlayers;
    }

    public List<PlayerData> getOfflinePlayers() {
        List<PlayerData> offlinePlayers = new ArrayList<>();
        for (PlayerData playerData : playerDataMap.values()) {
            if (!playerData.isOnline()) {
                offlinePlayers.add(playerData);
            }
        }
        return offlinePlayers;
    }

    private void printPlayerDataMap() {
        StringBuilder mapInfo = new StringBuilder("[信息提供器]Current PlayerDataMap:\n");
        for (Map.Entry<UUID, PlayerData> entry : playerDataMap.entrySet()) {
            mapInfo.append("Player UUID: ").append(entry.getKey()).append(", ")
                    .append("Data: ").append(entry.getValue()).append("\n");
        }
        plugin.getLogger().info(mapInfo.toString());
    }
}