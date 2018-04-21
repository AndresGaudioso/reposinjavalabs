package es.sinjava.one;

import static es.sinjava.util.PlayerFactoryUtil.getPlayers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import es.sinjava.model.Player;

public class OrderingApp { // <iframe src="https://pastebin.com/embed_iframe/xp48wy1x"
							// style="border:none;width:100%; height:200px;"></iframe>

	public static void main(String... args) {

		System.out.println("\n java 6 primera iteración \n");
		List<Player> nuevaColeccion = getPlayers();

		toConsole(nuevaColeccion);

		Collections.sort(nuevaColeccion, new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		toConsole(nuevaColeccion);

		// java 8 way. Primer intento
		System.out.println("\n java 8 primera iteración \n");

		List<Player> nuevaColeccion8 = getPlayers();

		nuevaColeccion8.sort(new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				return o2.getName().compareTo(o1.getName());
			}
		});

		toConsole(nuevaColeccion8);

		System.out.println("\n java 8 segunda iteración \n");

		List<Player> nuevaColeccionLambda = getPlayers();

		toConsole(nuevaColeccionLambda);

		nuevaColeccionLambda.sort((Player o1, Player o2) -> o2.getName().compareTo(o1.getName()));

		toConsole(nuevaColeccionLambda);
	}

	protected static void toConsole(List<Player> nuevaColeccion) {
		for (Player player : nuevaColeccion) {
			System.out.println(player.getName() + " - " + player.getPlayerType().name().toLowerCase());
		}
	}

}
