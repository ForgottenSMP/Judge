package me.yirf.judge.group;

import me.yirf.judge.utils.SchedulerUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

public class Group {

    public static Map<UUID, Entity> group = new ConcurrentHashMap<>();
    public static Map<UUID, Boolean> control = new ConcurrentHashMap<>();

    public static void add(Entity entity, Player p) {
        UUID pu = p.getUniqueId();
        group.put(pu, entity);
    }

    public static void remove(Player p) {
        Entity display = group.get(p.getUniqueId());
        if (display != null) {
            SchedulerUtil.runEntity(display, display::remove);
        }
        group.remove(p.getUniqueId());
    }

    public static boolean check(Player p) {
        return group.get(p.getUniqueId()) != null;
    }
}
