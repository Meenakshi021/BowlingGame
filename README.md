# Bowling Game
-- Meenakshi Sharma

As part of this project, I have tried to simulate a bowling game using RESTful API services.

Technologies Used : Java Spring Boot, H2 Databases, Postman.

As part of this game, we can do the following:

### a.) Start Game

**API**: POST */bowlingGame/startGame*

**Description**: We will start the game and will generate a game id along with user information.Only if the no. of players are not more than 3.

#### Request example:
```
{
    "noOfPlayer": 2,
    "userInfo": [
        {
            "userName": "Prashant"
        },
        {
            "userName": "Meenakshi"
        }
    ]
} 
```
#### Response example:
```
{
    "gameId": 1,
    "noOfPlayer": 2,
    "userInfo": [
        {
            "userId": 1,
            "userName": "Prashant"
        },
        {
            "userId": 2,
            "userName": "Meenakshi"
        }
    ]
}
```
### b.) Play Game 

**API**: GET */bowlingGame/playFrame/{playerId}*

**Description**: This API gets player id and plays a frame for the player. We randomly generate player's score. One frame can have max 2 throws by a player.

#### Request Example :
```
/bowlingGame/playFrame/1
```

#### Response Example:
```
{
    "id": 1,
    "currentFrameNumber": 1,
    "totalScore": 2,
    "strikeCount": 0,
    "spareCount": 0,
    "missedStrikeCount": 0,
    "scorePerframe": "1 1 ",
    "userId": 1,
    "gameStatus": "Game In Progress"
}
```
### c.) Player details

**API**: GET */bowlingGame/getPlayerDetail/{playerId}*

**Description**: This API will return the current scores of a player based on their player id. These scores contains : currentFrameNumber, totalScore, strikeCount, spareCount, missedStrikeCount, scorePerframe and gameStatus.Description of these properties are explained as comments in the code.

#### Request Example :
```
/bowlingGame/getPlayerDetail/1
```

#### Response Example:
```
{
    "name": "Prashant",
    "userscore": {
        "id": 1,
        "currentFrameNumber": 1,
        "totalScore": 2,
        "strikeCount": 0,
        "spareCount": 0,
        "missedStrikeCount": 0,
        "scorePerframe": "1 1 ",
        "userId": 1,
        "gameStatus": "Game In Progress"
    }
}
```

### d.) Game details

**API**: GET */bowlingGame/getGameDetail/{gameId}*

**Description**: This API returns the list of all players and their score details who are playing a game, based on the passed game id as input.

#### Request Example :
```
/bowlingGame/getGameDetail/1
```

#### Response Example:
```
[
    {
        "name": "Prashant",
        "userscore": {
            "id": 1,
            "currentFrameNumber": 1,
            "totalScore": 2,
            "strikeCount": 0,
            "spareCount": 0,
            "missedStrikeCount": 0,
            "scorePerframe": "1 1 ",
            "userId": 1,
            "gameStatus": "Game In Progress"
        }
    },
    {
        "name": "Meenakshi",
        "userscore": {
            "id": 2,
            "currentFrameNumber": 1,
            "totalScore": 6,
            "strikeCount": 0,
            "spareCount": 0,
            "missedStrikeCount": 1,
            "scorePerframe": "6 0 ",
            "userId": 2,
            "gameStatus": "Game In Progress"
        }
    }
]
```


### Future Scope 

* As future scope I would like to include the following 
    * Game arena creation with count of lanes available
    * Lane allocation for players more than 3
    * Unit test coverage
    * Swagger implementation 
    * Better exception handling
    







