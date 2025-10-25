# TODO come up with a cool name

# Topics of chocice:
1. [X] Pathfinding algorithm
2. [X] Procedural generation of the obstacles (Perlin noise)

## Rendering the game
1. [X] The application should render sprites to the screen
2. [X] Things on the front should be rendered for thing in the background
3. [X] The camera should be able to move around
4. [X] The obstacles on the map should be rendered correctly
5. [X] The stars should render correctly

>How to test: Click "New Game" and observe the described aspects of the game in terms of correct rendering.

## Launching the game
1. [X] Start the application, click "New Game" button and the game field will launch and the game will start.

>How to test: Click "New Game" and see if the game starts.

## Movement of the opponents
1. [X] After the game launches, opponents try to catch the player and end the game, using 3 different pathfinding algotithms.

>How to test: Start a new game and observe the opponents try to move in the direction of the player.


## Movement of the player
1. [X] After the game launches, the player is able to move in directions (NSWE) inside the game field using WSAD

>How to test: Start a new game and try to move the player using WSAD.

## Structural generation of the obstacles
1. [X] After the launch of the game, obstacles are generated in the game field. These are barriers, impenetrable by any game entity. Each time there should be a different setting of them.
2. [X] If there is time, some special tiles with special properties should be created

>How to test: Launch a new game and see how the terrain is generated. Notice how it varies every time a different seed is input. Also the muddy areas are subject to seed changes, which can be observed.

## Generating the stars
1. [X] After the game launches, there should be constantly generated stars in the game field in random tiles.
2. [X] Stars should generate constantly, but there is a max cap of them in one moment. The stars should not generate on the obstacles.

>How to test: Launch a new game, move the player and try to find stars scattered on the map. If you collect one, anotherone should appear in a different spot.

## Player lifetime counter
1. [X] After the game launches, a Player lifetime counter should appear somewhere in the game field (without obstructing the field). It should be set to a default value (TBD).
2. [X]Upon collection of a star by the player the player's life length is extended which is visible on the counter, and when player collides with an enemy, a star should be deducted from there.

>How to test: Try to collect a star and see how the lifetime counter changes. Also try to collide with an enemy and notice the change in the different direction.

## End game screen
1. [X] After the player is caught by an enemy or runs out of time, the game should end and a short message with a score should be displayed
2. [ ] We can also add an option to play again.

>How to test: Try collect some stars and then run out of lifetime. The game should halt as soon as you are at zero stars and a messagge should be displayed, with your final score of how many stars you collected. (if play again)

## Textures of the gamefield, player and enemies
1. [X] Throught the gameplay there should be visible textures of diverse game entities.

>How to test: Try and play the game for a bit, notice the textures described above being generated.

## Reading the input
1. [X] Player will interact with the game using keyboard keys.

>How to test: Try clicking on the buttons, inputing custom seed and moving the player.

## Initial postion of the opponents
1. [X] After the game is launched, opponents should be evenly distributed on the map.

>How to test: Launch a new game, see how the oponnents are placed on the map.

## Initial position of the player
1. [X] After the game is launched, the player should be generated in a start position, giving it an opportunity to run away from the enemies.

>How to test: Launch a new game and see how the player spawns.

## Start menu
1. After main.java is exectuted, a start menu should apper with options (buttons) to play a new game, quit game and a text field to input a custom seed.

>How to test: Launch main.java and observe the menu appearing. Test its features by pressing different buttons and input a custom seed.

