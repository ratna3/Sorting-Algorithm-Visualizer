package visualization;

/**
 * Class that represents a point in a 2D coordinate system.
 * Used for drawing algorithms and visualization.
 */
public class Pixel implements Comparable<Pixel> {
    public int x; // x-coordinate
    public int y; // y-coordinate

    /**
     * Constructor for creating a new Pixel
     * @param x The x-coordinate of the pixel
     * @param y The y-coordinate of the pixel
     */
    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Gets the x-coordinate of this pixel
     * @return The x-coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Gets the y-coordinate of this pixel
     * @return The y-coordinate
     */
    public int getY() {
        return y;
    }
    
    /**
     * Compares this pixel with another pixel
     * First compares x-coordinates, then y-coordinates if x-coordinates are equal
     * @param other The pixel to compare with
     * @return A negative integer, zero, or a positive integer if this pixel is less than, equal to, or greater than the other
     */
    @Override
    public int compareTo(Pixel other) {
        if(x != other.x)
            return x - other.x;
        return y - other.y;
    }

    /**
     * Returns a string representation of this pixel
     * @return The string representation in the format "(x, y)"
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}