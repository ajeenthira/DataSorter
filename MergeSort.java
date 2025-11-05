package datasorter;

public class MergeSort {
    private long stepCount;
    private long startTime;
    private long endTime;
    
    public MergeSort() {
        stepCount = 0;
    }
    
    public void sort(int[] array) {
        stepCount = 0;
        startTime = System.nanoTime();
        
        if (array.length > 1) {
            mergeSort(array, 0, array.length - 1);
        }
        
        endTime = System.nanoTime();
    }
    
    private void mergeSort(int[] array, int left, int right) {
        stepCount++; // method call
        if (left < right) {
            int mid = (left + right) / 2;
            stepCount++; // calculation
            
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            
            merge(array, left, mid, right);
        }
    }
    
    private void merge(int[] array, int left, int mid, int right) {
        stepCount++; // method call
        
        // Create temporary arrays
        int n1 = mid - left + 1;
        int n2 = right - mid;
        stepCount += 2; // calculations
        
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        stepCount += 2; // array creations
        
        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
            stepCount++; // assignment
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
            stepCount++; // assignment
        }
        
        // Merge the temporary arrays
        int i = 0, j = 0, k = left;
        stepCount += 3; // initializations
        
        while (i < n1 && j < n2) {
            stepCount++; // while condition
            stepCount++; // comparison
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
                stepCount += 2; // assignment and increment
            } else {
                array[k] = rightArray[j];
                j++;
                stepCount += 2; // assignment and increment
            }
            k++;
            stepCount++; // increment
        }
        
        // Copy remaining elements
        while (i < n1) {
            stepCount++; // while condition
            array[k] = leftArray[i];
            i++;
            k++;
            stepCount += 3; // assignment and increments
        }
        
        while (j < n2) {
            stepCount++; // while condition
            array[k] = rightArray[j];
            j++;
            k++;
            stepCount += 3; // assignment and increments
        }
    }
    
    public long getStepCount() {
        return stepCount;
    }
    
    public long getTimeTaken() {
        return endTime - startTime;
    }
    
    public void displaySortedArray(int[] array) {
        System.out.print("Merge Sort Result: [");
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