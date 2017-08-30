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
import org.bukkit.util.Vector;

import Particle.Main;
import Particle.ParticleEffect;

public class SnowFairy implements CommandExecutor, Listener {
	
	Main plugin; 
	public SnowFairy(Main passedPlugin) {
	this.plugin = passedPlugin;
	}
    @EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to do this!");
			return true;
		}
		if(command.getName().equalsIgnoreCase("SnowFairy")) {
			Player player = (Player) sender;
			if (player.hasPermission("funparticle.snowfairy")){
				String uidd = player.getUniqueId().toString();
				int Flames = plugin.getConfig().getInt("Players." + uidd + ".Flames");
				int Hearts = plugin.getConfig().getInt("Players." + uidd + ".Hearts");
				int Rainbow = plugin.getConfig().getInt("Players." + uidd + ".Rainbow");
				int Halo = plugin.getConfig().getInt("Players." + uidd + ".Halo");
				int SnowFairy = plugin.getConfig().getInt("Players." + uidd + ".SnowFairy");
				if(SnowFairy < 1 && Halo < 1 && Flames < 1 && Hearts < 1 && Rainbow < 1) {
					final int tid = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin , new Runnable() {

                        @Override
                        public void run(){
                        for (double t = 0; t < Math.PI * 5; t += Math.PI / 25) {
                        double yaw = player.getLocation().getYaw();
                        Location loc = player.getLocation().add(0,1,0);
                        double x = (Math.sin(t)*((Math.pow(Math.E, Math.cos(t))-(2*Math.cos(4*t))-(Math.pow(Math.sin(t/12), 5)))));
                        double y = (Math.cos(t)*((Math.pow(Math.E, Math.cos(t))-(2*Math.cos(4*t))-(Math.pow(Math.sin(t/12), 5)))));
                        Vector vec = new Vector(x, y, 0);
                        rotateAroundAxisY(vec, -yaw/180*Math.PI);
                        loc.add(vec);
                        ParticleEffect.END_ROD.display(0, 0, 0, 0, 2, loc , 50);
                        loc.subtract(vec);
                        
        				}  }                      
                    
	                },0 , 15);
                player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.BLUE + " The SnowFairy particle effect has been enabled for you!");
                plugin.getConfig().set("Players." + uidd + ".SnowFairy", SnowFairy + 1);
                plugin.saveConfig();
                plugin.taskID5.put(player.getName(), tid);
				}
		        if(SnowFairy > 0) {
		        if(plugin.taskID5.containsKey(player.getName())) {
		        		    int tid = plugin.taskID5.get(player.getName()); 
		        		    plugin.getServer().getScheduler().cancelTask(tid);
		        		    plugin.taskID5.remove(player.getName());
		        }
				player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.BLUE + " The SnowFairy particle effect has been disabled for you!");
				plugin.getConfig().set("Players." + uidd + ".SnowFairy", SnowFairy -1);
				plugin.saveConfig();
			}
			    if(Halo < 1 && Flames < 1 && Hearts < 1 && Rainbow > 0 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Halo < 1 && Flames < 1 && Hearts > 0 && Rainbow < 1 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Halo < 1 && Flames > 0 && Hearts < 1 && Rainbow < 1 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
			    if(Halo > 0 && Flames < 1 && Hearts < 1 && Rainbow < 1 && SnowFairy < 1) {
			    	player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Particles" + ChatColor.DARK_GREEN + "]" + ChatColor.RED + " You can only have 1 particle effect on at a time!");
			    }
}
			else player.sendMessage(ChatColor.RED + "You do not have permission to do this!");

    }
		return false;
}
    private Vector rotateAroundAxisY(Vector v, double b) {
        double x = Math.cos(b)*v.getX() + Math.sin(b)*v.getZ();
        double z = -Math.sin(b)*v.getX() + Math.cos(b)*v.getZ();
        return v.setX(x).setZ(z);
    }


}
