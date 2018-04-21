package es.sinjava.one;

import static es.sinjava.util.PlayerFactoryUtil.getPlayers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import es.sinjava.model.Player;

public class OrderingAppTwo extends OrderingApp {

	public static void main(String... args) {

		System.out.println("\n java 6 flavor \n");
		List<Player> nuevaColeccion = getPlayers();

		Collections.sort(nuevaColeccion, new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				int compareValue = o1.getName().compareTo(o2.getName());
				compareValue = (compareValue == 0) ? o1.getPlayerType().compareTo(o2.getPlayerType()) : compareValue;
				return compareValue;
			}
		});

		toConsole(nuevaColeccion);

		// java 8 way. Primer intento
		System.out.println("\n java 8 primera iteración \n");

		List<Player> nuevaColeccion8 = getPlayers();

		nuevaColeccion8.sort(new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				int compareValue = o2.getName().compareTo(o1.getName());
				compareValue = (compareValue == 0) ? o2.getPlayerType().compareTo(o1.getPlayerType()) : compareValue;
				return compareValue;
			}
		});

		System.out.println("\n java 8 segunda iteración \n");

		List<Player> nuevaColeccionLambda2 = getPlayers();

		nuevaColeccionLambda2.sort((Player o1, Player o2) -> (o1.getName().compareTo(o2.getName()) == 0)
				? o1.getPlayerType().compareTo(o2.getPlayerType())
				: o1.getName().compareTo(o2.getName()));

		toConsole(nuevaColeccionLambda2);

		// Tercera revisión, es más de lo mismo
		List<Player> nuevaColeccionLambda3 = getPlayers();

		nuevaColeccionLambda3.sort((Player o1, Player o2) -> {
			int compareValue = o2.getName().compareTo(o1.getName());
			compareValue = (compareValue == 0) ? o2.getPlayerType().compareTo(o1.getPlayerType()) : compareValue;
			return compareValue;
		});

		toConsole(nuevaColeccionLambda3);

	}

}
