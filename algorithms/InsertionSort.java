package algorithms;

import visualization.*;

/**
 * Implementation of the Insertion Sort algorithm
 */
public class InsertionSort extends SortAlgorithm {
    
    public InsertionSort(int[] array, Bar[] bars, int width, int height, int delay) {
        super(array, bars, width, height, delay);
        this.algorithmName = "Insertion Sort";
    }
    
    @Override
    public void sort() {
        currentOperation = "Starting Insertion Sort";
        draw();
        pause();
        
        // Insertion sort algorithm
        for (int i = 1; i < values.length; i++) {
            int key = values[i];
            int j = i - 1;
            
            compareIndex1 = i;
            currentOperation = "Inserting element " + key + " into sorted portion";
            draw();
            pause();
            
            // Custom comparison and movement for insertion sort
            while (j >= 0) {
                currentStep++;
                numberOfComparisons++;
                
                compareIndex2 = j;
                currentOperation = "Comparing key " + key + " with element at index " + j + 
                                   " (" + values[j] + ")";
                draw();
                pause();
                
                if (values[j] > key) {
                    currentOperation = "Moving element " + values[j] + " one position ahead";
                    values[j + 1] = values[j];
                    j--;
                    
                    updateBars();
                    numberOfSwaps++;
                    draw();
                    pause();
                } else {
                    break;
                }
            }
            
            values[j + 1] = key;
            updateBars();
            
            currentOperation = "Placed key " + key + " at correct position " + (j + 1);
            compareIndex1 = j + 1;
            compareIndex2 = -1;
            draw();
            pause();
        }
        
        markComplete();
    }
}
