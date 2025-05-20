# Sorting Algorithm Visualizer

This program provides a console-based visualization of various sorting algorithms, allowing you to see the step-by-step process of how different sorting techniques work.

## Overview

The Sorting Algorithm Visualizer demonstrates seven popular sorting algorithms:
1. Bubble Sort
2. Selection Sort
3. Insertion Sort
4. Merge Sort
5. Quick Sort
6. Heap Sort
7. Radix Sort

Each algorithm is visualized in the console using ASCII graphics, with statistics tracking comparisons, swaps, and operations performed during the sorting process.

## Directory Structure

```
Sorting_Algorithm_Visualizer/
├── README.md                     # This documentation file
├── SortVisualizer.java           # Main program entry point
├── algorithms/                   # Package containing sorting algorithms
│   ├── SortAlgorithm.java        # Abstract base class for all sorting algorithms
│   ├── BubbleSort.java           # Bubble sort implementation
│   ├── SelectionSort.java        # Selection sort implementation
│   ├── InsertionSort.java        # Insertion sort implementation
│   ├── MergeSort.java            # Merge sort implementation
│   ├── QuickSort.java            # Quick sort implementation
│   ├── HeapSort.java             # Heap sort implementation
│   └── RadixSort.java            # Radix sort implementation
└── visualization/                # Package for visualization components
    ├── Bar.java                  # Represents a visual bar in the chart
    ├── LineDrawing.java          # Line drawing algorithm
    └── Pixel.java                # Pixel representation for drawing
```

## How to Run the Program

### Prerequisites
- Java Development Kit (JDK) 15 or newer installed
- Command-line terminal or compatible IDE

### Compilation

1. Open a command prompt/terminal
2. Navigate to the project directory:
   ```
   cd path/to/Sorting_Algorithm_Visualizer
   ```
3. Compile the program:
   ```
   javac SortVisualizer.java
   ```

### Running the Program

After successful compilation, run the program with:
```
java SortVisualizer
```

## Program Features

### 1. Array Input Options
- Enter your own custom array of integers
- Use a sample array `[64, 34, 25, 12, 22, 11, 90]`
- Generate a random array of your desired size and range

### 2. Sorting Algorithm Selection
Choose from seven different sorting algorithms to visualize:
- Bubble Sort: Simple comparison-based algorithm that repeatedly steps through the list
- Selection Sort: Simple in-place comparison sort that finds the minimum element
- Insertion Sort: Builds sorted array one item at a time
- Merge Sort: Efficient divide-and-conquer algorithm
- Quick Sort: Highly efficient divide-and-conquer algorithm
- Heap Sort: Comparison-based sort that uses a binary heap data structure
- Radix Sort: Non-comparative sorting algorithm that sorts data based on individual digits

### 3. Animation Speed Control
Adjust the delay between steps to control the visualization speed.

### 4. Visualization Features
- Vertical bars represent elements in the array
- Bars being compared or swapped are highlighted with `#` character
- Current operation is described in text
- Statistics track comparisons, swaps, and current step

## Controls and Usage

1. When prompted, choose how to input your array (custom, sample, or random)
2. Select a sorting algorithm to visualize (1-7)
3. Set the animation delay (in milliseconds)
4. Watch the sorting process in action
5. After completion, view the sorted array
6. Press Enter to return to the algorithm menu
7. Select option 0 to exit the program

## Understanding the Visualization

- Each vertical bar represents an element in the array
- The height of a bar corresponds to the value of the element
- Bars currently being compared or swapped are highlighted with `#` character
- The value of each element is displayed below its bar
- The index position is shown at the bottom

## Troubleshooting

### Screen Clearing Issues
The program uses ANSI escape sequences to clear the console. If the visualization doesn't display correctly:
- Try running in a terminal that supports ANSI escape codes (Windows Terminal, Git Bash, etc.)
- If using Windows Command Prompt, you might see escape characters instead of screen clearing

### Compilation Errors
- Ensure you're using JDK 15+ which supports the switch expressions
- Verify all files are in the correct directories with proper package declarations
- Check that no duplicate files exist in the root directory

### Display Issues
- For optimal visualization, use a console with at least 80×25 characters
- Resize your terminal window if the visualization appears cut off or distorted

## Educational Value

This visualization tool helps in understanding:
- How different sorting algorithms work internally
- The number of comparisons and swaps different algorithms require
- Time complexity differences through direct observation
- Trade-offs between simple and advanced sorting algorithms

## Implementation Details

The program uses:
- Object-oriented design with inheritance for sorting algorithms
- Java Reflection API for cross-package method invocation
- ASCII art techniques to create visual bar charts
- Real-time console-based animation
