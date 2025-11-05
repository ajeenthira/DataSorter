package datasorter;

public class QuickSort {
    private long stepCount;
    private long startTime;
    private long endTime;
    
    public QuickSort() {
        stepCount = 0;
    }
    
    public void sort(int[] array) {
        stepCount = 0;
        startTime = System.nanoTime();
        
        if (array.length > 1) {
            quickSort(array, 0, array.length - 1);
        }
        
        endTime = System.nanoTime();
    }
    
    private void quickSort(int[] array, int low, int high) {
        stepCount++; // method call
        if (low < high) {
            int pi = partition(array, low, high);
            stepCount++; // partition index
            
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }
    
    private int partition(int[] array, int low, int high) {
        stepCount++; // method call
        
        int pivot = array[high];
        stepCount++; // pivot assignment
        
        int i = (low - 1);
        stepCount++; // index initialization
        
        for (int j = low; j < high; j++) {
            stepCount++; // for loop
            stepCount++; // comparison
            if (array[j] <= pivot) {
                i++;
                stepCount++; // increment i
                
                // Swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                stepCount += 3; // swap operations
            }
        }
        
        // Swap array[i+1] and array[high] (pivot)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        stepCount += 3; // swap operations
        
        return i + 1;
    }
    
    public long getStepCount() {
        return stepCount;
    }
    
    public long getTimeTaken() {
        return endTime - startTime;
    }
    
    public void displaySortedArray(int[] array) {
        System.out.print("Quick Sort Result: [");
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