import java.util.Scanner;

public class FF {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int T = 0;
            double totalSum = 0; // sum
            double totaln = 0; // n
            double SSA = 0; // SSA
            double SA = 0; // SA - CT = SST

            while (true) {
                System.out.println("Enter 'X' for X-bar, 'N' for Nomal, 'A' for Anova, or 'exit' to finish:");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                if (input.equalsIgnoreCase("N")) {
                    System.out.println("Please enter the calculation (e.g., 1 + 5 * 3 / 2):"); 
                    String calculation = scanner.nextLine();
                    String[] tokens = calculation.trim().split("\\s+");

                    double result = Double.parseDouble(tokens[0]);
                    for (int i = 1; i < tokens.length - 1; i += 2) {
                        double num = Double.parseDouble(tokens[i + 1]);
                        switch (tokens[i]) {
                            case "+":
                                result += num;
                                break;
                            case "-":
                                result -= num;
                                break;
                            case "*":
                                result *= num;
                                break;
                            case "/":
                                result /= num;
                                break;
                            default:
                                System.out.println("Invalid operator.");
                                break;
                        }
                    }
                    System.out.println("Result: " + result);

                } else if (input.equalsIgnoreCase("X")) {
                    System.out.println("Enter numbers (e.g., 1 8 6):");
                    String[] numbers = scanner.nextLine().trim().split("\\s+");
                    double sum = 0;
                    int n = numbers.length;
                    for (String number : numbers) {
                        try {
                            double num = Double.parseDouble(number);
                            sum += num;
                        } catch (NumberFormatException e) {
                            System.out.println("Error");
                            sum = 0;
                            n = 0;
                            break;
                        }
                    }
                    double mean = sum / n;
                    System.out.println("Sum: " + sum);
                    System.out.println("n = " + n);
                    System.out.println("Mean (X-bar): " + mean);

                } else if (input.equalsIgnoreCase("A")) {
                    double sum = 0;
                    double n = 0;
                    while (true) {
                        System.out.println("Enter numbers (e.g., 1 2 3) or 'stop' to finish Ti");
                        String[] numbers = scanner.nextLine().trim().split("\\s+");

                        if (numbers[0].equalsIgnoreCase("stop")) {
                            break;
                        } else {
                            for (String number : numbers) {
                                try {
                                    double num = Double.parseDouble(number);
                                    sum += num;
                                    totalSum += num; // sum
                                    n++; // n
                                    totaln++; // n
                                    SA += Math.pow(num, 2); // SSA
                                } catch (NumberFormatException e) {
                                    System.out.println("Error");
                                    sum = 0;
                                    n = 0;
                                    break;
                                }
                            }
                            double mean = sum / n;
                            System.out.println("Sum for T: " + sum);
                            System.out.println("n for T = " + n);
                            T++;
                            SSA += Math.pow(sum, 2) / n; // SSA
                        }
                        sum = 0; // sum
                        n = 0; // n
                    }
                    double CT = (totalSum * totalSum) / totaln; // CT
                    double newSSA = SA - CT; // SSA
                    double SST = SA - CT; // SST
                    double SSW = SST - (SSA - (totalSum * totalSum / totaln));
                    double MSA = (SSA - (totalSum * totalSum / totaln)) / (T - 1);
                    System.out.printf("Total n: %.3f%n", totaln);
                    System.out.println("k: " + T);
                    System.out.println("k-1: " + (T - 1));
                    System.out.printf("n-k: %.3f%n", (totaln - T));
                    System.out.printf("CT: %.3f%n", CT);
                    SA += Math.pow(sum, 2) / n; //
                    System.out.printf("SSA: %.3f%n", SSA - (totalSum * totalSum / totaln));
                    System.out.printf("SST: %.3f%n", SST);
                    System.out.printf("SSW: %.3f%n", SST - (SSA - (totalSum * totalSum / totaln)));
                    System.out.printf("MSA: %.3f%n", (SSA - (totalSum * totalSum / totaln)) / (T - 1));
                    System.out.printf("MSW: %.3f%n", SSW / (totaln - T));
                    System.out.printf("F: %.3f%n", MSA / (SSW / (totaln - 1)));
                } else {
                    System.out.println("Invalid input. Please enter 'X', 'N', 'A', or 'exit'.");
                }
            }
        }
    }
}
