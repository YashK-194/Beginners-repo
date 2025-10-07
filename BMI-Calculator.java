import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        System.out.println("--- BMI Calculator ---");
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter weight in kilograms (kg): ");
        double weight = sc.nextDouble();
        
        System.out.print("Enter height in meters (m): ");
        double height = sc.nextDouble();
        
        // Formula: BMI = weight / (height * height)
        double bmi = weight / (height * height);
        
        System.out.printf("Your BMI is: %.2f%n", bmi);
        
        // --- New Feature: Interpret the BMI Score ---
        String category;
        if (bmi < 18.5) {
            category = "Underweight";
        } else if (bmi < 25) {
            category = "Normal weight";
        } else if (bmi < 30) {
            category = "Overweight";
        } else {
            category = "Obese";
        }
        
        System.out.println("Health Category: " + category);
        
        sc.close();
    }
}