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

public class Rainbow implements CommandExecutor, Listener {
	
	Main plugin; 
	public Rainbow(Main passedPlugin) {
	this.plugin = passedPlugin;
	}
    @EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to do this!");
			return true;
		}
		if(command.getName().equalsIgnoreCase("Rainbow")) {
			Player player = (Player) sender;
			if (player.hasPermission("funparticle.rainbow")){
				String uidd = player.getUniqueId().toString();
				int Flames = plugin.getConfig().getInt("Players." + uidd + ".Flames");
				int Hearts = plugin.getConfig().getInt("Players." + uidd + ".Hearts");
				int Rainbow = plugin.getConfig().getInt("Players." + uidd + ".Rainbow");
				int Halo = plugin.getConfig().getInt("Players." + uidd + ".Halo");
				int SnowFairy = plugin.getConfig().getInt("Players." + uidd + ".SnowFairy");
				if(Rainbow < 1 && Flames < 1 && Hearts < 1 && Halo < 1 && SnowFairy < 1) {
				final int tid2 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin , new Runnable() {
					double t = 0;
					double r = 1;
                    @Override
                    public void run(){
    				Location loc = player.getLocation().add(0,1,0);
                    t = t + Math.PI/12;
                    double x = r*Math.cos(t);
                    double y = r*Math.cos(t);
                    double z = r*Math.sin(t);
                    loc.add(x,y,z);
                    ParticleEffect.SPELL_MOB.display(0, 0, 0, 10, 2, loc , 50);
                    loc.subtract(x,y,z);
                    }
	                },0 ,1);
				final int tid = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin , new Runnable() {
					double t = 0;
					double r = 1;
                    @Override
                    public void run(){
    				Location loc = player.getLocation().add(0,1,0);
                    t = t + Math.PI/12;
                    double x = r*-Math.cos(t);
                    double y = r*Math.cos(t);
                    double z = r*-Math.sin(t);
                    loc.add(x,y,z);
                    ParticleEffect.SPELL_MOB.display(0, 0, 0, 10, 2, loc , 50);
                    loc.subtract(x,y,z);
                    }
	                },0 ,1);
                player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.BLUE + " The Rainbow effect has been enabled for you!");
                plugin.getConfig().set("Players." + uidd + ".Rainbow", Rainbow + 1);
                plugin.saveConfig();
                plugin.taskID1.put(player.getName(), tid);
                plugin.taskID3.put(player.getName(), tid2);
				}
			    if(Rainbow > 0) {
			        if(plugin.taskID1.containsKey(player.getName())) {
	        		    int tid = plugin.taskID1.get(player.getName()); 
	        		    plugin.getServer().getScheduler().cancelTask(tid);
	        		    plugin.taskID1.remove(player.getName());
	        }
			        if(plugin.taskID3.containsKey(player.getName())) {
	        		    int tid2 = plugin.taskID3.get(player.getName()); 
	        		    plugin.getServer().getScheduler().cancelTask(tid2);
	        		    plugin.taskID3.remove(player.getName());
			        }
					player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.BLUE + " The Rainbow effect has been disabled for you!");
					plugin.getConfig().set("Players." + uidd + ".Rainbow", Rainbow -1);
					plugin.saveConfig();
				}
			    if(Rainbow < 1 && Flames > 0 && Hearts < 1 && Halo < 1 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Rainbow < 1 && Flames < 1 && Hearts > 0 && Halo < 1 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Rainbow < 1 && Flames < 1 && Hearts < 1 && Halo > 0 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Rainbow < 1 && Flames < 1 && Hearts < 1 && Halo < 1 && SnowFairy > 0) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
}
			else player.sendMessage(ChatColor.RED + "You do not have permission to do this!");

    }
		return false;
}
}
