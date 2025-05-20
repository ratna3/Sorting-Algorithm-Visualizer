package visualization;

import java.util.TreeMap;

/**
 * Class that represents a vertical bar in the visualization.
 * Each bar corresponds to an element in the array being sorted.
 */
public class Bar {
    private int x; // x position of the bar
    private int height; // height of the bar
    private final int baseY; // the base y position (typically the bottom) - this never changes
    
    /**
     * Constructor for creating a new Bar
     * @param x The x-coordinate position of the bar
     * @param height The height of the bar
     * @param baseY The base y-coordinate (bottom) of the bar
     */
    public Bar(int x, int height, int baseY) {
        this.x = x;
        this.height = height;
        this.baseY = baseY;
    }
    
    /**
     * Gets the x-coordinate of this bar
     * @return The x-coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Gets the height of this bar
     * @return The height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Gets the base y-coordinate of this bar
     * @return The base y-coordinate
     */
    public int getBaseY() {
        return baseY;
    }
    
    /**
     * Sets the height of this bar
     * @param height The new height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * Sets the x-coordinate of this bar
     * @param x The new x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Draws this bar using the LineDrawing class
     * @return A map of pixels to their intensity values
     */
    public TreeMap<Pixel, Double> draw() {
        return LineDrawing.drawVerticalBar(x, baseY, baseY - height);
    }
}