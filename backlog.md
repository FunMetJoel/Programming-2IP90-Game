# //TODO come up with a cool name

# Topics of chocice:
1. [X] Pathfinding algorithm
2. [ ] Procedural generation of the obstacles (Perlin noise)

## Rendering the game
1. [X] The application should render sprites to the screen
2. [X] Things on the front should be rendered for thing in the background
3. [X] The camera should be able to move around

## Launching the game
1. [ ] Start the application, click "New Game" button and the game field will launch and the game will start.
2. [ ] If we have time, we can add different difficulties, difficulty menu will appear after pressing "New Game".

## Movement of the opponents
1. [ ] After the game launches, opponents try to catch the player and end the game, using a pathfinding algotithm.
2. [ ] If we have time, we can also implement this: as the runtime advances, the bots should become faster and be able to catch up to the player with more ease.

## Movement of the player
1. [X] After the game launches, the player is able to move in directions (NSWE) inside the game field using some key input (to be decided later).
2. No remarks yet.

## Structural generation of the obstacles
1. [ ] After the launch of the game, obstacles are generated in the game field. These are barriers, impenetrable by any game entity. Each time there should be a different setting of them.
2. [ ] There is a fixed number and shape of obstacles that always appear in the level.
3. [ ] If there is time, some special tiles with special properties should be created

## Generating the fuel canisters
1. [ ] After the game launches, there should be constantly generated fuel canisters in the game field in random tiles, which upon collection by the player extend the player's life length.
2. [ ] Fuel canisters should generate constantly, but there is a max cap of them in one moment. The fuel canisters should not generate on the obstacles.

## Player lifetime counter
1. [ ] After the game launches, a Player lifetime counter should appear somewhere in the game field (without obstructing the field). It should be set to a default value (TBD).
2. No remarks yet.

## End game screen
1. [ ] After the player is caught by an enemy or runs out of time, the game should and and a short message with a score should be displayed
2. [ ] We can also add an option to play again.

## Textures of the gamefield, player and enemies
1. [ ] Throught the gameplay there should be visible textures of diverse game entities.
2. No remarks yet.

## Reading the input
1. [X] Player will interact with the game using keyboard keys.
2. No remarks yet.

## Initial postion of the opponents
1. [ ] After the game is launched, opponents should be evenly distributed on the map.
2. No remarks yet.

## Initial position of the player
1. [ ] After the game is launched, the player should be generated in a start position, giving it an opportunity to run away from the enemies.
2. No remarks yet.