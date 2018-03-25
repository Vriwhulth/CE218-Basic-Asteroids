package utilities;

/**
 * Created by ap16718.
 */

// vector 2D class

// mutable 2D vectors
public final class Vector2D {
    public double x, y;

    // constructor for zero vector
    public Vector2D() {}

    // constructor for vector with given coordinates
    public Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    // constructor that copies the argument vector
    public Vector2D(Vector2D v)
    {
        this.x = v.x;
        this.y = v.y;
    }

    // set coordinates
    public Vector2D set(double x, double y)
    {
        this.x = x;
        this.y = y;
        return this;
    }

    // set coordinates based on argument vector
    public Vector2D set(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
        return this;
    }

    // compare for equality (note Object type argument)
    public boolean equals(Object o) {
        if(o instanceof Vector2D){
            if(this.x == ((Vector2D)o).x && this.y == ((Vector2D)o).y)
                return true;
            else
                return false;
        }
        return false;
    }

    // String for displaying vector as text
    public String toString() {
        String s = "( "+this.x+", "+this.y+")";
        return s;
    }

    //  magnitude (= "length") of this vector
    public double mag() {
        return Math.hypot(this.x, this.y);
    }

    // angle between vector and horizontal axis in radians in range [-PI,PI]
// can be calculated using Math.atan2
    public double angle() {
        return Math.atan2(this.y,this.x);
    }

    // angle between this vector and another vector in range [-PI,PI]
    public double angle(Vector2D other) {
        double anglebetween = other.angle() - this.angle();
        if(anglebetween > Math.PI)
            anglebetween -= Math.PI*2;
        else if (anglebetween < -Math.PI)
            anglebetween += Math.PI*2;
        return anglebetween;
    }

    // add argument vector
    public Vector2D add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    // add values to coordinates
    public Vector2D add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    // weighted add - surprisingly useful
    public Vector2D addScaled(Vector2D v, double fac) {
        this.x = this.x + fac * v.x;
        this.y = this.y + fac * v.y;
        return this;
    }

    // subtract argument vector
    public Vector2D subtract(Vector2D v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    // subtract values from coordinates
    public Vector2D subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    // multiply with factor
    public Vector2D mult(double fac) {
        this.x *= fac;
        this.y *= fac;
        return this;
    }

    // rotate by angle given in radians
    public Vector2D rotate(double angle) {
        double x = this.x * Math.cos(angle) - this.y * Math.sin(angle);
        double y = this.x * Math.sin(angle) + this.y * Math.cos(angle);
        this.set(x,y);
        return this;
    }

    // "dot product" ("scalar product") with argument vector
    public double dot(Vector2D v)
    {
        return this.x * v.x + this.y * v.y;
    }

    // distance to argument vector
    public double dist(Vector2D v) {
        return Math.hypot(v.x - this.x, v.y - this.y);
    }

    // normalise vector so that magnitude becomes 1
    public Vector2D normalise() {
        double m = this.mag();
        this.x = this.x /m;
        this.y = this.y/m;
        Math.hypot(this.x,this.y);
        return this;
    }

    // wrap-around operation, assumes w> 0 and h>0
// remember to manage negative values of the coordinates
    public Vector2D wrap(double w, double h) {
        double x = this.x/w;
        double y = this.y/h;
        if(x > 1)
            x-=1;
        else if (x < 0)
            x+=1;
        if(y > 1)
            y-=1;
        else if (y < 0)
            y+=1;
        this.x = x * w;
        this.y = y * h;
        return this;

    }

    // construct vector with given polar coordinates
    public static Vector2D polar(double angle, double mag) {
        double x = mag * Math.cos(angle);
        double y = mag * Math.sin(angle);
        Vector2D v1 = new Vector2D(x,y);
        return v1;
    }

}