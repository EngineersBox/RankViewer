package me.engineersbox.rankviewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import me.lucko.luckperms.api.Contexts;
import me.lucko.luckperms.api.Group;
import me.lucko.luckperms.api.User;
import me.lucko.luckperms.api.caching.MetaData;

public class GroupPlugins {

	public static List<String> pexGetGroups(Player p) {
		
		PermissionUser user2 = PermissionsEx.getUser(p);
		
		List<String> groups = new ArrayList<>();
		List<String> g2 = user2.getOwnParentIdentifiers();
		
		for (int i = 0; i < g2.size(); i++) {
			
			groups.add(g2.get(i).toString());
		}
		
		return groups;
		
	}
	
	public static List<String> pexGetGroupPrefixes(Player p) {
		
		PermissionUser user2 = PermissionsEx.getUser(p);
		
		List<String> g2 = user2.getOwnParentIdentifiers();
		List<String> groupPrefix = new ArrayList<>();
		
		for (int i = 0; i < g2.size(); i++) {
			
			groupPrefix.add(Main.format(user2.getParents().get(i).getPrefix()));
		}
		
		return groupPrefix;
		
	}
	
	public static boolean lpInGroup(Player p, String group) {
		
		User user = Main.api.getUser(p.getUniqueId());
		Set<Group> groups = Main.api.getGroups();
		boolean groupCount = false;
		for (Group cGroup : groups) {
			if ((user.inheritsGroup(cGroup)) && (!cGroup.getName().equalsIgnoreCase(group))) {
				groupCount = true;
			}
		}
		
		return groupCount;
		
	}
	
	public static Integer lpGetGroupCount(Player p) {
		
		User user = Main.api.getUser(p.getUniqueId());
		Set<Group> groups = Main.api.getGroups();
		Integer groupCount = 0;
		for (Group cGroup : groups) {
			if ((user.inheritsGroup(cGroup)) && (!cGroup.getName().equalsIgnoreCase("default"))) {
				groupCount += 1;
			}
		}
		
		return groupCount;
		
	}
	
	public static List<String> lpGetGroups(Player p) {
		
		User user = Main.api.getUser(p.getUniqueId());
		Set<Group> groups = Main.api.getGroups();
		List<String> retGroups = new ArrayList<String>();
		for (Group cGroup : groups) {
			if ((user.inheritsGroup(cGroup)) && (!cGroup.getName().equalsIgnoreCase("default"))) {
				retGroups.add(cGroup.getName());
				p.sendMessage(cGroup.getName());
			}
		}
		
		return retGroups;
	}
	
	public static List<String> lpGetGroupPrefixes(Player p) {
		
		User user = Main.api.getUser(p.getUniqueId());
		Contexts contexts = Main.api.getContextManager().getApplicableContexts(p);
		MetaData metaData;
		Set<Group> groups = Main.api.getGroups();
		List<String> retGroups = new ArrayList<String>();
		for (Group cGroup : groups) {
			if ((user.inheritsGroup(cGroup)) && (!cGroup.getName().equalsIgnoreCase("default"))) {
				metaData = cGroup.getCachedData().getMetaData(contexts);
				if (metaData.getPrefix() != null) {
					retGroups.add(Main.format(metaData.getPrefix()));
					p.sendMessage(Main.format(metaData.getPrefix()));
				} else {
					retGroups.add(Main.format("&f[" + cGroup.getName() + "]"));
					p.sendMessage(Main.format(metaData.getPrefix()));
				}
			}
		}
		
		return retGroups;
		
	}
	
	public static String lpGetUserPrefix(Player p) {
		
		User user = Main.api.getUser(p.getUniqueId());
		Contexts contexts = Main.api.getContextManager().getApplicableContexts(p);
		MetaData metaData = user.getCachedData().getMetaData(contexts);
		return Main.format(metaData.getPrefix());
		
	}
	
}
