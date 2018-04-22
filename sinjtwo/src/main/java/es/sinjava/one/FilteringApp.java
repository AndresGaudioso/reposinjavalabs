package es.sinjava.one;

import static es.sinjava.util.PlayerFilter.filterByType;
import static es.sinjava.util.PlayerFilter.filterPlayers;
import static es.sinjava.util.PlayerFilter.filterPlayersByType;

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
		// java 1.6
		// Solamente para listar los players
		// Sección 1
		List<Player> players = PlayerFactoryUtil.getPlayers();

		// Listamos todo los los players
		for (Iterator<Player> it = players.iterator(); it.hasNext();) {
			System.out.println("Name " + it.next().getName());
		}

		// Sección 1
		System.out.println("Filtrando en java 6");
		List<Player> listPlayersFilteredOne = filterByType(players, PlayerType.ACUSTICA);

		toConsole(listPlayersFilteredOne);

		System.out.println();

		// Sección 2

		System.out.println("Filtrando en java 6 con multipredicado");

		List<Player> listPlayersFilteredTwo = filterByType(players, PlayerType.ACUSTICA, PlayerType.BATERIA);

		toConsole(listPlayersFilteredTwo);

		// Sección 3

		System.out.println("\n Java 8 way ");

		List<Player> listPlayersFilteredNewWay = filterPlayers(players, PlayerFilter::isVoz);

		toConsole(listPlayersFilteredNewWay);

		// Seccion 4

		System.out.println("\n Java 8 way multi predicado ");

		List<Player> filtrado8Multipredicado = filterPlayersByType(players, PlayerFilter::filterByType,
				PlayerType.ACUSTICA, PlayerType.BATERIA);

		toConsole(filtrado8Multipredicado);

	}

	protected static void toConsole(List<Player> nuevaColeccion) {
		for (Player player : nuevaColeccion) {
			System.out.println(player.getName() + " - " + player.getPlayerType().name().toLowerCase());
		}
	}

}
