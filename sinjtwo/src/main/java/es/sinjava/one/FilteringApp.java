package es.sinjava.one;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.sinjava.model.Player;
import es.sinjava.model.PlayerType;
import es.sinjava.util.PlayerFactoryUtil;
import es.sinjava.util.PlayerFilter;

// Preparando el curso de java 8

public class FilteringApp {

	public static void main(String... args) {
		System.out.println("Clase de ejemplo : " + FilteringApp.class.getSimpleName());
		// java 1.7
		// Solamente para listar los players
		for (Iterator<Player> it = PlayerFactoryUtil.getPlayers().iterator(); it.hasNext();) {
			System.out.println("Name " + it.next().getName());
		}

		List<Player> listPlayersFilteredOne = PlayerFilter.filterByType(PlayerFactoryUtil.getPlayers(),
				PlayerType.ACUSTICA);

		for (Player player : listPlayersFilteredOne) {
			System.out.println("Name " + player.getName() + " - " + player.getPlayerType().name().toLowerCase() + " ");
		}
		System.out.println();

		List<Player> listPlayersFilteredTwo = PlayerFilter.filterByType(PlayerFactoryUtil.getPlayers(),
				PlayerType.ACUSTICA, PlayerType.BATERIA);

		for (Player player : listPlayersFilteredTwo) {
			System.out.print( player.getName() + " - " + player.getPlayerType().name().toLowerCase() + " | ");

		}
		System.out.println();

	}

}
