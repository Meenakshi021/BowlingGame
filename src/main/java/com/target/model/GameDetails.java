package com.target.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This class is responsible for mapping game details
 * 
 * @author Meenakshi
 *
 */
@Entity
@Table(name = "gameDetails")
public class GameDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gameId")
	private int gameId;

	@Column(name = "No_Of_Player")
	private int noOfPlayer;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "gameUserId", referencedColumnName = "gameId")
	private List<UserInfo> userInfo = new ArrayList<>();

	public GameDetails() {
		// TODO Auto-generated constructor stub
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getNoOfPlayer() {
		return noOfPlayer;
	}

	public void setNoOfPlayer(int noOfPlayer) {
		this.noOfPlayer = noOfPlayer;
	}

	public List<UserInfo> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(List<UserInfo> userInfo) {
		this.userInfo = userInfo;
	}

	public GameDetails(int gameId, int noOfPlayer, List<UserInfo> userInfo) {
		super();
		this.gameId = gameId;
		this.noOfPlayer = noOfPlayer;
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "GameDetails [gameId=" + gameId + ", noOfPlayer=" + noOfPlayer + ", userInfo=" + userInfo + "]";
	}

}
