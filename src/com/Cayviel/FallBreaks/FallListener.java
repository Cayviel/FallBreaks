package com.Cayviel.FallBreaks;

import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftWorld;

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
					int BlockOnData = BlockOn.getData();
					net.minecraft.server.Block.byId[BlockOn.getTypeId()].g(((CraftWorld)w).getHandle(), BlockOn.getX(), BlockOn.getY(), BlockOn.getZ(), BlockOnData);
					BlockOn.setTypeId(0);
				}
			}
		}
	}
}