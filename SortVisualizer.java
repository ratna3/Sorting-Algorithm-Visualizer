import java.util.*;
import visualization.*;

/**
 * Class that visualizes sorting algorithms in the console.
 * Creates a visual representation of the sorting process as it happens.
 */
public class SortVisualizer {
    /**
     * Draw the current state of the visualization
     */
    public static void draw(Bar[] bars, int[] values, int width, int height, 
                           String algorithmName, int currentStep, int comparisons, 
                           int swaps, String operation, int compareIndex1, int compareIndex2) {
        // Create a 2D grid to represent the console
        char[][] grid = new char[height][width];
        
        // Initialize the grid with spaces
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = ' ';
            }
        }
        
        // Draw each bar
        for (int i = 0; i < bars.length; i++) {
            Bar bar = bars[i];
            TreeMap<Pixel, Double> pixels = bar.draw();
            
            char barChar = '|';
            
            // Highlight bars being compared or swapped
            if (i == compareIndex1 || i == compareIndex2) {
                barChar = '#'; // Special character for highlighted elements
            }
            
            for (Map.Entry<Pixel, Double> entry : pixels.entrySet()) {
                Pixel pixel = entry.getKey();
                double intensity = entry.getValue();
                
                // Only draw if within bounds
                if (pixel.getX() >= 0 && pixel.getX() < width && 
                    pixel.getY() >= 0 && pixel.getY() < height) {
                    
                    // Use different characters based on intensity and highlighting
                    if (intensity > 0.8) {
                        grid[pixel.getY()][pixel.getX()] = barChar;
                    } else if (intensity > 0.4) {
                        grid[pixel.getY()][pixel.getX()] = ':';
                    } else {
                        grid[pixel.getY()][pixel.getX()] = '.';
                    }
                }
            }
            
            // Add the value number below each bar
            String valueStr = String.valueOf(values[i]);
            int xPos = bar.getX() - valueStr.length()/2;
            int yPos = height - 3;
            
            for (int c = 0; c < valueStr.length(); c++) {
                if (xPos + c >= 0 && xPos + c < width && yPos >= 0 && yPos < height) {
                    grid[yPos][xPos + c] = valueStr.charAt(c);
                }
            }
            
            // Add index number below the value
            String indexStr = String.valueOf(i);
            xPos = bar.getX() - indexStr.length()/2;
            yPos = height - 1;
            
            for (int c = 0; c < indexStr.length(); c++) {
                if (xPos + c >= 0 && xPos + c < width && yPos >= 0 && yPos < height) {
                    grid[yPos][xPos + c] = indexStr.charAt(c);
                }
            }
        }
        
        // Clear screen (ANSI escape sequence)
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        // Print algorithm information header
        System.out.println("==== " + algorithmName + " Visualization ====");
        System.out.println("Step: " + currentStep + " | Comparisons: " + comparisons + " | Swaps: " + swaps);
        System.out.println("Current operation: " + operation);
        System.out.println("=================================");
        
        // Print grid
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
        
        // Print legend
        System.out.println("=================================");
        System.out.println("Legend: | = Bar  # = Currently being operated on");
        System.out.println("=================================");
    }
    
    /**
     * Creates and initializes the bars for visualization
     */
    private static Bar[] createBars(int[] array, int width, int height) {
        int maxValue = findMaxValue(array);
        if (maxValue == 0) maxValue = 1;
        
        int spacing = width / (array.length + 1);
        
        Bar[] bars = new Bar[array.length];
        for (int i = 0; i < array.length; i++) {
            // Calculate bar height proportional to the value (with a minimum height of 1)
            int barHeight = Math.max(1, (int)((array[i] / (double)maxValue) * (height - 10)));
            bars[i] = new Bar((i + 1) * spacing, barHeight, height - 5);
        }
        
        return bars;
    }
    
    /**
     * Find maximum value in an array
     */
    private static int findMaxValue(int[] array) {
        if (array.length == 0) return 0;
        
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
    
    /**
     * Main method to run the visualization with user input
     */
    public static void main(String[] args) {
        // Using try-with-resources for Scanner
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("===== Sorting Algorithm Visualizer =====");
            
            // Get array input
            int[] array = getArrayInput(scanner);
            
            // Display algorithm menu and get selection
            while (true) {
                System.out.println("\nChoose a sorting algorithm:");
                System.out.println("1. Bubble Sort");
                System.out.println("2. Selection Sort");
                System.out.println("3. Insertion Sort");
                System.out.println("4. Merge Sort");
                System.out.println("5. Quick Sort");
                System.out.println("6. Heap Sort");
                System.out.println("0. Exit");
                
                System.out.print("Enter your choice: ");
                int choice = getIntegerInput(scanner, 0, 6);
                
                if (choice == 0) {
                    System.out.println("Thank You for Learning. Goodbye!");
                    break;
                }
                
                // Get visualization parameters
                System.out.print("Enter animation delay (milliseconds, recommended 500): ");
                int delay = getIntegerInput(scanner, 0, 2000);
                
                // Set up visualization dimensions
                int width = 80;
                int height = 25;
                
                // Create bars for visualization
                Bar[] bars = createBars(array, width, height);
                
                // Create and run the selected algorithm
                algorithms.SortAlgorithm sorter = null;
                
                switch (choice) {
                    case 1 -> sorter = new algorithms.BubbleSort(array, bars, width, height, delay);
                    case 2 -> sorter = new algorithms.SelectionSort(array, bars, width, height, delay);
                    case 3 -> sorter = new algorithms.InsertionSort(array, bars, width, height, delay);
                    case 4 -> sorter = new algorithms.MergeSort(array, bars, width, height, delay);
                    case 5 -> sorter = new algorithms.QuickSort(array, bars, width, height, delay);
                    case 6 -> sorter = new algorithms.HeapSort(array, bars, width, height, delay);
                }
                
                // Run the sorting algorithm
                if (sorter != null) {
                    sorter.sort();
                    
                    // Print the sorted array for verification
                    int[] sortedArray = sorter.getSortedArray();
                    System.out.println("\nFinal sorted array:");
                    for (int value : sortedArray) {
                        System.out.print(value + " ");
                    }
                } else {
                    System.out.println("\nError: Could not initialize sorting algorithm.");
                }
                
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }
    
    /**
     * Gets integer input from user within a specified range
     */
    private static int getIntegerInput(Scanner scanner, int min, int max) {
        int input = -1;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= min && input <= max) {
                    validInput = true;
                } else {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        
        return input;
    }
    
    /**
     * Gets array input from user
     */
    private static int[] getArrayInput(Scanner scanner) {
        System.out.println("\nEnter array elements to sort:");
        System.out.println("1. Enter custom array");
        System.out.println("2. Use sample array [64, 34, 25, 12, 22, 11, 90]");
        System.out.println("3. Generate random array");
        
        System.out.print("Choose an option (1-3): ");
        int option = getIntegerInput(scanner, 1, 3);
        
        return switch (option) {
            case 1 -> {
                // Custom array input
                System.out.print("Enter array elements separated by spaces: ");
                String input = scanner.nextLine().trim();
                String[] elements = input.split("\\s+");
                
                int[] arr = new int[elements.length];
                for (int i = 0; i < elements.length; i++) {
                    try {
                        arr[i] = Integer.parseInt(elements[i]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number: " + elements[i] + ". Using 0 instead.");
                        arr[i] = 0;
                    }
                }
                yield arr;
            }
            case 2 -> new int[]{64, 34, 25, 12, 22, 11, 90}; // Sample array
            case 3 -> {
                // Random array
                System.out.print("Enter size of random array (5-20 recommended): ");
                int size = getIntegerInput(scanner, 1, 100);
                
                System.out.print("Enter maximum value for random numbers: ");
                int maxValue = getIntegerInput(scanner, 1, 1000);
                
                int[] arr = new int[size];
                Random random = new Random();
                for (int i = 0; i < size; i++) {
                    arr[i] = random.nextInt(maxValue) + 1;
                }
                
                System.out.print("Generated array: ");
                for (int value : arr) {
                    System.out.print(value + " ");
                }
                System.out.println();
                
                yield arr;
            }
            default -> new int[]{0}; // Should never happen
        };
    }
}
