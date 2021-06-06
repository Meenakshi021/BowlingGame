package com.target.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.target.model.GameDetails;
import com.target.model.PlayerDetail;
import com.target.model.UserScore;

/**
 * This service interface contains list of methods used to perform CRUD
 * operation
 * 
 * @author Meenakshi
 *
 */
@Component
public interface GameServices {

	public GameDetails saveGameDetails(GameDetails gameDetails);

	public Optional<GameDetails> findByGameId(int id);

	public UserScore getUserScore(int id);

	public UserScore updateUserScore(UserScore userScore, Integer userId);

	public UserScore insertUserScore(UserScore userScore, Integer userId);

	public PlayerDetail getPlayerDetail(Integer playerId);

	public List<PlayerDetail> getGameDetail(Integer gameId);
}
