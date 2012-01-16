package com.Cayviel.FallBreaks;

import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.entity.CraftPlayer;


public class FallListener extends EntityListener{
	PluginManager pm;
	public FallListener(PluginManager pmo){
		pm=pmo;
	}
	String world = FallBreaks.properties.getProperty("level-name");
	World w;

	public void onEntityDamage(EntityDamageEvent Ifell){
		if (Ifell.getEntity() instanceof Player){
			if (Ifell.getCause().equals(DamageCause.FALL)){

				Player player = (Player)Ifell.getEntity();
			
				if (w == null){	w = player.getWorld();}
					
					Block BlockOn = player.getLocation().subtract(0, 1, 0).getBlock();
					
					if (FallBreaks.breakconfig.getbreak(BlockOn.getType().toString())){

						int booteq = getbooteq(player.getInventory().getBoots().getTypeId());
						int Blockreq = matreq(BlockOn.getTypeId());
						boolean canBreak = (Blockreq <= booteq);
						
						if ((BreakConfig.bootreq)&&(booteq==0)){return;}

						if (BreakConfig.bootmining){
							if (canBreak){
								bootmine(BlockOn, player);
							}
						}						
						if (BreakConfig.boottier){
							if (canBreak){
								BlockOn.setTypeId(0);
							}
						}else{BlockOn.setTypeId(0);}
					}
				}
			}
		}
	void bootmine (Block BlockOn, Player player){
			((CraftPlayer)player).getHandle().itemInWorldManager.c(BlockOn.getX(), BlockOn.getY(), BlockOn.getZ());
	}
	 int matreq(int itemid){
		 
		switch (itemid){
		
		case 01: return 1;
		case 16: return 1;
		case 23: return 1;
		case 24: return 1;
		case 25: return 1;
		case 43: return 1;
		case 30: return 1;
		case 44: return 1;
		case 45: return 1;
		case 48: return 1;
		case 61: return 1;
		case 62: return 1;
		case 71: return 1;
		case 101: return 1;
		case 112: return 1;
		case 113: return 1;
		case 116: return 1;
		case 117: return 1;
		case 121: return 1;
		
		//stairs
		case 53: return 1;
		case 67: return 1;
		case 108: return 1;
		case 109: return 1;
		case 114: return 1;

		
		case 15: return 2;
		case 21: return 2;
		case 22: return 2;
		case 118: return 2;
				
		case 14: return 3;
		case 41: return 3;
		case 42: return 3;
		case 56: return 3;
		case 73: return 3;
		case 74: return 3;

		case 57: return 3;
		
		case 49: return 4;
		case 122: return 4; 
		
		case 120: return 5;
		
		default: return 0;
		}
	 }

	int getbooteq(int itemid){
		switch (itemid) {
		case 301: return 1;
		case 305: return 2;
		case 309: return 3;
		case 313: return 4;
		case 317: return 1;
		default:  return 0;
		}
	}	
}