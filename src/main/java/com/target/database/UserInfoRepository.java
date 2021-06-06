package com.target.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.target.model.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	@Query("from UserInfo where game_User_Id =:gameId")
	List<UserInfo> getUserInfoByGameId(@Param("gameId") int gameId);

}
