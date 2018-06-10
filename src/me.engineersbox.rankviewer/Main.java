package me.engineersbox.rankviewer;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Main extends JavaPlugin implements Listener {
	
	public static FileConfiguration config;
	public static File cfile;
	
	public static String prefix = ChatColor.AQUA + "[" + ChatColor.RED + "BlockPalette" + ChatColor.AQUA + "] ";
	
    public void onEnable() {
        
    	Bukkit.getServer().getPluginManager().registerEvents(this, this);
        getCommand("rv version").setExecutor(new Commands());
        getCommand("rv help").setExecutor(new Commands());
        
    }
    
    @Override
    public void onDisable() {
    	
    }
    
    @EventHandler
    public static void onPlayerChat(AsyncPlayerChatEvent e) {
		
		Player p = e.getPlayer();
		
		PermissionUser user2 = PermissionsEx.getUser(p);
		
		List<String> groups = user2.getParentIdentifiers();
		String username = user2.getName();
		
		String ChatMessage = e.getMessage();
		e.setCancelled(true);
		
		TextComponent comp = new TextComponent(ChatColor.AQUA + "[" + ChatColor.RED + "R" + ChatColor.AQUA + "] " + ChatColor.WHITE);
		comp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(groups.toString().replace("[]", "")).create()));
		TextComponent comp2 = new TextComponent("<" + username + "> ");
		comp2.addExtra(ChatMessage);
		
		p.spigot().sendMessage(comp, comp2);
		
	}
    
}