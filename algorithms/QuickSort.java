package algorithms;

import visualization.*;

/**
 * Implementation of the Quick Sort algorithm
 */
public class QuickSort extends SortAlgorithm {
    
    public QuickSort(int[] array, Bar[] bars, int width, int height, int delay) {
        super(array, bars, width, height, delay);
        this.algorithmName = "Quick Sort";
    }
    
    @Override
    public void sort() {
        currentOperation = "Starting Quick Sort";
        draw();
        pause();
        
        quickSortRecursive(0, values.length - 1);
        
        markComplete();
    }
    
    private void quickSortRecursive(int low, int high) {
        if (low < high) {
            int partitionIndex = partition(low, high);
            
            quickSortRecursive(low, partitionIndex - 1);
            quickSortRecursive(partitionIndex + 1, high);
        }
    }
    
    private int partition(int low, int high) {
        int pivot = values[high];
        
        currentOperation = "Partitioning section [" + low + ".." + high + "] with pivot " + pivot;
        draw();
        pause();
        
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            currentStep++;
            numberOfComparisons++;
            
            compareIndex1 = j;
            compareIndex2 = high;
            currentOperation = "Comparing element " + values[j] + " with pivot " + pivot;
            draw();
            pause();
            
            if (values[j] < pivot) {
                i++;
                
                if (i != j) {
                    swap(i, j);
                }
            }
        }
        
        currentOperation = "Moving pivot " + pivot + " to its correct position";
        
        int temp = values[i + 1];
        values[i + 1] = values[high];
        values[high] = temp;
        
        updateBars();
        
        numberOfSwaps++;
        
        compareIndex1 = i + 1;
        compareIndex2 = high;
        draw();
        pause();
        
        currentOperation = "Partition complete, pivot is now at position " + (i + 1);
        compareIndex1 = compareIndex2 = -1;
        draw();
        pause();
        
        return i + 1;
    }
}
