package me.engineersbox.rankviewer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.util.DiscordUtil;

import net.md_5.bungee.api.chat.BaseComponent;
//import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Main extends JavaPlugin implements Listener {
	
	public static String prefix = ChatColor.AQUA + "[" + ChatColor.BLUE + "Rank Viewer" + ChatColor.AQUA + "] ";
	
	@Override
    public void onEnable() {
        
    	Bukkit.getServer().getPluginManager().registerEvents(this, this);
        getCommand("rv version").setExecutor(new Commands());
        getCommand("rv help").setExecutor(new Commands());
        getCommand("rv reload").setExecutor(new Commands());
        getCommand("rv").setExecutor(new Commands());
        
    }
    
    @Override
    public void onDisable() {
    	
    }
    
    public static String format(String msg) {
    	
		String coloredMsg = "";
		for(int i = 0; i < msg.length(); i++)
		{
		    if(msg.charAt(i) == '&')
		        coloredMsg += '§';
		    else
		        coloredMsg += msg.charAt(i);
		}
		return coloredMsg;
    	
    }
    
    @EventHandler
    public static void onPlayerChat(AsyncPlayerChatEvent e) {
		
		Player p = e.getPlayer();
		
		PermissionUser user2 = PermissionsEx.getUser(p);
		
		List<String> groups = new ArrayList<>();
		List<String> g2 = user2.getOwnParentIdentifiers();
		String prefix = format(user2.getPrefix());
		String username = user2.getName();
		for (int i = 0; i < g2.size(); i++) {
			groups.add(g2.get(i).toString());
		}
		
		String ChatMessage = e.getMessage();
		e.setCancelled(true);
		
		TextComponent comp = new TextComponent(ChatColor.AQUA + "[" + ChatColor.RED + "R" + ChatColor.AQUA + "] " + ChatColor.WHITE);
		
		ArrayList<Object> components = new ArrayList<>();
		TextComponent hoverMessage = new TextComponent(new ComponentBuilder(groups.get(0)).create());
		TextComponent newLine = new TextComponent(ComponentSerializer.parse("{text: \"\n\"}"));
		
		for (int i = 1; i < groups.size(); i++) {
			
			hoverMessage.addExtra(newLine);
			hoverMessage.addExtra(new TextComponent(new ComponentBuilder(groups.get(i)).create()));
			
		}
		
		components.add(hoverMessage);
		BaseComponent[] hoverToSend = (BaseComponent[])components.toArray(new BaseComponent[components.size()]);
		
		comp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverToSend));
		
		TextComponent comp2 = new TextComponent(prefix + username + ChatColor.WHITE +": ");
		comp2.addExtra(ChatMessage);

		for (Player player : Bukkit.getOnlinePlayers()) {
			
			player.spigot().sendMessage(comp, comp2);
			e.setMessage(ChatMessage);

		}
		
		DiscordUtil.sendMessage(DiscordSRV.getPlugin().getMainTextChannel(), "**" + groups.get(0).toString() + "** " + username + " » " + ChatMessage);
		
	}
    
}