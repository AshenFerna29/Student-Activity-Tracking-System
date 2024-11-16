// importing Scanner to get all the inputs
import java.util.Scanner;
public class Student {

    // Creating a method to add the student details
    public  void studentdetails() {
        Scanner input = new Scanner(System.in);//intializing the input

        if (Main.studentCount >= Main.maxAmount) {//Checking whether the maximum amount has reached or not
            System.out.println("The student  count is already full");
            return;
        }
        while (true) {
            System.out.println("Enter the ID of the Student (w1234567) : ");
            String Student_ID = input.nextLine().toLowerCase();// getting the user input
            Main.studentid[Main.studentCount] = Student_ID;//adding the input in to the array
            if (Main.duplicateid(Student_ID)) {//checking if theres a student id is already given or not
                System.out.println("This ID has  been mentioned already its a duplicate id please insert a new id ");
                continue;
            }


            if (Student_ID.length() == 8) {//checking length
                if (Student_ID.charAt(0) == 'w' && Student_ID.substring(1).matches("\\d{7}")) {//Checking the stings and inputs
                    break;

                } else {
                    System.out.println("Please enter in the correct format(w1234567) ");
                }
            } else {
                System.out.println("The length is invalid please input correct id ");
            }
        }


        while (true) {
            System.out.println("Enter the Student Name : ");
            String Student_name = input.nextLine().toLowerCase();

            Main.studentname[Main.studentCount] = Student_name;//Adding the Student name into the array

            if (Main.duplicatename(Student_name)) {//checking the duplicate names
                System.out.println("There is a student registered in this name please check again ");
                continue;}



            Main.studentCount++;// Increasing the student count
            System.out.println("The Student is added to the list successfully");
            break;
        }
    }



}