import java.util.Scanner;

public class CompoundInterestCalculator {
    public static void main(String[] args) {
        System.out.println("--- Compound Interest Calculator ---");
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter principal amount (P): ");
        double principal = sc.nextDouble();
        
        System.out.print("Enter annual interest rate (in %): ");
        double ratePercent = sc.nextDouble();
        double rate = ratePercent / 100.0;  // 轉換成小數
        
        System.out.print("Enter time period in years (T): ");
        double time = sc.nextDouble();
        
        System.out.print("Enter number of times interest is compounded per year (N): ");
        System.out.print("\n  (1 = annually, 4 = quarterly, 12 = monthly): ");
        int n = sc.nextInt();
        
        // Calculate using the compound interest formula
        double finalAmount = calculateFinalAmount(principal, rate, time, n);
        double compoundInterest = finalAmount - principal;
        
        System.out.println("\n--- Results ---");
        System.out.printf("Principal Amount: $%.2f%n", principal);
        System.out.printf("Interest Rate: %.2f%%%n", ratePercent);
        System.out.printf("Time Period: %.1f years%n", time);
        System.out.printf("Compounding Frequency: %d times per year%n", n);
        System.out.printf("Final Amount: $%.2f%n", finalAmount);
        System.out.printf("Compound Interest Earned: $%.2f%n", compoundInterest);
        
        sc.close();
    }
    
    /**
     * Calculate the final amount using the compound interest formula:
     * A = P(1 + R/N)^(N*T)
     * 
     * Where:
     * A → Final Amount (Principal + Interest)
     * P → Principal amount
     * R → Annual Rate (in decimal)
     * T → Time in years
     * N → Number of times interest is compounded per year
     *     (e.g., 1 for annually, 12 for monthly, 4 for quarterly)
     * 
     * @param principal The principal amount (P)
     * @param rate The annual interest rate as a decimal (R)
     * @param time The time period in years (T)
     * @param compoundingFrequency The number of times interest is compounded per year (N)
     * @return The final amount after compound interest
     */
    public static double calculateFinalAmount(double principal, double rate, double time, int compoundingFrequency) {
        // Apply the compound interest formula: A = P(1 + R/N)^(N*T)
        double finalAmount = principal * Math.pow(1 + (rate / compoundingFrequency), compoundingFrequency * time);
        return finalAmount;
    }
}