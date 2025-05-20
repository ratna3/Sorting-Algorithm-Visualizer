package algorithms;

import visualization.*;

/**
 * Implementation of the Selection Sort algorithm
 */
public class SelectionSort extends SortAlgorithm {
    
    public SelectionSort(int[] array, Bar[] bars, int width, int height, int delay) {
        super(array, bars, width, height, delay);
        this.algorithmName = "Selection Sort";
    }
    
    @Override
    public void sort() {
        currentOperation = "Starting Selection Sort";
        draw();
        pause();
        
        // Selection sort algorithm
        for (int i = 0; i < values.length - 1; i++) {
            // Find the minimum element in unsorted array
            int minIndex = i;
            compareIndex1 = i;
            
            currentOperation = "Looking for minimum element starting from index " + i;
            draw();
            pause();
            
            for (int j = i + 1; j < values.length; j++) {
                // Override compare method for special case in selection sort
                compareIndex1 = minIndex;
                compareIndex2 = j;
                currentStep++;
                numberOfComparisons++;
                currentOperation = "Comparing elements at indices " + minIndex + " and " + j + 
                                   ": " + values[minIndex] + " > " + values[j] + "?";
                draw();
                pause();
                
                if (values[j] < values[minIndex]) {
                    minIndex = j;
                    compareIndex1 = j;
                    
                    currentOperation = "Found new minimum value " + values[j] + " at index " + j;
                    draw();
                    pause();
                }
            }
            
            // Swap the found minimum element with the first element if needed
            if (minIndex != i) {
                swap(i, minIndex);
            } else {
                currentOperation = "Element at index " + i + " is already the minimum";
                draw();
                pause();
            }
            
            currentOperation = "Completed iteration " + (i + 1) + " of " + (values.length - 1);
            compareIndex1 = compareIndex2 = -1;
            draw();
            pause();
        }
        
        markComplete();
    }
}
