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
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.util.DiscordUtil;
import me.engineersbox.rankviewer.AbstractFile;
import me.engineersbox.rankviewer.Config;
import me.engineersbox.rankviewer.updater.SpigotUpdater;
import me.engineersbox.rankviewer.updater.Updaters;
import me.lucko.luckperms.api.LuckPermsApi;
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
	public static LuckPermsApi api;
	
	@Override
    public void onEnable() {
        
		if (!getDataFolder().exists()) {
    		getDataFolder().mkdirs();
    		
    	}
		try {
			RegisteredServiceProvider<LuckPermsApi> provider = Bukkit.getServicesManager().getRegistration(LuckPermsApi.class);
			if (provider != null) {
				api = provider.getProvider();
			}
		} catch (NoClassDefFoundError e) {
			//None
		}
    	
    	new Config(this);
    	
    	SpigotUpdater updater = new SpigotUpdater(this, 61256);
    	Updaters.checkVersion(updater);
		
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
		if (msg != null) {
			for(int i = 0; i < msg.length(); i++)
			{
			    if(msg.charAt(i) == '&')
			        coloredMsg += '§';
			    else
			        coloredMsg += msg.charAt(i);
			}
		} else {
			coloredMsg = "§f";
		}

		return coloredMsg;
    	
    }
    
    @EventHandler
    public static void onPlayerChat(AsyncPlayerChatEvent e) {
    	
		Player p = e.getPlayer();
		
		PermissionUser user2 = null;
		String username = null;
		boolean pluginPex = false;
		boolean pluginLp = false;
		if (Bukkit.getPluginManager().getPlugin("PermissionsEx") != null) {
			user2 = PermissionsEx.getUser(p);
			username = user2.getName();
			pluginPex = true;
		} else if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null) {
			username = p.getName();
			pluginLp = true;
		}
		
		String ChatMessage = e.getMessage();
		e.setCancelled(true);
		String[] chatSplit = ChatMessage.split(" ");
		TextComponent msgToPlayer = new TextComponent();
		String bukkitConsoleText = "";
		
		if (((pluginPex == true) && (user2.inGroup(Config.getDefaultGroup().toString())) && (user2.getOwnParentIdentifiers().size() < 1)) | ((pluginLp == true) && (GroupPlugins.lpInGroup(p, Config.getDefaultGroup().toString())) && (GroupPlugins.lpGetGroupCount(p) < 1))) {
			
			String prefixDefault = null;
			if (pluginPex == true) {
				prefixDefault = format(user2.getPrefix());
			} else if (pluginLp == true) {
				prefixDefault = GroupPlugins.lpGetGroupPrefixes(p).get(0);
			}
			ArrayList<Object> components = new ArrayList<>();
			
			TextComponent rTab = new TextComponent(format(Config.getTabFormat().toString()) + " " + ChatColor.WHITE);
			TextComponent hoverMessage = new TextComponent(new ComponentBuilder(prefixDefault + "No Rank").create());
			components.add(hoverMessage);
			BaseComponent[] hoverToSend = (BaseComponent[]) components.toArray(new BaseComponent[components.size()]);
			
			rTab.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverToSend));
			
			TextComponent comp2 = new TextComponent(ChatColor.getLastColors(prefixDefault) + username + ChatColor.WHITE +": ");
			
			msgToPlayer.addExtra(rTab);
			msgToPlayer.addExtra(comp2);
			
			bukkitConsoleText += prefixDefault + comp2.toLegacyText();
			
		} else {
			
			List<String> groups = null;
			List<String> groupPrefix = null;
			String prefixUser = null;
			if (pluginPex == true) {
				prefixUser = format(user2.getPrefix());
				groups = GroupPlugins.pexGetGroups(p);
				groupPrefix = GroupPlugins.pexGetGroupPrefixes(p);
			} else if (pluginLp == true) {
				prefixUser = GroupPlugins.lpGetUserPrefix(p);
				groups = GroupPlugins.lpGetGroups(p);
				groupPrefix = GroupPlugins.lpGetGroupPrefixes(p);
			}
			
			ArrayList<Object> components = new ArrayList<>();
			
			TextComponent rTab = new TextComponent(format(Config.getTabFormat().toString()) + " " + ChatColor.WHITE);
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
			rTab.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverToSend));
			TextComponent comp2 = new TextComponent(ChatColor.getLastColors(prefixUser) + username + ChatColor.WHITE +": ");
			
			msgToPlayer.addExtra(rTab);
			msgToPlayer.addExtra(comp2);
			
			bukkitConsoleText += prefixUser + comp2.toLegacyText();
			
		}
		
		for (String val : chatSplit) {
			
			if ((val.contains("https://")) || (val.contains("http://")) || (val.contains("www."))) {
				TextComponent linkClickable = new TextComponent(ComponentSerializer.parse("{text: \"" + format(Config.getData("Links.Color").toString()) + format(Config.getULine().toString()) + val + "\",clickEvent:{action:open_url,value:\"" + val + "\"}} "));
				linkClickable.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, val));
				msgToPlayer.addExtra(linkClickable);
				msgToPlayer.addExtra(ChatColor.RESET + " " + ChatColor.WHITE);
				bukkitConsoleText += linkClickable.toLegacyText();
			} else {
				TextComponent msgContent = new TextComponent(new ComponentBuilder(format(val)).create());
				msgToPlayer.addExtra(msgContent);
				msgToPlayer.addExtra(" ");
				bukkitConsoleText += msgContent.toLegacyText();
			}
			
		}
		
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			
			player.spigot().sendMessage(msgToPlayer);

		}
		
		Bukkit.getConsoleSender().sendMessage(bukkitConsoleText);
		
		if ((Bukkit.getServer().getPluginManager().getPlugin("DiscordSRV") != null) && (p.hasPermission("rv.discord"))) {
			
			if (Config.getDCConfig().equals(true)) {
				DiscordUtil.sendMessage(DiscordSRV.getPlugin().getMainTextChannel(), username + " » " + ChatMessage);
			} else if (Config.getDCConfig().equals(false)) {
				DiscordUtil.sendMessage(DiscordSRV.getPlugin().getDestinationTextChannelForGameChannelName(Config.getDCChannel().toString()), username + " » " + ChatMessage);
			} else {
				Bukkit.getLogger().warning("[RankViewer] Config Option 'Use Main Discord Channel' is not of type boolean");
			}
			
		}		
		
		
	}
    
}