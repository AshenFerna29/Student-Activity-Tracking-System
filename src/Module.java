//importing

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Module {

    // Creating methods
    private List<String> moduleNames = new ArrayList<>();
    private List<Double> marks = new ArrayList<>();
    private double average;
    private static int[] above40Counts = new int[3]; // Assuming there are 3 modules

    // Creating a method  to get the input
    public void inputofmodule(Scanner input) {
        double total = 0;
        for (int j = 0; j < 3; j++) {
            String moduleName = "Module " + (j + 1);
            System.out.println("Enter the marks for " + moduleName + ": ");
            double mark = -1;
            boolean validInput = false;

            while (!validInput) {
                if (input.hasNextDouble()) {
                    mark = input.nextDouble();
                    input.nextLine(); // Consume newline

                    if (mark >= 0 && mark <= 100) {
                        validInput = true;
                    } else {
                        System.out.println("You can only add marks in the range 0 to 100. Please try again:");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a numeric value between 0 and 100:");
                    input.next(); // Consume invalid input
                }
            }

            moduleNames.add(moduleName);//Adding the names in to the arrays
            marks.add(mark);
            total += mark;//increasing the marks

            // Update count if marks are above 40
            if (mark > 40) {
                above40Counts[j]++;
            }
        }
        average = total / 3;//getting the average
        System.out.println("Total average: " + average);
        rank();//calling the rank method
    }
    // getting a maethod to module names
    public List<String> getModuleNames() {
        return moduleNames;
    }
    //getting the marks in a method
    public List<Double> getMarks() {
        return marks;
    }
    //Getting the average
    public double getAverage() {
        return average;
    }

    //Creating a method to get the rank
    public void rank() {
        if (average >= 80) {
            System.out.println("DISTINCTION PASS!");
        } else if (average >= 70) {
            System.out.println("MERIT PASS!");
        } else if (average >= 40) {
            System.out.println("PASS!");
        } else {
            System.out.println("FAIL!");
        }
    }
    //method to get the 40 above count
    public static int[] getAbove40Counts() {
        return above40Counts;
    }
}
