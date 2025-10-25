# Programming-2IP90-Game

This is the repository for the Programming 2IP90 Game project.

## Java version
This project works and was tested with Java 24.0.2

## How to run
1. Run the `main()` method in `Main.java` located in the `src` folder.
2. The start menu window should open.
3. Input a custom seed in the input box or play with random seed inputting nothing.
4. Click "New Game" to launch the game or "Quit Game" to quit game.
3. Use the W, A, S, D keys to move the player around the map.
4. Try to avoid the enemies while collecting starts to extend your lifetime.
5. The game ends when out of stars.

## How to test: Procedural generation
1. In the start menu, type a different seed in the "Seed" input field. (int value)
2. Click "New Game" to start a new game with the given seed.
3. Observe the generated obstacles.

## How to test: Enemy pathfinding
1. Start a new game.
2. Observe the different behaviors of the enemies:
   - Greedy enemies (red) try to move directly towards the player. This results in them getting stuck behind obstacles. To counteract this, they move faster.
   - Dijkstra enemies (blue) use Dijkstra's algorithm to to find the shortest path to the player.
   - A* enemies (green) use the A* algorithm to find the shortest path to the player, they will avoid the mud tiles.