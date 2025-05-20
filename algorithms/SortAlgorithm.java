package algorithms;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import visualization.*;

/**
 * Abstract base class for all sorting algorithms
 * Provides common functionality for visualization and updates
 */
public abstract class SortAlgorithm {
    protected final int[] values;       // Array being sorted
    protected final Bar[] bars;         // Visual representation
    protected final int width;          // Width of visualization area
    protected final int height;         // Height of visualization area
    protected final int delay;          // Animation delay
    
    // Tracking metrics
    protected int numberOfComparisons = 0;
    protected int numberOfSwaps = 0;
    protected int currentStep = 0;
    
    // Visualization state
    protected int compareIndex1 = -1;
    protected int compareIndex2 = -1;
    protected String currentOperation = "";
    protected String algorithmName;     // Name of the algorithm
    
    /**
     * Constructor for a sorting algorithm
     */
    public SortAlgorithm(int[] array, Bar[] bars, int width, int height, int delay) {
        this.values = Arrays.copyOf(array, array.length);
        this.bars = bars;
        this.width = width;
        this.height = height;
        this.delay = delay;
        this.algorithmName = getClass().getSimpleName();
    }
    
    /**
     * Execute the sorting algorithm
     */
    public abstract void sort();
    
    /**
     * Update the heights of the bars based on current values
     */
    protected void updateBars() {
        int maxValue = findMaximumValue();
        if (maxValue == 0) maxValue = 1;
        
        for (int i = 0; i < values.length; i++) {
            int barHeight = Math.max(1, (int)((values[i] / (double)maxValue) * (height - 10)));
            bars[i].setHeight(barHeight);
        }
    }
    
    /**
     * Draw the current state of the sorting algorithm
     */
    protected void draw() {
        try {
            // Use reflection to call SortVisualizer.draw (which is in the default package)
            Class<?> visualizerClass = Class.forName("SortVisualizer");
            java.lang.reflect.Method drawMethod = visualizerClass.getMethod("draw", 
                Bar[].class, int[].class, int.class, int.class, String.class, 
                int.class, int.class, int.class, String.class, int.class, int.class);
            
            drawMethod.invoke(null, bars, values, width, height, algorithmName, 
                    currentStep, numberOfComparisons, numberOfSwaps, 
                    currentOperation, compareIndex1, compareIndex2);
        } catch (Exception e) {
            System.err.println("Error drawing visualization: " + e.getMessage());
        }
    }
    
    /**
     * Find the maximum value in the array
     */
    protected int findMaximumValue() {
        if (values.length == 0) return 0;
        
        int maximumValue = values[0];
        for (int value : values) {
            if (value > maximumValue) {
                maximumValue = value;
            }
        }
        return maximumValue;
    }
    
    /**
     * Safely pause the visualization
     */
    protected void pause() {
        if (delay <= 0) {
            return;
        }
        
        try {
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.err.println("Animation interrupted: " + exception.getMessage());
        }
    }
    
    /**
     * Swap two elements in the array and update visualization
     */
    protected void swap(int i, int j) {
        currentOperation = "Swapping elements at indices " + i + " and " + j + 
                           ": " + values[i] + " <-> " + values[j];
        
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
        
        numberOfSwaps++;
        updateBars();
        compareIndex1 = i;
        compareIndex2 = j;
        draw();
        pause();
    }
    
    /**
     * Compare two elements and update visualization
     */
    protected boolean compare(int i, int j) {
        compareIndex1 = i;
        compareIndex2 = j;
        currentOperation = "Comparing elements at indices " + i + " and " + j + 
                           ": " + values[i] + " > " + values[j] + "?";
        
        numberOfComparisons++;
        currentStep++;
        draw();
        pause();
        
        return values[i] > values[j];
    }
    
    /**
     * Get the sorted array
     */
    public int[] getSortedArray() {
        return values;
    }
    
    /**
     * Mark the sorting as complete
     */
    protected void markComplete() {
        currentOperation = "Sorting complete! Array is now sorted.";
        compareIndex1 = compareIndex2 = -1;
        draw();
    }
}
