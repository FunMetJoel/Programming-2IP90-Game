package gameEngine;

import java.util.Vector;

public class Vector2<T> {
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
}
