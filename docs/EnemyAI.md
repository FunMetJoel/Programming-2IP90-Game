# Topic of choice: Pathfinding / enemy ai
# Used sources:
- https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
- https://www.redblobgames.com/pathfinding/a-star/implementation.html
- https://en.wikipedia.org/wiki/A*_search_algorithm

## First code: Greedy approach
The first code was a simple 'move towards player' function. It didn't think about how to reach the player, just that it needed to go towards it.
Was made to quickly test enemy's, but has flaws.
It caused the enemy to get stuck behind a obstacle when it had to walk back first to be able to reach the player

## Second code: Dijkstra's algorithm
Then we through about a simple algorithm we learned in highschool that would calculate the cost to go to each position, and when it reaches the player, backtracks to know the correct route. We created this code by based on the wikipedia article about Dijkstra pathfinding. This code worked quite well. It is not efficient nor neat to look at, but it did do its job very well.
Not much we learned from this code, except that Dijkstra studied at TU/e.

## Third code: A* algorithm
Finally we implemented the A* algorithm, based on the the a* wikipedia article and the redblobgames article about pathfinding. 
This algorithm is an improvement on Dijkstra's algorithm, as it uses heuristics to make an educated guess on which tiles to explore first.
In this code, we also implemented a check for the walking speed of tiles, so the enemy would prioritize faster tiles over slower tiles. 
In this article I learned about the **PriorityQueue** data structure, which was new to me, and seems useful for this situation.
This article also used a more graph like structure than the Dijkstra code we made. This makes it more readable and easier to understand.

## Fourth code: Improvements on A* algorithm
The RedBlobGames article also mentioned some improvements that could be made to the A* algorithm to create more natural routes. Tease are implemented in the fourth version of the code.
(https://www.redblobgames.com/pathfinding/a-star/implementation.html#troubleshooting-ugly-path)
