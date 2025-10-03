package gameEngine;

import java.util.Vector;

public class Vector2<T extends Number> {
    public T x;
    public T y;
    
    public Vector2(T x, T y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public void add(Vector2<T> vector) {
        this.x = this.x.doubleValue() + vector.x.doubleValue();
        this.y += vector.y.doubleValue();
    }
}
