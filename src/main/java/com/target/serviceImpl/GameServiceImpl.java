package com.target.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.database.GameDetailsRepository;
import com.target.database.UserInfoRepository;
import com.target.database.UserScoreRepository;
import com.target.model.GameDetails;
import com.target.model.PlayerDetail;
import com.target.model.UserInfo;
import com.target.model.UserScore;
import com.target.service.GameServices;

@Service
public class GameServiceImpl implements GameServices {

	@Autowired
	private GameDetailsRepository gameDetailsRepo;

	@Autowired
	private UserScoreRepository userScoreRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	/**
	 * Method to save the game details in database
	 */
	public GameDetails saveGameDetails(GameDetails gameDetails) {
		return gameDetailsRepo.save(gameDetails);
	}

	/**
	 * Method to get the game details by game id
	 */
	public Optional<GameDetails> findByGameId(int id) {
		return gameDetailsRepo.findById(id);
	}

	/**
	 * Method to get the user scores by user id
	 */
	@Override
	public UserScore getUserScore(int id) {
		return userScoreRepository.getbyUserID(id);
	}

	/**
	 * Gets the user score by user id, randomly generates throws(passes) and updates
	 * user score table.
	 */
	@Override
	public UserScore updateUserScore(UserScore score, Integer playerId) {
		Random randomGenerator = new Random();
		int firstPass = randomGenerator.nextInt(11);
		int secondPass = randomGenerator.nextInt(11 - firstPass);
		int totalScore = score.getTotalScore();
		String passes = score.getScorePerframe();
		int emptyPass = score.getMissedStrikeCount();
		int spareCount = score.getSpareCount();
		int strikeCount = score.getStrikeCount();
		int frameNumber = score.getCurrentFrameNumber() + 1;

		if (frameNumber <= 10) {

			if (firstPass == 10) {
				passes += "X _ ";
				totalScore += 20;
				strikeCount++;
			} else if (firstPass == 0) {
				emptyPass++;
				passes += String.valueOf(firstPass) + " ";
			} else {
				passes += String.valueOf(firstPass) + " ";
				totalScore += firstPass;
			}
			if (firstPass != 10) {

				if (firstPass + secondPass == 10) {
					passes += "/ ";
					totalScore = totalScore + secondPass + 5;
					spareCount++;
				} else if (secondPass == 0) {
					passes += String.valueOf(secondPass) + " ";
					emptyPass++;
				} else {
					passes += String.valueOf(secondPass) + " ";
					totalScore += secondPass;
				}
			}
			if (frameNumber == 10 && (firstPass + secondPass == 10)) {

				int extraThrowsRemaining = strikeCount > 2 ? (firstPass == 10 ? 2 : 1) : strikeCount;
				int extraThrow = 0;
				for (int i = 1; i <= extraThrowsRemaining; i++) {
					extraThrow = randomGenerator.nextInt(11);
					if (extraThrow == 10) {
						passes += "X ";
						totalScore += 20;
						strikeCount++;
					} else {
						passes += String.valueOf(extraThrow) + " ";
						totalScore += extraThrow;
					}
				}

			}
			if (frameNumber == 10)
				score.setGameStatus("Game Over");
			score.setMissedStrikeCount(emptyPass);
			score.setTotalScore(totalScore);
			score.setCurrentFrameNumber(frameNumber);
			score.setScorePerframe(passes);
			score.setStrikeCount(strikeCount);
			score.setSpareCount(spareCount);
			userScoreRepository.save(score);
			return score;

		}
		score.setGameStatus("Game Over");
		return score;

	}

	/**
	 * Used during the first throw(pass) only.
	 */
	@Override
	public UserScore insertUserScore(UserScore userScore, Integer playerId) {
		UserScore score = new UserScore();
		score.setUserId(playerId);
		Random randomGenerator = new Random();
		int firstPass = randomGenerator.nextInt(11);
		int secondPass = randomGenerator.nextInt(11 - firstPass);
		int totalScore = 0;
		String passes = "";
		int emptyPass = 0;
		int spareCount = 0;
		int strikeCount = 0;
		if (firstPass == 10) {
			passes = "X _ ";
			totalScore = 20;
			strikeCount++;
		} else if (firstPass == 0) {
			passes += String.valueOf(firstPass) + " ";
			emptyPass++;
		} else {
			passes = String.valueOf(firstPass) + " ";
			totalScore = firstPass;
		}
		if (firstPass != 10) {

			if (firstPass + secondPass == 10) {
				passes += "/ ";
				totalScore = 15;
				spareCount++;
			} else if (secondPass == 0) {
				passes += String.valueOf(secondPass) + " ";
				emptyPass++;
			} else {
				passes += String.valueOf(secondPass) + " ";
				totalScore += secondPass;
			}
		}
		score.setGameStatus("Game In Progress");
		score.setMissedStrikeCount(emptyPass);
		score.setTotalScore(totalScore);
		score.setCurrentFrameNumber(1);
		score.setScorePerframe(passes);
		score.setStrikeCount(strikeCount);
		score.setSpareCount(spareCount);
		userScoreRepository.save(score);
		return score;
	}

	/**
	 * This method return the players details by player id
	 */
	@Override
	public PlayerDetail getPlayerDetail(Integer playerId) {
		PlayerDetail playerDetails = new PlayerDetail();

		UserInfo userInfo = userInfoRepository.getById(playerId);
		System.out.println("Sita user id is :" + userInfo.getUserId());
		UserScore userScore = userScoreRepository.getbyUserID(playerId);
		playerDetails.setName(userInfo.getUserName());
		playerDetails.setUserscore(userScore);
		return playerDetails;
	}

	/**
	 * This method returns the game details by game id
	 */
	@Override
	public List<PlayerDetail> getGameDetail(Integer gameId) {
		List<PlayerDetail> playersDetails = new ArrayList<>();

		List<UserInfo> userInfos = userInfoRepository.getUserInfoByGameId(gameId);

		for (UserInfo userInfo : userInfos) {
			PlayerDetail playerdetail = new PlayerDetail();
			playerdetail.setName(userInfo.getUserName());
			playerdetail.setUserscore(userScoreRepository.getbyUserID(userInfo.getUserId()));
			playersDetails.add(playerdetail);
		}

		return playersDetails;
	}

}
