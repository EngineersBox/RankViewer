package me.engineersbox.rankviewer;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import me.engineersbox.rankviewer.Main;

public class AbstractFile {
   
    protected static Main main;
    static File file;
    protected static FileConfiguration config;
   
    public AbstractFile(Main main, String filename) {
        AbstractFile.main = main;
        AbstractFile.file = new File(main.getDataFolder(), filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AbstractFile.config = YamlConfiguration.loadConfiguration(file);
        config.addDefault("Default Group Name", "defualt");
		config.addDefault("Links.Color", "&9");
		config.addDefault("Links.Underline", true);
        config.options().copyDefaults(true);
        AbstractFile.saveConfig();
    }
   
    public static void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
}