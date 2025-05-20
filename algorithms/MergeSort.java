package algorithms;

import visualization.*;

/**
 * Implementation of the Merge Sort algorithm
 */
public class MergeSort extends SortAlgorithm {
    
    public MergeSort(int[] array, Bar[] bars, int width, int height, int delay) {
        super(array, bars, width, height, delay);
        this.algorithmName = "Merge Sort";
    }
    
    @Override
    public void sort() {
        currentOperation = "Starting Merge Sort";
        draw();
        pause();
        
        mergeSortRecursive(0, values.length - 1);
        
        markComplete();
    }
    
    private void mergeSortRecursive(int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            
            currentOperation = "Dividing array section [" + left + ".." + right + "] at middle point " + middle;
            draw();
            pause();
            
            mergeSortRecursive(left, middle);
            mergeSortRecursive(middle + 1, right);
            
            merge(left, middle, right);
        }
    }
    
    private void merge(int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;
        
        currentOperation = "Merging subarrays [" + left + ".." + middle + "] and [" + (middle + 1) + ".." + right + "]";
        draw();
        pause();
        
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        
        for (int i = 0; i < n1; i++) {
            leftArray[i] = values[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = values[middle + 1 + j];
        }
        
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            currentStep++;
            numberOfComparisons++;
            
            compareIndex1 = left + i;
            compareIndex2 = middle + 1 + j;
            currentOperation = "Comparing elements: " + leftArray[i] + " <= " + rightArray[j] + "?";
            draw();
            pause();
            
            if (leftArray[i] <= rightArray[j]) {
                values[k] = leftArray[i];
                i++;
            } else {
                values[k] = rightArray[j];
                j++;
                numberOfSwaps++;
            }
            
            updateBars();
            
            currentOperation = "Placed value " + values[k] + " at position " + k;
            draw();
            pause();
            
            k++;
        }
        
        while (i < n1) {
            compareIndex1 = left + i;
            compareIndex2 = -1;
            currentOperation = "Copying remaining element from left array: " + leftArray[i];
            
            values[k] = leftArray[i];
            i++;
            k++;
            
            updateBars();
            draw();
            pause();
        }
        
        while (j < n2) {
            compareIndex1 = -1;
            compareIndex2 = middle + 1 + j;
            currentOperation = "Copying remaining element from right array: " + rightArray[j];
            
            values[k] = rightArray[j];
            j++;
            k++;
            
            updateBars();
            draw();
            pause();
        }
        
        currentOperation = "Merged section [" + left + ".." + right + "]";
        compareIndex1 = compareIndex2 = -1;
        draw();
        pause();
    }
}
