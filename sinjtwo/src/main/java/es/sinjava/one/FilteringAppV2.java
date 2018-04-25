package es.sinjava.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import es.sinjava.model.Player;
import es.sinjava.model.PlayerType;
import es.sinjava.util.PlayerFactoryUtil;

// Preparando el curso de java 8

public class FilteringAppV2 {

	public static void main(String... args) {

		List<Player> filtered = new ArrayList<>();
		Set<PlayerType> validos = new HashSet<>(Arrays.asList(PlayerType.ACUSTICA, PlayerType.BATERIA));
		for (Iterator<Player> iterator = PlayerFactoryUtil.getPlayers().iterator(); iterator.hasNext();) {
			Player currentPlayer = iterator.next();
			boolean success = (validos.contains(currentPlayer.getPlayerType())) && filtered.add(currentPlayer);
		}
		toConsole(filtered);
	}

	protected static void toConsole(List<Player> nuevaColeccion) {
		for (Player player : nuevaColeccion) {
			System.out.println(player.getName() + " - " + player.getPlayerType().name().toLowerCase());
		}
	}

}
