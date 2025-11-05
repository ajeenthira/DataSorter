// Implementation by K. Ajeenthira - 22UG3-0074
// CIT300 Assignment 2 - DataSorterApp
package datasorter;

import java.util.Scanner;
import java.util.Random;

public class DataSorterApp {
    private Scanner scanner;
    private int[] numbers;
    private BubbleSort bubbleSort;
    private MergeSort mergeSort;
    private QuickSort quickSort;
    
    public DataSorterApp() {
        scanner = new Scanner(System.in);
        bubbleSort = new BubbleSort();
        mergeSort = new MergeSort();
        quickSort = new QuickSort();
        numbers = new int[0]; // Empty array initially
    }
    
    public void start() {
        System.out.println("🚀 Welcome to Data Sorter: Sorting Algorithm Comparison Tool!");
        System.out.println("=============================================================");
        
        int choice;
        
        do {
            displayMenu();
            choice = getValidIntegerInput("Enter your choice (1-7): ");
            
            switch (choice) {
                case 1:
                    enterNumbersManually();
                    break;
                case 2:
                    generateRandomNumbers();
                    break;
                case 3:
                    performBubbleSort();
                    break;
                case 4:
                    performMergeSort();
                    break;
                case 5:
                    performQuickSort();
                    break;
                case 6:
                    compareAllAlgorithms();
                    break;
                case 7:
                    System.out.println("Thank you for using Data Sorter! Goodbye! 👋");
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please enter a number between 1-7.");
            }
            
            if (choice != 7) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        } while (choice != 7);
        
        scanner.close();
    }
    
    private void displayMenu() {
        System.out.println("\n=== Data Sorter: Sorting Algorithm Comparison Tool ===");
        System.out.println("1. Enter numbers manually");
        System.out.println("2. Generate random numbers");
        System.out.println("3. Perform Bubble Sort");
        System.out.println("4. Perform Merge Sort");
        System.out.println("5. Perform Quick Sort");
        System.out.println("6. Compare all algorithms (show performance table)");
        System.out.println("7. Exit");
        System.out.println("======================================================");
    }
    
    private void enterNumbersManually() {
        System.out.println("\n--- Enter Numbers Manually ---");
        System.out.print("How many numbers do you want to enter? ");
        int count = getValidIntegerInput("");
        
        if (count <= 0) {
            System.out.println("❌ Error: Please enter a positive number.");
            return;
        }
        
        numbers = new int[count];
        System.out.println("Enter " + count + " numbers:");
        
        for (int i = 0; i < count; i++) {
            System.out.print("Number " + (i + 1) + ": ");
            numbers[i] = getValidIntegerInput("");
        }
        
        System.out.println("✅ " + count + " numbers entered successfully!");
        displayCurrentArray();
    }
    
    private void generateRandomNumbers() {
        System.out.println("\n--- Generate Random Numbers ---");
        System.out.print("How many random numbers to generate? ");
        int count = getValidIntegerInput("");
        
        if (count <= 0) {
            System.out.println("❌ Error: Please enter a positive number.");
            return;
        }
        
        System.out.print("Enter maximum value for random numbers: ");
        int max = getValidIntegerInput("");
        
        numbers = new int[count];
        Random random = new Random();
        
        for (int i = 0; i < count; i++) {
            numbers[i] = random.nextInt(max + 1);
        }
        
        System.out.println("✅ " + count + " random numbers generated (0 to " + max + ")!");
        displayCurrentArray();
    }
    
    private void performBubbleSort() {
        if (!validateArray()) return;
        
        System.out.println("\n--- Performing Bubble Sort ---");
        displayCurrentArray();
        
        int[] arrayCopy = copyArray();
        bubbleSort.sort(arrayCopy);
        bubbleSort.displaySortedArray(arrayCopy);
    }
    
    private void performMergeSort() {
        if (!validateArray()) return;
        
        System.out.println("\n--- Performing Merge Sort ---");
        displayCurrentArray();
        
        int[] arrayCopy = copyArray();
        mergeSort.sort(arrayCopy);
        mergeSort.displaySortedArray(arrayCopy);
    }
    
    private void performQuickSort() {
        if (!validateArray()) return;
        
        System.out.println("\n--- Performing Quick Sort ---");
        displayCurrentArray();
        
        int[] arrayCopy = copyArray();
        quickSort.sort(arrayCopy);
        quickSort.displaySortedArray(arrayCopy);
    }
    
    private void compareAllAlgorithms() {
        if (!validateArray()) return;
        
        System.out.println("\n--- Algorithm Performance Comparison ---");
        displayCurrentArray();
        
        // Test Bubble Sort
        int[] bubbleArray = copyArray();
        bubbleSort.sort(bubbleArray);
        
        // Test Merge Sort
        int[] mergeArray = copyArray();
        mergeSort.sort(mergeArray);
        
        // Test Quick Sort
        int[] quickArray = copyArray();
        quickSort.sort(quickArray);
        
        // Display comparison table
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ALGORITHM PERFORMANCE COMPARISON");
        System.out.println("=".repeat(80));
        System.out.printf("%-15s %-20s %-25s %-15s\n", "Algorithm", "Time Taken (ns)", "Step Count", "Efficiency");
        System.out.println("-".repeat(80));
        
        System.out.printf("%-15s %-20d %-25d %-15s\n", 
            "Bubble Sort", bubbleSort.getTimeTaken(), bubbleSort.getStepCount(), 
            getEfficiencyRating(bubbleSort.getStepCount(), numbers.length));
        
        System.out.printf("%-15s %-20d %-25d %-15s\n", 
            "Merge Sort", mergeSort.getTimeTaken(), mergeSort.getStepCount(), 
            getEfficiencyRating(mergeSort.getStepCount(), numbers.length));
        
        System.out.printf("%-15s %-20d %-25d %-15s\n", 
            "Quick Sort", quickSort.getTimeTaken(), quickSort.getStepCount(), 
            getEfficiencyRating(quickSort.getStepCount(), numbers.length));
        
        System.out.println("=".repeat(80));
        
        // Show sorted results are identical
        System.out.println("✓ All algorithms produced identical sorted results: " + 
                         (arraysEqual(bubbleArray, mergeArray) && 
                          arraysEqual(mergeArray, quickArray)));
    }
    
    private String getEfficiencyRating(long steps, int n) {
        if (steps < n * Math.log(n) / Math.log(2)) {
            return "Excellent";
        } else if (steps < n * n / 2) {
            return "Good";
        } else {
            return "Fair";
        }
    }
    
    private boolean arraysEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }
    
    private boolean validateArray() {
        if (numbers == null || numbers.length == 0) {
            System.out.println("❌ Error: No numbers available. Please enter or generate numbers first.");
            return false;
        }
        return true;
    }
    
    private void displayCurrentArray() {
        System.out.print("Current Array: [");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i]);
            if (i < numbers.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("] (Length: " + numbers.length + ")");
    }
    
    private int[] copyArray() {
        int[] copy = new int[numbers.length];
        System.arraycopy(numbers, 0, copy, 0, numbers.length);
        return copy;
    }
    
    private int getValidIntegerInput(String prompt) {
        while (true) {
            if (!prompt.isEmpty()) {
                System.out.print(prompt);
            }
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a valid integer.");
                if (!prompt.isEmpty()) {
                    System.out.print(prompt);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        DataSorterApp app = new DataSorterApp();
        app.start();
    }

}
