package algorithms;

import visualization.*;

/**
 * Implementation of Bubble Sort algorithm
 */
public class BubbleSort extends SortAlgorithm {
    
    public BubbleSort(int[] array, Bar[] bars, int width, int height, int delay) {
        super(array, bars, width, height, delay);
        this.algorithmName = "Bubble Sort";
    }
    
    @Override
    public void sort() {
        // Initial draw to show starting state
        currentOperation = "Starting Bubble Sort";
        draw();
        pause();
        
        // Bubble sort algorithm
        for (int i = 0; i < values.length - 1; i++) {
            boolean swapped = false;
            
            for (int j = 0; j < values.length - i - 1; j++) {
                // Compare adjacent elements
                if (compare(j, j + 1)) {
                    // Swap them if they are in wrong order
                    swap(j, j + 1);
                    swapped = true;
                }
            }
            
            // If no swaps occurred in this pass, the array is already sorted
            if (!swapped) {
                currentOperation = "No swaps needed - array is sorted!";
                compareIndex1 = compareIndex2 = -1;
                draw();
                pause();
                break;
            }
            
            // Show completion of this pass
            currentOperation = "Completed pass " + (i + 1) + " of " + (values.length - 1);
            compareIndex1 = compareIndex2 = -1;
            draw();
            pause();
        }
        
        // Show final sorted result
        markComplete();
    }
}
