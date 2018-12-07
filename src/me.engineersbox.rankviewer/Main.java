package me.engineersbox.rankviewer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.util.DiscordUtil;
import me.engineersbox.rankviewer.AbstractFile;
import me.engineersbox.rankviewer.Config;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Main extends JavaPlugin implements Listener {
	
	public static FileConfiguration config;
	public static File cfile;
	
	//Globals
	public static String prefix = ChatColor.AQUA + "[" + ChatColor.BLUE + "Rank Viewer" + ChatColor.AQUA + "] ";
	
	@Override
    public void onEnable() {
        
		if (!getDataFolder().exists()) {
    		getDataFolder().mkdirs();
    		
    	}
    	
    	new Config(this);
		
    	Bukkit.getServer().getPluginManager().registerEvents(this, this);
        getCommand("rv version").setExecutor(new Commands());
        getCommand("rv help").setExecutor(new Commands());
        getCommand("rv reload").setExecutor(new Commands());
        getCommand("rv").setExecutor(new Commands());
        
    }
    
    @Override
    public void onDisable() {
    	
    	AbstractFile.saveConfig();
    	
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
		
		String username = user2.getName();
		
		String ChatMessage = e.getMessage();
		e.setCancelled(true);
		
		String[] chatSplit;
		String convURL = "";
		Boolean hasURL = false;
		Boolean canSplit = false;
		
		if (ChatMessage.contains(" ")) {
			
			chatSplit = ChatMessage.split(" ");
			
			for (int i = 0; i < chatSplit.length; i++) {
				
				if ((chatSplit[i].contains("https")) || (chatSplit[i].contains("http"))) {
					
					convURL = chatSplit[i].toString();
					hasURL = true;
					canSplit = true;
					break;
					
				} else {
					
					hasURL = false;
					
				}
				
			}
			
		} else  if ((!ChatMessage.contains(" ")) && (ChatMessage.contains("https")) || (ChatMessage.contains("http"))) {
			
			convURL = ChatMessage;
			canSplit = false;
			hasURL = true;
			
		} else {
			
			canSplit = false;
			hasURL = false;
			
		}
		
		TextComponent linkClickable = new TextComponent(ComponentSerializer.parse("{text: \"" + format(Config.getData("Links.Color").toString()) + format(Config.getULine().toString()) + convURL + "\",clickEvent:{action:open_url,value:\"" + convURL + "\"}}"));
		
		if ((user2.inGroup("default")) && (user2.getOwnParentIdentifiers().size() < 1)) {
			
			/*p.sendMessage("no groups");
			List<String> g1 = user2.getOwnParentIdentifiers();
			for (int i = 0; i < g1.size(); i++) {
				p.sendMessage(g1.get(i).toString());
			}
			p.sendMessage(g1.toString());
			*/ //debug
			
			String prefixDefault = format(user2.getPrefix());
			ArrayList<Object> components2 = new ArrayList<>();
			
			TextComponent rTab = new TextComponent(format(Config.getTabFormat().toString()) + " " + ChatColor.WHITE);
			TextComponent hoverMessage2 = new TextComponent(new ComponentBuilder(prefixDefault + "No Rank").create());
			components2.add(hoverMessage2);
			BaseComponent[] hoverToSend2 = (BaseComponent[])components2.toArray(new BaseComponent[components2.size()]);
			
			rTab.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverToSend2));
			
			TextComponent comp2 = new TextComponent(ChatColor.getLastColors(prefixDefault) + username + ChatColor.WHITE +": ");
			TextComponent comp3 = new TextComponent();
			
			if ((hasURL.equals(true)) && (canSplit.equals(true))) {
				
				String[] messageSplit = ChatMessage.split(convURL);
				
				String messageFirst = messageSplit[0].toString();
				if (messageSplit.length == 2) {
					
					String messageSecond = messageSplit[1].toString();
					comp3.addExtra(messageSecond);
					
				} else {
					
					comp3.addExtra("");
					
				}
				
				comp2.addExtra(messageFirst);
				
			} else if ((hasURL.equals(true)) && (canSplit.equals(false))) {
				
				comp2.addExtra(linkClickable);
				
			} else {
				
				comp2.addExtra(ChatMessage);
				
			}
	
			for (Player player : Bukkit.getOnlinePlayers()) {
				
				if ((hasURL.equals(true)) && (canSplit.equals(true))) {
					
					player.spigot().sendMessage(rTab, comp2, linkClickable, comp3);
					
				} else {
					
					player.spigot().sendMessage(rTab, comp2);
					
				}
	
			}
			
			if ((Bukkit.getServer().getPluginManager().getPlugin("DiscordSRV") != null) && (p.hasPermission("rv.discord"))) {
				
				if (Config.getDCConfig().equals(true)) {
					
					DiscordUtil.sendMessage(DiscordSRV.getPlugin().getMainTextChannel(), username + " » " + ChatMessage);
					
				} else if (Config.getDCConfig().equals(false)) {
					
					DiscordUtil.sendMessage(DiscordSRV.getPlugin().getDestinationTextChannelForGameChannelName(Config.getDCChannel().toString()), username + " » " + ChatMessage);
					
				} else {
					
					Bukkit.getLogger().warning("[RankViewer] Config Option 'Use Main Discord Channel' is not of type boolean");
					
				}
				
			}
			
		} else {
			
			//p.sendMessage("Has groups");	//debug
			
			List<String> groups = GroupPlugins.pexGetGroups(p);
			List<String> groupPrefix = GroupPlugins.pexGetGroupPrefixes(p);
			String prefixUser = format(user2.getPrefix());
			
			ArrayList<Object> components = new ArrayList<>();
			
			TextComponent comp = new TextComponent(format(Config.getTabFormat().toString()) + " " + ChatColor.WHITE);
			TextComponent hoverMessage = new TextComponent("");
			TextComponent newLine = new TextComponent(ComponentSerializer.parse("{text: \"\n\"}"));
			
			if (Config.getGName().equals(true)) {
				
				hoverMessage.addExtra(new TextComponent(new ComponentBuilder(groupPrefix.get(0) + groups.get(0)).create()));
				
				for (int i = 1; i < groups.size(); i++) {
					
					hoverMessage.addExtra(newLine);
					hoverMessage.addExtra(new TextComponent(new ComponentBuilder(groupPrefix.get(i) + groups.get(i)).create()));
					
				}
				
			} else if (Config.getGName().equals(false)) {
				
				hoverMessage.addExtra(new TextComponent(new ComponentBuilder(groupPrefix.get(0)).create()));
				
				for (int i = 1; i < groups.size(); i++) {
					
					hoverMessage.addExtra(newLine);
					hoverMessage.addExtra(new TextComponent(new ComponentBuilder(groupPrefix.get(i)).create()));
					
				}
				
			} else {
				
				Bukkit.getLogger().warning("[RankViewer] Config Option 'Use group name' is not of type boolean");
				
			}
			
			
			components.add(hoverMessage);
			BaseComponent[] hoverToSend = (BaseComponent[])components.toArray(new BaseComponent[components.size()]);
			
			comp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverToSend));
			linkClickable.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, convURL));
			
			TextComponent comp2 = new TextComponent(ChatColor.getLastColors(prefixUser) + username + ChatColor.WHITE +": ");
			TextComponent comp3 = new TextComponent();
			
			if ((hasURL.equals(true)) && (canSplit.equals(true))) {
				
				String[] messageSplit = ChatMessage.split(convURL);
						
				String messageFirst = messageSplit[0].toString();
				if (messageSplit.length == 2) {
					
					String messageSecond = messageSplit[1].toString();
					comp3.addExtra(messageSecond);
					
				} else {
					
					comp3.addExtra("");
					
				}
				
				comp2.addExtra(messageFirst);
				
			} else if ((hasURL.equals(true)) && (canSplit.equals(false))) {
				
				comp2.addExtra(linkClickable);
				
			} else {
				
				comp2.addExtra(ChatMessage);
				
			};
	
			for (Player player : Bukkit.getOnlinePlayers()) {
				
				if ((hasURL.equals(true)) && (canSplit.equals(true))) {
					
					player.spigot().sendMessage(comp, comp2, linkClickable, comp3);
					
				} else {
					
					player.spigot().sendMessage(comp, comp2);
					
				}
	
			}
			
			if ((Bukkit.getServer().getPluginManager().getPlugin("DiscordSRV") != null) && (p.hasPermission("rv.discord"))) {
				
				if (Config.getDCConfig().equals(true)) {
					
					DiscordUtil.sendMessage(DiscordSRV.getPlugin().getMainTextChannel(), "**" + groups.get(0).toString() + "** " + username + " » " + ChatMessage);
					
				} else if (Config.getDCConfig().equals(false)) {
					
					DiscordUtil.sendMessage(DiscordSRV.getPlugin().getDestinationTextChannelForGameChannelName(Config.getDCChannel().toString()), "**" + groups.get(0).toString() + "** " + username + " » " + ChatMessage);
					
				} else {
					
					Bukkit.getLogger().warning("[RankViewer] Config Option 'Use Main Discord Channel' is not of type boolean");
					
				}
				
			}
			
		
    	}
		
	}
    
}