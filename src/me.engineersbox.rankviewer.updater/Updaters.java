package me.engineersbox.rankviewer.updater;

import org.bukkit.Bukkit;

public class Updaters {

	public static void checkVersion(SpigotUpdater updater) {
		
    	Bukkit.getLogger().info("[Rank Viewer] Checking for updates...");
    	try {
			if (Class.forName("org.spigotmc.SpigotConfig") != null) {
				if (updater.checkForUpdates()) {
					Bukkit.getLogger().info("[Rank Viewer] An update was found! New version: " + updater.getLatestVersion() + " download: " + updater.getResourceURL());
				}
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			Bukkit.getLogger().warning("[Rank Viewer] Could not check for updates! Stacktrace:");
			e.printStackTrace();
		}
    	
    }
	
}
