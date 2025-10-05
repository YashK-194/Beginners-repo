import java.util.InputMismatchException;
import java.util.Scanner;

public class UnitConverter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        // Main loop to keep the program running until the user decides to exit.
        while (true) {
            printMenu();
            try {
                System.out.print("\nEnter your choice: ");
                choice = sc.nextInt();
                
                // Direct the user to the correct conversion based on their choice.
                switch (choice) {
                    case 1:
                        convertKilometersToMiles(sc);
                        break;
                    case 2:
                        convertMilesToKilometers(sc);
                        break;
                    case 3:
                        convertMetersToFeet(sc);
                        break;
                    case 4:
                        convertFeetToMeters(sc);
                        break;
                    case 5:
                        convertCelsiusToFahrenheit(sc);
                        break;
                    case 6:
                        convertFahrenheitToCelsius(sc);
                        break;
                    case 0:
                        System.out.println("Thank you for using the Unit Converter. Goodbye!");
                        sc.close(); // Close the scanner before exiting.
                        return; // Exit the main method, terminating the program.
                    default:
                        System.out.println("Invalid choice. Please select a valid option from the menu.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear the invalid input from the scanner buffer.
            }
            System.out.println("\n----------------------------------------");
        }
    }

    /**
     * Prints the main menu of conversion options.
     */
    public static void printMenu() {
        System.out.println("--- Unit Converter Menu ---");
        System.out.println("1. Kilometers to Miles");
        System.out.println("2. Miles to Kilometers");
        System.out.println("3. Meters to Feet");
        System.out.println("4. Feet to Meters");
        System.out.println("5. Celsius to Fahrenheit");
        System.out.println("6. Fahrenheit to Celsius");
        System.out.println("0. Exit");
    }

    /**
     * Handles the logic for Kilometers to Miles conversion.
     * @param sc The Scanner object to read user input.
     */
    public static void convertKilometersToMiles(Scanner sc) {
        try {
            System.out.print("Enter distance in kilometers: ");
            double kilometers = sc.nextDouble();
            if (kilometers < 0) {
                System.out.println("Error: Distance cannot be negative.");
                return;
            }
            double miles = kilometers * 0.621371;
            System.out.printf("%.2f kilometers is equal to %.2f miles.%n", kilometers, miles);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for kilometers.");
            sc.next(); // Clear the buffer
        }
    }

    /**
     * Handles the logic for Miles to Kilometers conversion.
     * @param sc The Scanner object to read user input.
     */
    public static void convertMilesToKilometers(Scanner sc) {
        try {
            System.out.print("Enter distance in miles: ");
            double miles = sc.nextDouble();
             if (miles < 0) {
                System.out.println("Error: Distance cannot be negative.");
                return;
            }
            double kilometers = miles / 0.621371;
            System.out.printf("%.2f miles is equal to %.2f kilometers.%n", miles, kilometers);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for miles.");
            sc.next(); // Clear the buffer
        }
    }

    /**
     * Handles the logic for Meters to Feet conversion.
     * @param sc The Scanner object to read user input.
     */
    public static void convertMetersToFeet(Scanner sc) {
        try {
            System.out.print("Enter distance in meters: ");
            double meters = sc.nextDouble();
             if (meters < 0) {
                System.out.println("Error: Distance cannot be negative.");
                return;
            }
            double feet = meters * 3.28084;
            System.out.printf("%.2f meters is equal to %.2f feet.%n", meters, feet);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for meters.");
            sc.next(); // Clear the buffer
        }
    }

    /**
     * Handles the logic for Feet to Meters conversion.
     * @param sc The Scanner object to read user input.
     */
    public static void convertFeetToMeters(Scanner sc) {
        try {
            System.out.print("Enter distance in feet: ");
            double feet = sc.nextDouble();
             if (feet < 0) {
                System.out.println("Error: Distance cannot be negative.");
                return;
            }
            double meters = feet / 3.28084;
            System.out.printf("%.2f feet is equal to %.2f meters.%n", feet, meters);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for feet.");
            sc.next(); // Clear the buffer
        }
    }
    
    /**
     * Handles the logic for Celsius to Fahrenheit conversion.
     * @param sc The Scanner object to read user input.
     */
    public static void convertCelsiusToFahrenheit(Scanner sc) {
        try {
            System.out.print("Enter temperature in Celsius: ");
            double celsius = sc.nextDouble();
            double fahrenheit = (celsius * 9/5) + 32;
            System.out.printf("%.2f°C is equal to %.2f°F.%n", celsius, fahrenheit);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for Celsius.");
            sc.next(); // Clear the buffer
        }
    }

    /**
     * Handles the logic for Fahrenheit to Celsius conversion.
     * @param sc The Scanner object to read user input.
     */
    public static void convertFahrenheitToCelsius(Scanner sc) {
        try {
            System.out.print("Enter temperature in Fahrenheit: ");
            double fahrenheit = sc.nextDouble();
            double celsius = (fahrenheit - 32) * 5/9;
            System.out.printf("%.2f°F is equal to %.2f°C.%n", fahrenheit, celsius);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for Fahrenheit.");
            sc.next(); // Clear the buffer
        }
    }
}