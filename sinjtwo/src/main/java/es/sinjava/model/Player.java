package es.sinjava.model;

import java.util.Date;

public class Player {

	public Player() {
		super();
	}

	public Player(PlayerType playerType, String name, String band, Date date, int age) {
		super();
		this.playerType = playerType;
		this.name = name;
		this.band = band;
		this.date = date;
		this.age = age;
	}

	private PlayerType playerType;
	private String name;
	private String band;
	private Date date;
	private int age;

	public PlayerType getPlayerType() {
		return playerType;
	}

	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
