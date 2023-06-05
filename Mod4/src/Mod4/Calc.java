package Mod4;


import java.util.Scanner;

public class Calc {
    public void run() {
        try (Scanner userType = new Scanner(System.in)) {
            System.out.println("Please enter beginning principal amount.");
            double amount = userType.nextDouble();

            System.out.println("Please enter annual interest rate, e.g. 0.06");
            float interestRate = userType.nextFloat();

            System.out.println("Please enter number of payment periods (e.g. total # of months)");
            int months = userType.nextInt();

            float monthlyIR = interestRate / 12;

            displayAmortizationTable(amount, monthlyIR, months);
        }

    }

    public static double calcMonthlyPayments(double amount, double rate, int months) {
        return (rate * amount) / (1 - Math.pow(1 + rate, -months));
    }

    public static void displayAmortizationTable(double amount, double rate, int months) {
        double balance = amount;
        double payment = calcMonthlyPayments(amount, rate, months);
        double irPaid, amountPaid, newBalance;

        System.out.printf("%n");
        System.out.printf("%n");

        System.out.printf(" %-8s  %.2f %n", "Monthly Payment", payment);

        System.out.printf("%n");

        System.out.printf("%20s %20s %20s %20s %20s%n", "Current Month",
                "Starting Principal", "Interest Payment", "Principal Payment", "Ending Principal");

        for (int month = 1; month <= months; month++) {
            irPaid = balance * rate;
            amountPaid = payment - irPaid;

            newBalance = balance - amountPaid;

            System.out.printf("%20d %20.2f %20.2f %20.2f %20.2f%n", month,
                    balance, irPaid, amountPaid, newBalance);

            balance = newBalance;
        }
    }
}