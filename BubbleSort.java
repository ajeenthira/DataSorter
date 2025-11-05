// Implementation by [Sarfan] - [22UG3-0064]
// CIT300 Assignment 2 - [BubbleSort]package datasorter;

public class BubbleSort {
    private long stepCount;
    private long startTime;
    private long endTime;
    
    public BubbleSort() {
        stepCount = 0;
    }
    
    public void sort(int[] array) {
        stepCount = 0;
        startTime = System.nanoTime();
        
        int n = array.length;
        stepCount++; // initialization
        
        for (int i = 0; i < n - 1; i++) {
            stepCount++; // outer loop
            for (int j = 0; j < n - i - 1; j++) {
                stepCount++; // inner loop
                stepCount++; // comparison
                if (array[j] > array[j + 1]) {
                    // Swap elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    stepCount += 3; // 3 assignments for swap
                }
            }
        }
        
        endTime = System.nanoTime();
    }
    
    public long getStepCount() {
        return stepCount;
    }
    
    public long getTimeTaken() {
        return endTime - startTime;
    }
    
    public void displaySortedArray(int[] array) {
        System.out.print("Bubble Sort Result: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println("Steps: " + getStepCount() + " | Time: " + getTimeTaken() + " ns");
    }

}
