package com.target.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.target.model.UserScore;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Integer> {
	@Query("from UserScore where userId =:id")
	UserScore getbyUserID(@Param("id") int id);
}
