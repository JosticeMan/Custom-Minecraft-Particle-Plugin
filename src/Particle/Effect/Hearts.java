package Particle.Effect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import Particle.Main;
import Particle.ParticleEffect;

public class Hearts implements CommandExecutor, Listener {
	
	Main plugin; 
	public Hearts(Main passedPlugin) {
	this.plugin = passedPlugin;
	}

    @EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to do this!");
			return false;
		}
		if(command.getName().equalsIgnoreCase("Hearts")) {
			Player player = (Player) sender;
			if (player.hasPermission("funparticle.hearts")){
				String uidd = player.getUniqueId().toString();
				int Hearts = plugin.getConfig().getInt("Players." + uidd + ".Hearts");
				int Flames = plugin.getConfig().getInt("Players." + uidd + ".Flames");
				int Rainbow = plugin.getConfig().getInt("Players." + uidd + ".Rainbow");
				int Halo = plugin.getConfig().getInt("Players." + uidd + ".Halo");
				int SnowFairy = plugin.getConfig().getInt("Players." + uidd + ".SnowFairy");
				if(Hearts < 1 && Flames < 1 && Rainbow < 1 && Halo < 1 && SnowFairy < 1) {
					final int tid = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
    					double t = 0;
    					double r = 1;                	
	                	@Override
	                	public void run() {
	        				Location loc = player.getLocation().add(0,1,0);
	                        t = t + Math.PI/12;
	                        double x = r*Math.cos(t);
	                        double y = r*Math.cos(t);
	                        double z = r*Math.sin(t);
	                        loc.add(x, y, z);
	                        ParticleEffect.HEART.display(0, 0, 0, 1, 1, loc, 50);
	                        loc.subtract(x, y, z);
	                	}
	                },0 ,0);
					final int tid2 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {              	
	                	@Override
	                	public void run() {
	                		ParticleEffect.HEART.display(0, 0, 0, 1, 1, player.getLocation().add(0,2,0), 50);
	                		ParticleEffect.HEART.display(0, 0, 0, 1, 1, player.getLocation().add(1,0,0), 50);
	                		ParticleEffect.HEART.display(0, 0, 0, 1, 1, player.getLocation().add(0,0,1), 50);
	                		ParticleEffect.HEART.display(0, 0, 0, 1, 1, player.getLocation().add(-1,0,0), 50);
	                		ParticleEffect.HEART.display(0, 0, 0, 1, 1, player.getLocation().add(0,0,-1), 50);
	                	}
	                },0 ,20);
                player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.BLUE + " The Heart particle effect has been enabled for you!");
                plugin.getConfig().set("Players." + uidd + ".Hearts", Hearts + 1);
                plugin.saveConfig();
                plugin.taskID2.put(player.getName(), tid);
                plugin.taskID6.put(player.getName(), tid2);
				}
			    if(Hearts > 0) {
			        if(plugin.taskID2.containsKey(player.getName())) {
	        		    int tid = plugin.taskID2.get(player.getName()); 
	        		    plugin.getServer().getScheduler().cancelTask(tid);
	        		    plugin.taskID2.remove(player.getName());
	        }
			        if(plugin.taskID6.containsKey(player.getName())) {
	        		    int tid2 = plugin.taskID6.get(player.getName()); 
	        		    plugin.getServer().getScheduler().cancelTask(tid2);
	        		    plugin.taskID6.remove(player.getName());
	        }
					player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.BLUE + " The Heart particle effect has been disabled for you!");
					plugin.getConfig().set("Players." + uidd + ".Hearts", Hearts -1);
					plugin.saveConfig();
				}
			    if(Hearts < 1 && Flames > 0 && Rainbow < 1 && Halo < 1 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Hearts < 1 && Flames < 1 && Rainbow > 0 && Halo < 1 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Hearts < 1 && Flames < 1 && Rainbow < 1 && Halo > 0 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Hearts < 1 && Flames < 1 && Rainbow < 1 && Halo < 1 && SnowFairy > 0) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
}
			else player.sendMessage(ChatColor.RED + "You do not have permission to do this!");

    }
		return false;
}
}

