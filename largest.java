import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> numbers = new ArrayList<>();
        
        System.out.println("Enter numbers one by one. Type 'done' when finished:");

        // Input loop with validation
        while (true) {
            System.out.print("Enter number (or 'done'): ");
            String input = sc.next();

            // Check if user wants to stop
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            try {
                double num = Double.parseDouble(input);
                numbers.add(num);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        // Check if any numbers were entered
        if (numbers.isEmpty()) {
            System.out.println("No numbers entered. Exiting...");
            return;
        }

        // Finding required values
        double largest = Collections.max(numbers);
        double smallest = Collections.min(numbers);
        double sum = 0;

        for (double num : numbers) {
            sum += num;
        }

        double average = sum / numbers.size();
        double range = largest - smallest;

        // Displaying results
        System.out.println("\n=== Analysis Report ===");
        System.out.println("Numbers Entered: " + numbers);
        System.out.println("Count: " + numbers.size());
        System.out.println("Largest Number: " + largest);
        System.out.println("Smallest Number: " + smallest);
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
        System.out.println("Range (Largest - Smallest): " + range);

        sc.close();
    }
}
