package es.sinjava.one;

import es.sinjava.factory.PlayerFactoryUtil;
import es.sinjava.model.Player;

// Preparando el curso de java 8

public class FilteringApp {

	public static void main(String... args) {
		System.out.println("Clase de ejemplo : " + FilteringApp.class.getSimpleName());
		// java 1.7
		for(Player player: PlayerFactoryUtil.getPlayers()) {
			System.out.println("Player name " +player.getName() + " - " + player.getPlayerType().name().toLowerCase());
		}
		
		

	}

}
