package Particle.Effect;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import Particle.Main;
import Particle.ParticleEffect;

public class Flames implements CommandExecutor, Listener {
	
	Main plugin; 
	public Flames(Main passedPlugin) {
	this.plugin = passedPlugin;
	}
    @EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to do this!");
			return true;
		}
		if(command.getName().equalsIgnoreCase("Flames")) {
			Player player = (Player) sender;
			if (player.hasPermission("funparticle.flames")){
				String uidd = player.getUniqueId().toString();
				int Flames = plugin.getConfig().getInt("Players." + uidd + ".Flames");
				int Hearts = plugin.getConfig().getInt("Players." + uidd + ".Hearts");
				int Rainbow = plugin.getConfig().getInt("Players." + uidd + ".Rainbow");
				int Halo = plugin.getConfig().getInt("Players." + uidd + ".Halo");
				int SnowFairy = plugin.getConfig().getInt("Players." + uidd + ".SnowFairy");
				if(Flames < 1 && Hearts < 1 && Rainbow < 1 && Halo < 1 && SnowFairy < 1) {
					final int tid = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin , new Runnable() {	
				
                    @Override
                    public void run(){
                       ParticleEffect.FLAME.display(0, 0, 0, 1, 10, player.getLocation(), 50);
                    }
	                },0 ,20);
                player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.BLUE + " The Flame particle effect has been enabled for you!");
                plugin.getConfig().set("Players." + uidd + ".Flames", Flames + 1);
                plugin.saveConfig();
                plugin.taskID.put(player.getName(), tid);
				}
		        if(Flames > 0) {
		        if(plugin.taskID.containsKey(player.getName())) {
		        		    int tid = plugin.taskID.get(player.getName()); 
		        		    plugin.getServer().getScheduler().cancelTask(tid);
		        		    plugin.taskID.remove(player.getName());
		        }
				player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.BLUE + " The Flame particle effect has been disabled for you!");
				plugin.getConfig().set("Players." + uidd + ".Flames", Flames -1);
				plugin.saveConfig();
			}
			    if(Flames < 1 && Hearts > 0 && Rainbow < 1 && Halo < 1 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Flames < 1 && Hearts < 1 && Rainbow > 0 && Halo < 1 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Flames < 1 && Hearts < 1 && Rainbow < 1 && Halo > 0 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Flames < 1 && Hearts < 1 && Rainbow < 1 && Halo < 1 && SnowFairy > 0) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
}
			else player.sendMessage(ChatColor.RED + "You do not have permission to do this!");

    }
		return false;
}
}
