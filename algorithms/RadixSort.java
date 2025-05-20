package algorithms;

import java.util.Arrays;
import visualization.*;

/**
 * Implementation of the Radix Sort algorithm
 */
public class RadixSort extends SortAlgorithm {

    public RadixSort(int[] array, Bar[] bars, int width, int height, int delay) {
        super(array, bars, width, height, delay);
        this.algorithmName = "Radix Sort";
    }

    @Override
    public void sort() {
        currentOperation = "Starting Radix Sort";
        draw();
        pause();

        // Find the maximum number to know number of digits
        int max = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
            }
        }

        // Do counting sort for every digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(exp);
        }

        markComplete();
    }

    private void countingSortByDigit(int exp) {
        int n = values.length;
        int[] output = new int[n];
        int[] count = new int[10]; // 0-9 digits

        currentOperation = "Sorting by digit at position: " + (int) Math.log10(exp);
        draw();
        pause();

        // Initialize count array
        Arrays.fill(count, 0);

        // Store count of occurrences
        for (int i = 0; i < n; i++) {
            int digit = (values[i] / exp) % 10;
            count[digit]++;

            compareIndex1 = i;
            currentOperation = "Examining digit " + digit + " at position " + (int) Math.log10(exp)
                    + " of element " + values[i];
            currentStep++;
            draw();
            pause();
        }

        // Change count[i] so that it contains position of this digit in output
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
            currentStep++;
        }

        // Build output array
        for (int i = n - 1; i >= 0; i--) {
            int digit = (values[i] / exp) % 10;
            output[count[digit] - 1] = values[i];
            count[digit]--;

            compareIndex1 = i;
            compareIndex2 = count[digit];
            currentOperation = "Placing " + values[i] + " in position " + count[digit]
                    + " based on digit " + digit;
            numberOfSwaps++;
            currentStep++;
            draw();
            pause();
        }

        // Copy the output array back to values
        for (int i = 0; i < n; i++) {
            values[i] = output[i];

            compareIndex1 = i;
            currentOperation = "Copying sorted value " + output[i] + " back to position " + i;
            updateBars();
            draw();
            pause();
        }

        compareIndex1 = compareIndex2 = -1;
        currentOperation = "Completed sorting by digit at position: " + (int) Math.log10(exp);
        draw();
        pause();
    }
}
