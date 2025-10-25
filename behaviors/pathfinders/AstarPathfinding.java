package behaviors.pathfinders;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import behaviors.GridMovement;
import behaviors.SpeedManager;
import gameEngine.GameObject;
import gameEngine.Vector2;

public class AstarPathfinding extends Pathfinding {
    SpeedManager speedManager;
    
    public AstarPathfinding(GameObject gameObject, GridMovement target) {
        super(gameObject, target);
        this.speedManager = (SpeedManager) gameObject.getBehavior(SpeedManager.class);
    }

    @Override
    public void move() {
        search(gridMovement.getPosition(), target.getPosition());
    }

    private void search(Vector2<Integer> start, Vector2<Integer> target) {
        PriorityQueue<PositionValuePair> frontier = new PriorityQueue<PositionValuePair>(
            new PositionValuePairComparator()
        );
        frontier.add(new PositionValuePair(start, 0.0));

        HashMap<Vector2<Integer>, Vector2<Integer>> cameFrom = 
            new HashMap<Vector2<Integer>, Vector2<Integer>>();

        HashMap<Vector2<Integer>, Double> currentCost = 
            new HashMap<Vector2<Integer>, Double>();

        cameFrom.put(start, null);
        currentCost.put(start, 0.0);

        while (!frontier.isEmpty()) {
            Vector2<Integer> currPosition = frontier.poll().pos;

            if (currPosition.equals(target)) {
                break;
            }

            for (Vector2<Integer> neighbor : getNeighbors(currPosition)) {
                double newCost = currentCost.get(currPosition) 
                    + (100.0 / speedManager.getSpeed(level.getTile(neighbor.x, neighbor.y).getClass()));

                if ((!currentCost.containsKey(neighbor)) || (newCost < currentCost.get(neighbor))) {
                    currentCost.put(neighbor, newCost);
                    double priority = newCost + heuristic(neighbor, target);
                    frontier.add(new PositionValuePair(neighbor, priority));
                    cameFrom.put(neighbor, currPosition);
                }
            }
        }
    }

    private static double heuristic(Vector2<Integer> pos1, Vector2<Integer> pos2) {
        return Math.abs(pos1.x - pos2.x) + Math.abs(pos1.y - pos2.y);
    }

    private record PositionValuePair(Vector2<Integer> pos, Double value) {}

    private class PositionValuePairComparator implements Comparator<PositionValuePair> {  
        public int compare(PositionValuePair p1, PositionValuePair p2) {
            if (p1.value < p2.value) {
                return 1;
            } else if (p1.value > p2.value) {
                return -1;
            }
                
            return 0;
        }
    }

    private ArrayList<Vector2<Integer>> getNeighbors(Vector2<Integer> position) {
        ArrayList<Vector2<Integer>> positions = new ArrayList<Vector2<Integer>>();

        if (level.canEnter(position.x + 1, position.y)) {
            positions.add(new Vector2<Integer>(position.x + 1, position.y));
        }
        if (level.canEnter(position.x - 1, position.y)) {
            positions.add(new Vector2<Integer>(position.x - 1, position.y));
        }
        if (level.canEnter(position.x, position.y + 1)) {
            positions.add(new Vector2<Integer>(position.x, position.y + 1));
        }
        if (level.canEnter(position.x, position.y - 1)) {
            positions.add(new Vector2<Integer>(position.x, position.y - 1));
        }

        return positions;
    }
}
