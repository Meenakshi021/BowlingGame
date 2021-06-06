package com.target.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is responsible for storing user scores
 * 
 * @author Meenakshi
 *
 */
@Entity
@Table(name = "userScore")
public class UserScore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
	 * latest frame number for current user
	 */
	private int currentFrameNumber;

	private int totalScore;
	/**
	 * no. of strikes scored through out the game till now
	 */
	private int strikeCount;
	/**
	 * no. of spare scored through out the game till now
	 */

	private int spareCount;

	/**
	 * no. of throws that scored zero till now
	 */

	private int missedStrikeCount;
	/**
	 * String representation of throws and their corresponding scores separated by a
	 * space X: Strikes, /: Spare, _ : Follows a strike( Means ball not played), 0-9
	 * : score
	 */
	private String scorePerframe;

	private int userId;
	/**
	 * game status can be one of these two : 1. Game In Progress 2. Game Over
	 */
	private String gameStatus;

	public UserScore() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurrentFrameNumber() {
		return currentFrameNumber;
	}

	public void setCurrentFrameNumber(int currentFrameNumber) {
		this.currentFrameNumber = currentFrameNumber;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getStrikeCount() {
		return strikeCount;
	}

	public void setStrikeCount(int strikeCount) {
		this.strikeCount = strikeCount;
	}

	public int getSpareCount() {
		return spareCount;
	}

	public void setSpareCount(int spareCount) {
		this.spareCount = spareCount;
	}

	public int getMissedStrikeCount() {
		return missedStrikeCount;
	}

	public void setMissedStrikeCount(int missedStrikeCount) {
		this.missedStrikeCount = missedStrikeCount;
	}

	public String getScorePerframe() {
		return scorePerframe;
	}

	public void setScorePerframe(String scorePerframe) {
		this.scorePerframe = scorePerframe;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

}
