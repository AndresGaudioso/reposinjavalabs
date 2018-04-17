package es.sinjava.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.sinjava.model.Player;
import es.sinjava.model.PlayerType;

public class PlayerFactoryUtil {

	public static List<Player> getPlayers() {
		List<Player> listPlayers = new ArrayList<>(0);
		for (int index = 0; index < 15; index++) {
			PlayerType playerType = (PlayerType.values())[index % 5];
			String name = (NamesUtil.values())[index % 8].name();
			listPlayers.add(new Player(playerType, name, "MyBand", new Date(), index));
		}
		return listPlayers;
	}

}
