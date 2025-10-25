# Topic of choice: Pathfinding / enemy ai
# Used articles:
- https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
- https://www.redblobgames.com/pathfinding/a-star/implementation.html
- https://en.wikipedia.org/wiki/A*_search_algorithm

## First code
The first code was a simple 'move towards player' function. It didn't think about how to reach the player, just that it needed to go towards it.

This caused the enemy to get stuck behind a obstacle when it had to walk back first to be able to reach the player


## Second code
Then we trought about a algorithm that would calculate the cost to go to each position, and when it reaches the player, backtracks to know the correct route. This code worked quite well. It is not efficient nor neat to look at, but it did do its job very well (after some bugfixing)

## The problem with enemies with the same algorithms
We noticed that when you have a lot of enemies using the same algoritm, they will stack and move as one:
![Multiple enemies on top of each other](/docs/img/image.png)