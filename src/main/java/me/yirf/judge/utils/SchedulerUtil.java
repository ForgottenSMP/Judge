package me.yirf.judge.utils;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import me.yirf.judge.Judge;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import java.util.function.Consumer;

public class SchedulerUtil {

    public static boolean isFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static void runEntity(Entity entity, Runnable runnable) {
        if (isFolia()) {
            entity.getScheduler().run(Judge.instance, task -> runnable.run(), null);
            return;
        }

        Bukkit.getScheduler().runTask(Judge.instance, runnable);
    }

    public static void runEntityLater(Entity entity, Runnable runnable, long delay) {
        if (isFolia()) {
            entity.getScheduler().runDelayed(Judge.instance, task -> runnable.run(), null, delay);
            return;
        }

        Bukkit.getScheduler().runTaskLater(Judge.instance, runnable, delay);
    }

    public static void runGlobalTimer(Consumer<ScheduledTask> runnable, long delay, long period) {
        if (isFolia()) {
            Bukkit.getGlobalRegionScheduler().runAtFixedRate(Judge.instance, runnable, delay, period);
            return;
        }

        Bukkit.getScheduler().runTaskTimer(Judge.instance, () -> runnable.accept(null), delay, period);
    }
}
