package com.target;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.target.model.GameDetails;
import com.target.model.PlayerDetail;
import com.target.model.UserScore;
import com.target.service.GameServices;

import ErrorHandling.CustomException;

/**
 * Controller contains different API methods for bowling game.
 * 
 * @author Meenakshi
 *
 */
@RequestMapping("/bowlingGame")
@RestController
public class ControllerClass {

	@Autowired
	private GameServices service;

	/**
	 * Method to start the game and it will return the Game id and player
	 * information.
	 * 
	 * @param details
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/startGame")
	public GameDetails startGame(@RequestBody GameDetails details) throws CustomException {
		// Throw exception when no. of players are greater than 3.
		if (details.getNoOfPlayer() > 3 || details.getUserInfo().size() > 3) {
			throw new CustomException(
					"Maximum 3 players are allowed in one lane. Please start new game with 3 or less players.");
		}
		return service.saveGameDetails(details);

	}

	/**
	 * This method gets player id and plays a frame for the player. Randomly
	 * generates player's score. one frame can have max 2 throws by a player.
	 * 
	 * @param playerId
	 * @return UserScore
	 */
	@GetMapping("/playFrame/{playerId}")
	public UserScore playFrame(@PathVariable Integer playerId) {
		UserScore userScore;
		try {
			userScore = service.getUserScore(playerId);
		} catch (Exception e) {
			userScore = null;
		}

		if (userScore != null) {
			userScore = service.updateUserScore(userScore, playerId);
		} else {
			userScore = service.insertUserScore(userScore, playerId);
		}

		return userScore;

	}

	/**
	 * This method takes player id as input and returns the details of player
	 * including game scores.
	 * 
	 * @param playerId
	 * @return PlayerDetail
	 */
	@GetMapping("/getPlayerDetail/{playerId}")
	public PlayerDetail getPlayerDetail(@PathVariable Integer playerId) {
		return service.getPlayerDetail(playerId);

	}

	/**
	 * This method takes game id as input and returns a list of all the players and
	 * their corresponding scores.
	 * 
	 * @param gameId
	 * @return List<PlayerDetail>
	 */
	@GetMapping("/getGameDetail/{gameId}")
	public List<PlayerDetail> getGameDetail(@PathVariable Integer gameId) {
		System.out.println("Game id : " + gameId);
		return service.getGameDetail(gameId);

	}

}
