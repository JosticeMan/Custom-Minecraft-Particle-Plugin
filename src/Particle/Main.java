package Particle;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import Particle.Effect.Flames;
import Particle.Effect.Hearts;
import Particle.Effect.Rainbow;
import Particle.Effect.SnowFairy;
import Particle.Effect.Halo;


public class Main extends JavaPlugin implements Listener {
	
	public static Main pl;
	
	public static Main getPL() {
		return pl;
	}
	public Map<String, Integer> taskID = new HashMap<String, Integer>();
	public Map<String, Integer> taskID1 = new HashMap<String, Integer>();
	public Map<String, Integer> taskID2 = new HashMap<String, Integer>();
	public Map<String, Integer> taskID3 = new HashMap<String, Integer>();
	public Map<String, Integer> taskID4 = new HashMap<String, Integer>();
	public Map<String, Integer> taskID5 = new HashMap<String, Integer>();
	public Map<String, Integer> taskID6 = new HashMap<String, Integer>();
	
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this,this);
		this.getCommand("Flames").setExecutor(new Flames(this));
		this.getCommand("Hearts").setExecutor(new Hearts(this));
		this.getCommand("Rainbow").setExecutor(new Rainbow(this));
		this.getCommand("Halo").setExecutor(new Halo(this));
		this.getCommand("SnowFairy").setExecutor(new SnowFairy(this));

	}
	public void onDisable() {
		
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(!getConfig().contains("Players." + p.getUniqueId().toString())) {	
			getConfig().set("Players." + p.getUniqueId().toString() + ".Flames", 0);
			getConfig().set("Players." + p.getUniqueId().toString() + ".Hearts", 0);
			getConfig().set("Players." + p.getUniqueId().toString() + ".Rainbow", 0);
			getConfig().set("Players." + p.getUniqueId().toString() + ".Halo", 0);
			getConfig().set("Players." + p.getUniqueId().toString() + ".SnowFairy", 0);
			saveConfig();
		}
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
        if(taskID.containsKey(player.getName())) {
		    int tid = taskID.get(player.getName()); 
		    getServer().getScheduler().cancelTask(tid);
		    taskID.remove(player.getName());
}
        if(taskID2.containsKey(player.getName())) {
		    int tid = taskID2.get(player.getName()); 
		    getServer().getScheduler().cancelTask(tid);
		    taskID2.remove(player.getName());
}
        if(taskID1.containsKey(player.getName())) {
		    int tid = taskID1.get(player.getName()); 
		    getServer().getScheduler().cancelTask(tid);
		    taskID1.remove(player.getName());
}
        if(taskID3.containsKey(player.getName())) {
		    int tid2 = taskID3.get(player.getName()); 
		    getServer().getScheduler().cancelTask(tid2);
		    taskID3.remove(player.getName());
        }
        if(taskID4.containsKey(player.getName())) {
		    int tid = taskID4.get(player.getName()); 
		    getServer().getScheduler().cancelTask(tid);
		    taskID4.remove(player.getName());
}
        if(taskID5.containsKey(player.getName())) {
		    int tid = taskID5.get(player.getName()); 
		    getServer().getScheduler().cancelTask(tid);
		    taskID5.remove(player.getName());
}
        if(taskID6.containsKey(player.getName())) {
		    int tid2 = taskID6.get(player.getName()); 
		    getServer().getScheduler().cancelTask(tid2);
		    taskID6.remove(player.getName());
}
		String uidd = player.getUniqueId().toString();
		int Flames = getConfig().getInt("Players." + uidd + ".Flames");
		int Hearts = getConfig().getInt("Players." + uidd + ".Hearts");
		int Rainbow = getConfig().getInt("Players." + uidd + ".Rainbow");
		int Halo = getConfig().getInt("Players." + uidd + ".Halo");
		int SnowFairy = getConfig().getInt("Players." + uidd + ".SnowFairy");
		if(Flames > 0 && Rainbow < 1 && Hearts < 1 && Halo < 1 && SnowFairy < 1) {
			getConfig().set("Players." + uidd + ".Flames", Flames -1);
			saveConfig();
		}
		if(Rainbow > 0 && Flames < 1 && Hearts < 1 && Halo < 1 && SnowFairy < 1) {
			getConfig().set("Players." + uidd + ".Rainbow", Rainbow -1);
			saveConfig();
		}
		if(Hearts > 0 && Flames < 1 && Rainbow < 1 && Halo < 1 && SnowFairy < 1) {
			getConfig().set("Players." + uidd + ".Hearts", Hearts -1);
			saveConfig();
		}
		if(Halo > 0 && Flames < 1 && Rainbow < 1 && Hearts < 1 && SnowFairy < 1) {
			getConfig().set("Players." + uidd + ".Halo", Halo -1);
			saveConfig();
		}
		if(Halo < 1 && Flames < 1 && Rainbow < 1 && Hearts < 1 && SnowFairy > 0) {
			getConfig().set("Players." + uidd + ".SnowFairy", SnowFairy -1);
			saveConfig();
		}
		
	}

}







                            


