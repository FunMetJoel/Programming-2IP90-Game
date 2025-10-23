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

    /**
     * Scales a vector by a certain amount.
     * @param scaler the scalar to scale the vector with
     * @return a new scaled vector
     */
    public Vector2<Double> newScaledVector(Double scaler) {
        return new Vector2<Double>(
            x.doubleValue() * scaler,
            y.doubleValue() * scaler
        );
    }

    /**
     * Scales a vector by a certain amount.
     * @param scaler the 2d vector with a scalar for the x and for the y
     * @return a new scaled vector
     */
    public Vector2<Double> newScaledVector(Vector2<Double> scaler) {
        return new Vector2<Double>(
            x.doubleValue() * scaler.x,
            y.doubleValue() * scaler.y
        );
    }

    /**
     * Adds two vectors together.
     * @param vector the other vector.
     * @return a new vector with both vector values added together.
     */
    public Vector2<Double> addVector(Vector2<Double> vector) {
        return new Vector2<Double>(
            x.doubleValue() + vector.x,
            y.doubleValue() + vector.y
        );
    }
    
    /**
     * Subtracts a vector from another.
     * @param vector the vector to subtract.
     * @return a new vector with one vector subtracted from the other
     */
    public Vector2<Double> subtractVector(Vector2<Double> vector) {
        return new Vector2<Double>(
            x.doubleValue() - vector.x,
            y.doubleValue() - vector.y
        );
    }

    /**
     * Turns the vector in to a double vector.
     * @return a new vector with double values
     */
    public Vector2<Double> toDouble() {
        return new Vector2<Double>(
            x.doubleValue(),
            y.doubleValue()
        );
    }

    public boolean equals(Vector2<T> otherVector) {
        return ((this.x == otherVector.x) && (this.y == otherVector.y));
    }
}
