package visualization;

import java.util.TreeMap;

/**
 * Implementation of Xiaolin Wu's line algorithm for anti-aliased line drawing.
 * This class provides methods to draw lines with varying intensities for a smoother appearance.
 */
public class LineDrawing {
    public static TreeMap<Pixel,Double> xuLine(int x0, int y0, int x1, int y1) {
        TreeMap<Pixel,Double> retVal = new TreeMap<>();
        
        // Handle vertical lines
        boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);
        if (steep) {
            // Swap x0, y0
            int temp = x0;
            x0 = y0;
            y0 = temp;
            
            // Swap x1, y1
            temp = x1;
            x1 = y1;
            y1 = temp;
        }
        
        // Ensure x0 <= x1
        if (x0 > x1) {
            // Swap x0, x1
            int temp = x0;
            x0 = x1;
            x1 = temp;
            
            // Swap y0, y1
            temp = y0;
            y0 = y1;
            y1 = temp;
        }
        
        int dx = x1 - x0;
        int dy = y1 - y0;
        double gradient = (dx == 0) ? 1.0 : (double) dy / dx;
        
        // Handle first endpoint
        int xend = x0;
        double yend = y0 + gradient * (xend - x0);
        int xpxl1 = xend;
        int ypxl1 = (int) yend;
        double fpart = yend - Math.floor(yend);
        double rfpart = 1.0 - fpart;
        
        if (steep) {
            retVal.put(new Pixel(ypxl1, xpxl1), rfpart);
            if (fpart > 0) {
                retVal.put(new Pixel(ypxl1 + 1, xpxl1), fpart);
            }
        } else {
            retVal.put(new Pixel(xpxl1, ypxl1), rfpart);
            if (fpart > 0) {
                retVal.put(new Pixel(xpxl1, ypxl1 + 1), fpart);
            }
        }
        
        double intery = yend + gradient;
        
        // Handle second endpoint
        int xend2 = x1;
        double yend2 = y1;
        int xpxl2 = xend2;
        int ypxl2 = (int) yend2;
        fpart = yend2 - Math.floor(yend2);
        rfpart = 1.0 - fpart;
        
        if (steep) {
            retVal.put(new Pixel(ypxl2, xpxl2), rfpart);
            if (fpart > 0) {
                retVal.put(new Pixel(ypxl2 + 1, xpxl2), fpart);
            }
        } else {
            retVal.put(new Pixel(xpxl2, ypxl2), rfpart);
            if (fpart > 0) {
                retVal.put(new Pixel(xpxl2, ypxl2 + 1), fpart);
            }
        }
        
        // Main loop
        for (int x = xpxl1 + 1; x < xpxl2; x++) {
            int y = (int) intery;
            fpart = intery - y;
            rfpart = 1.0 - fpart;
            
            if (steep) {
                if (rfpart > 0) {
                    retVal.put(new Pixel(y, x), rfpart);
                }
                if (fpart > 0) {
                    retVal.put(new Pixel(y + 1, x), fpart);
                }
            } else {
                if (rfpart > 0) {
                    retVal.put(new Pixel(x, y), rfpart);
                }
                if (fpart > 0) {
                    retVal.put(new Pixel(x, y + 1), fpart);
                }
            }
            
            intery += gradient;
        }
        
        return retVal;
    }
    
    /**
     * Draws a vertical bar from y1 to y2 at position x
     * @param x The x position of the bar
     * @param y1 The starting y position (usually the bottom)
     * @param y2 The ending y position (usually the top)
     * @return A map of pixels to their intensity values
     */
    public static TreeMap<Pixel,Double> drawVerticalBar(int x, int y1, int y2) {
        return xuLine(x, y1, x, y2);
    }

    public static void main(String[] args) {
        TreeMap<Pixel,Double> answer = LineDrawing.xuLine(0, 0, 1, 3);
        for(Pixel p : answer.keySet()) {
            System.out.println(p + " " + answer.get(p));
        }
    }
}