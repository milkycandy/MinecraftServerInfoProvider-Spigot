package cn.milkycandy.serverinfoprovider.model;

import java.util.UUID;

public class PlayerData {
    private UUID uuid;
    private String name;
    private String world;
    private long lastSeen;
    private boolean online;

    public PlayerData(UUID uuid, String name, String world, long lastSeen, boolean online) {
        this.uuid = uuid;
        this.name = name;
        this.world = world;
        this.lastSeen = lastSeen;
        this.online = online;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public long getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(long lastSeen) {
        this.lastSeen = lastSeen;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", world='" + world + '\'' +
                ", lastSeen=" + lastSeen +
                ", online=" + online +
                '}';
    }
}