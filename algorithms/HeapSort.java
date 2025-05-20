package algorithms;

import visualization.*;

/**
 * Implementation of the Heap Sort algorithm
 */
public class HeapSort extends SortAlgorithm {
    
    public HeapSort(int[] array, Bar[] bars, int width, int height, int delay) {
        super(array, bars, width, height, delay);
        this.algorithmName = "Heap Sort";
    }
    
    @Override
    public void sort() {
        currentOperation = "Starting Heap Sort";
        draw();
        pause();
        
        int n = values.length;
        
        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }
        
        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            currentOperation = "Moving root " + values[0] + " to the end";
            
            int temp = values[0];
            values[0] = values[i];
            values[i] = temp;
            
            updateBars();
            numberOfSwaps++;
            
            compareIndex1 = 0;
            compareIndex2 = i;
            draw();
            pause();
            
            heapify(i, 0);
        }
        
        markComplete();
    }
    
    private void heapify(int n, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        
        currentOperation = "Heapifying subtree rooted at index " + root;
        compareIndex1 = root;
        draw();
        pause();
        
        if (left < n) {
            numberOfComparisons++;
            
            compareIndex2 = left;
            currentOperation = "Comparing left child " + values[left] + " with root " + values[largest];
            draw();
            pause();
            
            if (values[left] > values[largest]) {
                largest = left;
                compareIndex1 = left;
                
                currentOperation = "Left child is larger than root";
                draw();
                pause();
            }
        }
        
        if (right < n) {
            numberOfComparisons++;
            
            compareIndex2 = right;
            currentOperation = "Comparing right child " + values[right] + " with largest " + values[largest];
            draw();
            pause();
            
            if (values[right] > values[largest]) {
                largest = right;
                compareIndex1 = right;
                
                currentOperation = "Right child is largest";
                draw();
                pause();
            }
        }
        
        if (largest != root) {
            currentOperation = "Swapping root with largest child: " + 
                               values[root] + " <-> " + values[largest];
            
            int temp = values[root];
            values[root] = values[largest];
            values[largest] = temp;
            
            updateBars();
            numberOfSwaps++;
            
            compareIndex1 = root;
            compareIndex2 = largest;
            draw();
            pause();
            
            heapify(n, largest);
        } else {
            currentOperation = "Heap property satisfied for node " + root;
            compareIndex1 = compareIndex2 = -1;
            draw();
            pause();
        }
    }
}
