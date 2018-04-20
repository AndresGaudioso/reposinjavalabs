package es.sinjava.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import es.sinjava.model.Player;
import es.sinjava.model.PlayerType;

public class PlayerFilter {

	public static List<Player> filterByType(List<Player> collection, PlayerType... playertypes) {

		List<Player> filtered = new ArrayList<>();
		// Java 1.7 way
		Set<PlayerType> playerTypesSet = new HashSet<>(Arrays.asList(playertypes));

		for (Player player : collection) {
			if (playerTypesSet.contains(player.getPlayerType())) {
				filtered.add(player);
			}
		}
		return filtered;
	}

	public static boolean inTypeCollecion(Player player, PlayerType... playertypes) {
		Set<PlayerType> playerTypesSet = new HashSet<>(Arrays.asList(playertypes));
		return playerTypesSet.contains(player.getPlayerType());
	}

	public static boolean isVoz(Player player) {
		return PlayerType.VOZ.equals(player.getPlayerType());
	}

	public static boolean filterByType(Player player, Set<PlayerType> playerTypesSet) {
		return playerTypesSet.contains(player.getPlayerType());
	}

	// nueva sintaxis de java 8 pasamos un parámetro de tipo predicado
	public static List<Player> filterPlayers(List<Player> players, Predicate<Player> p) {
		List<Player> playersList = new ArrayList<>();
		for (Player player : players) {
			if (p.test(player)) {
				playersList.add(player);
			}
		}
		return playersList;
	}

	public static List<Player> filterPlayersByType(List<Player> players, BiPredicate<Player, Set<PlayerType>> predicate,
			PlayerType... playertypes) {

		Set<PlayerType> playerTypesSet = new HashSet<>(Arrays.asList(playertypes));
		List<Player> playersList = new ArrayList<>();
		for (Player player : players) {
			if (predicate.test(player, playerTypesSet)) {
				playersList.add(player);
			}
		}
		return playersList;
	}

}
