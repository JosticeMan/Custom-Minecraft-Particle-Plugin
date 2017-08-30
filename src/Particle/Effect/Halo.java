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

public class Halo implements CommandExecutor, Listener{
	
	Main plugin; 
	public Halo(Main passedPlugin) {
	this.plugin = passedPlugin;
	}
	
    @EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to do this!");
			return true;
		}
		if(command.getName().equalsIgnoreCase("Halo")) {
			Player player = (Player) sender;
			if (player.hasPermission("funparticle.Halo")){
				String uidd = player.getUniqueId().toString();
				int Flames = plugin.getConfig().getInt("Players." + uidd + ".Flames");
				int Hearts = plugin.getConfig().getInt("Players." + uidd + ".Hearts");
				int Rainbow = plugin.getConfig().getInt("Players." + uidd + ".Rainbow");
				int Halo = plugin.getConfig().getInt("Players." + uidd + ".Halo");
				int SnowFairy = plugin.getConfig().getInt("Players." + uidd + ".SnowFairy");
				if(Halo < 1 && Flames < 1 && Hearts < 1 && Rainbow < 1 && SnowFairy < 1) {
					final int tid = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin , new Runnable() {
    					double r = 0.35;
    					double t = 0;
                        @Override
                        public void run(){
        				Location loc = player.getLocation().add(0,2.25,0);
        				double angle = 10;
        				for (int degree = 0; degree < 360; degree += angle) {
                        t = t + Math.PI/16;
                        double x = r*Math.cos(t);
                        double z = r*Math.sin(t);
                        loc.add(x,0,z);
                        ParticleEffect.END_ROD.display(0, 0, 0, 0, 1, loc , 50);
                        loc.subtract(x,0,z);
        				}
                    }
	                },0 ,40);
                player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.BLUE + " The Halo particle effect has been enabled for you!");
                plugin.getConfig().set("Players." + uidd + ".Halo", Halo + 1);
                plugin.saveConfig();
                plugin.taskID4.put(player.getName(), tid);
				}
		        if(Halo > 0) {
		        if(plugin.taskID4.containsKey(player.getName())) {
		        		    int tid = plugin.taskID4.get(player.getName()); 
		        		    plugin.getServer().getScheduler().cancelTask(tid);
		        		    plugin.taskID4.remove(player.getName());
		        }
				player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.BLUE + " The Halo particle effect has been disabled for you!");
				plugin.getConfig().set("Players." + uidd + ".Halo", Halo -1);
				plugin.saveConfig();
			}
			    if(Halo < 1 && Flames < 1 && Hearts < 1 && Rainbow > 0 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Halo < 1 && Flames < 1 && Hearts < 0 && Rainbow < 1 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Halo < 1 && Flames > 0 && Hearts < 1 && Rainbow < 1 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Halo < 1 && Flames < 1 && Hearts < 1 && Rainbow < 1 && SnowFairy > 0) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
}
			else player.sendMessage(ChatColor.RED + "You do not have permission to do this!");

    }
		return false;
}
}
