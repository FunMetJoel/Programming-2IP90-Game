package gameEngine;

/**
 * A object with a x and a y.
 */
public class Vector2<T extends Number> {
    public T x;
    public T y;
    
    /**
     * Creates a new vector of type T with an x and a y.
     * @param x the x value
     * @param y the y vale
     */
    public Vector2(T x, T y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public Vector2<T> copy() {
        return new Vector2<T>(x, y);
    }

    /**
     * Rounds vector to a int.
     * @return rounded vector
     */
    public Vector2<Integer> round() {
        return new Vector2<Integer>(
            Math.round(x.floatValue()),
            Math.round(y.floatValue())
        );
    }
}
