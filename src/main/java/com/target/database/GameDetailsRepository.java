package com.target.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.target.model.GameDetails;

@Repository
public interface GameDetailsRepository extends JpaRepository<GameDetails, Integer> {

}
