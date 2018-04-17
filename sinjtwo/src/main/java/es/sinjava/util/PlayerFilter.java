package es.sinjava.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.sinjava.model.Player;
import es.sinjava.model.PlayerType;

public class PlayerFilter {

	public static List<Player> filterByType(List<Player> collection, PlayerType... playertypes) {
	
		List<Player> filtered = new ArrayList<>();
		// Java 1.7 way
		Set<PlayerType> playerTypesSet= new HashSet<>(Arrays.asList(playertypes));
		
		for( Player player: collection) {
			if(playerTypesSet.contains(player.getPlayerType())) {
				filtered.add(player);
			}
		}
		return filtered;
	}

}
