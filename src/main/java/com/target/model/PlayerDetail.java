package com.target.model;

import org.springframework.stereotype.Component;
/**
 * This class is responsible for mapping of players details.
 * @author Meenakshi
 *
 */
@Component
public class PlayerDetail {

	private String name;

	private UserScore userscore;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserScore getUserscore() {
		return userscore;
	}

	public void setUserscore(UserScore userscore) {
		this.userscore = userscore;
	}

}
