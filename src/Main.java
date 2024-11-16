//importing Scanner and file options
import java.io.*;
import java.util.*;

//creating the main method
public class Main {
    //Creating String Arrays and the MaxAmount for the string
    static final int maxAmount = 100;
    static String[] studentid = new String[maxAmount];
    static String[] studentname = new String[maxAmount];
    static int studentCount = 0;
    static Map<String, Module> studentModules = new HashMap<>();
    static double[] StudentAverage = new double[maxAmount];

    // Creating the Main method
    public static void main(String[] args) {

        //initializing the Inputs
        Scanner input = new Scanner(System.in);
        int UserChoice;// initializing the user input as integer

        while (true) { //creating a loop


            menu();// Calling the menumethod
            if (input.hasNextInt()) {//Checking the input whether its a integer
                UserChoice = input.nextInt();//getting  the user input
                input.nextLine();
                switch (UserChoice) { //creating the options


                    case 1:
                        availableSeats(); //calling method
                        break;
                    case 2:
                        studentRegister(input); //calling method
                        break;
                    case 3:
                        deleteStudent(input); //calling method
                        break;
                    case 4:
                        findStudent(input); //calling method
                        break;

                    case 5:
                        creatingfile(); //calling method
                        break;
                    case 6:
                        loadStudentDataFromFile(); //calling method
                        break;
                    case 7:
                        SortStudentByAlpha(); //calling method
                        break;
                    case 8:
                        Additionaloptins(input); //calling method
                        break;

                    case 9:
                        System.out.println("Thank you for using 'SAMS'..... \nExiting the program...........");
                        input.close();
                        System.exit(0); // Exiting the program



                    default:
                        System.out.println("Invalid Choice Please Input the correct number in the menu");// creating a default method for incirrect inputs from the user

                }
            } else {
                System.out.println("Invalid Input Enter ");
                input.nextLine();
            }

        }
    }

    // Creating the menu method
    public static void menu() {
        // Printing the main menu for the user
        System.out.println("\n*****  STUDENT ACTIVITY MANAGEMENT SYSTEM(SAMS) *****");
        System.out.println("\n1. Check Available Seats. ");
        System.out.println("2. Register Students (With ID). ");
        System.out.println("3. Delete Student. ");
        System.out.println("4. Find Student (With Student ID ). ");
        System.out.println("5. Save Student details in to file. ");
        System.out.println("6. Load Student data from the file. ");
        System.out.println("7. Sort the student by Alphabetical order.");
        System.out.println("8. Additional optins ");
        System.out.println("9. Exit the program.");
        System.out.print("\nEnter your choice. ");


    }

    // Printing the additional option menu method
    public static void Additionaloptins(Scanner input){
        while(true) {
            // printing the additional option menu
            System.out.println("a. Add Student name.");
            System.out.println("b. Module marks and the Rank. ");
            System.out.println("c. Summary of the Students.");
            System.out.println("d. Generate a report.");
            System.out.println("q. Exit to main menu.");
            System.out.println("Enter your choice : ");
            String Choice = input.next().toLowerCase();// getting the user input

            switch (Choice) { // additional options
                case "a":
                    studentRegister(input);//calling method

                    break;
                case "b":
                    importmodule(input);//calling method
                    break;
                case "c":
                    SummaryOfStudentDetails(input);//calling method
                    break;
                case "d":
                    ProgressReportOfStudents();//calling method
                    break;
                case "q":
                    return;// Returning to the main menu
                default:
                    System.out.println("Invalid Input please add a,b,c,d or q ......");// creating a default method for incirrect inputs from the user
                    break;

            }
        }




    }
    // Creating a method to register students
    public static void studentRegister(Scanner input) {
        Student studentpackage  = new Student(); // Creating a constructor
        studentpackage.studentdetails();// Callling the class using the constructor





    }
    // creating a method to find ids that already exist
    static boolean duplicateid(String Student_ID) {

        for (int i = 0; i < studentCount; i++) {// Creating loop
            if (studentid[i].equals(Student_ID)) {

                return true;
            }

        }
        return false;


    }
    // Creating a method to find names that already exist
    static boolean duplicatename(String Student_name) {
        for (int i = 0; i < studentCount; i++) { //Creating a loop
            if (studentname[i].equals(Student_name)) {//Checking whether student names are equal
                return true;
            }
        }
        return false;
    }

    // Creating a method to check the available seats after registering students
    public static void availableSeats() {
        int availabel_seats = maxAmount - studentCount;
        System.out.println("Available Seats are : " + availabel_seats);

    }

    // Creating a method to delete students
    public static void deleteStudent(Scanner input) {

        //checking whether the students are registered or not
        if (studentCount == 0) {
            System.out.println("There are no filled seats with students to delete please add a student and  the proceed ");
            return;
        }

        System.out.println("Enter the id of the student which you want to delete : ");
        String dlt_student = input.nextLine();


        boolean id_found = false;

        for (int i = 0; i < studentCount; i++) {//Creating a loop
            if (studentid[i].equals(dlt_student)) {
                id_found = true;
                for (int j = i; j < studentCount - 1; j++) {//Creating a loop
                    // Sending the student ids andd the names left
                    studentid[j] = studentid[j + 1];
                    studentname[j] = studentname[j + 1];
                }


                studentid[studentCount - 1] = null;
                studentname[studentCount - 1] = null;
                studentCount--; //reducing the student count
            }
        }
        if (id_found) {
            System.out.println("The Selected id which is " + dlt_student + "is Successfully deleted .....");
        } else {
            System.out.println("Invalid id The System could not find such id ! ");
        }


    }
    //Creating a method to get the module marks from the module class
    public static void importmodule(Scanner input) {
        if (studentCount == 0) {// checking is the students are registerd.
            System.out.println("There are no students registered yet. Please register students first.");
            return;
        }

        System.out.println("Enter the ID of the Student : ");
        String sID = input.next().toLowerCase(); // Read the student ID and convert to lowercase

        boolean found = false;
        for (int i = 0; i < studentCount; i++) {//Creating a loop
            if (studentid[i].equals(sID)) { // Check if the entered ID matches any registered student ID
                found = true;

                System.out.println("Enter module details for student " + studentname[i]);

                Module packageModule = new Module();// Creating a Constructor
                packageModule.inputofmodule(input); // Calling the method using the constructor

                studentModules.put(sID, packageModule);
                StudentAverage[i] = packageModule.getAverage();

                // Append module details to the file
                try (PrintWriter writer = new PrintWriter(new FileWriter("students.txt", true))) {
                    writer.print(studentid[i] + "-" + studentname[i]);
                    for (int j = 0; j < packageModule.getModuleNames().size(); j++) {//Creating a loop
                        writer.print("- " + packageModule.getModuleNames().get(j) + ": " + packageModule.getMarks().get(j));//printing the marsk in the file
                    }
                    writer.println(" - Average: " + packageModule.getAverage());
                    System.out.println("Successfully added the marks of the student.");
                } catch (IOException e) {
                    System.out.println("An error occurred while saving data to file.");
                }

                break; // Exit loop once student is found and modules are processed
            }
        }

        if (!found) {
            System.out.println("Student ID not found. Please register the student first.");
        }
    }



    //creating a method to create a file
    public static void creatingfile() {
        if (studentCount==0){// checking the student count
            System.out.println("There are no students registerd to store to the file please register students first.");
        }
        else {
            try (PrintWriter writer = new PrintWriter(new FileWriter("students.txt"))) {//Creating a file to store data


                for (int i = 0; i < studentCount; i++) {//Creating a loop

                    writer.println(studentid[i] + "-" + studentname[i]);//printing the nmaes and the ids in the file
                }
                System.out.println("Data saved to file successfully.");


            } catch (IOException e) {// Error handling
                System.out.println("An error occurred while saving data to file.");
            }
        }
    }
    //Creating a method to load all  the student data form the file
    public static void loadStudentDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");// spliting the text
                if (parts.length == 2) {

                    String id = parts[0].trim();//triming the white space
                    String name = parts[1].trim();
                    if(!duplicateid(id)) {
                        studentid[studentCount] = id;// intializing the variables
                        studentname[studentCount] =name;
                        studentCount++;}// increasing the count
                }
            }
            System.out.println("Data loaded from file successfully.");
        } catch (FileNotFoundException e) {// exception handling
            System.out.println("Data file not found. Starting with an empty list.");
        } catch (IOException e) {// exception handling
            System.out.println("An error occurred while loading data from file.");
        }


    }
    // Creating a method to find the students
    public static void findStudent(Scanner input ) {
        if (studentCount==0){// Checking the student count
            System.out.println("There are no students registerd yet please register the student first");
            return;}
        System.out.println("Enter the ID of the student to find : ");
        String idfind = input.next();



        boolean found1 = false;

        for (int i = 0; i < studentCount; i++) {//Creating a loop
            // Searching the student according to the input
            if (studentid [i].equals(idfind)) {
                found1 = true;
                System.out.println("Student name is : "+studentname[i]);// giving the output
                break;
            }


        }

        if(!found1){
            System.out.println("There is no Such ID...");//Error handling
        }
    }


    //Creating a method to sort the student by alphabetical order
    public static void SortStudentByAlpha(){
        if (studentCount==0){ // Checking the student count
            System.out.println("There are no students Please Register Students first.....");}

       // Sorting the Students by using BUBBLE SORT method

        for (int i = 0;i<studentCount-1;i++) {//Creating a loop
            for (int j = 0; j < studentCount - i; j++) {//Creating a loop
                if (studentname[j]== null || (studentname[j + 1] )== null) {
                    continue;}
                if ((studentname[j].compareTo(studentname[j + 1]) > 0) ){
                    String TemporaryString = studentname[j];// Creating temporry string
                    studentname[j] = studentname[j+1];
                    studentname[j] = studentname[j+1];
                    studentname[j+1] = TemporaryString;



                }
            }
        }
        System.out.println("Students are sorted according to the alphebetical order");
        for (int i = 0; i <studentCount;i++){// creating a loop to get the output as alphabetical order
            System.out.println(studentid[i]+ "-"+studentname[i]);

        }


    }

    // Creating a method to get the Summary of the Students Details
    public static void SummaryOfStudentDetails(Scanner input) {

        System.out.println("Total number of students registered: " + studentCount);
        if (studentCount==0){//Checkong the student count
            System.out.println("There are no students to get the summary....");
        }
        else {

            int[] above40Counts = Module.getAbove40Counts();// getting the students who have got above 40

            System.out.println("Number of students who scored more than 40 in each module:");
            for (int i = 0; i < above40Counts.length; i++) {// Creating a loop to get the studetns who have gpt above for the each modulen
                System.out.println("Module " + (i + 1) + ": " + above40Counts[i] + " students");// Printing the output
            }
        }
    }

    // Creating a method to get the report of each student who have registered
    public static void ProgressReportOfStudents() {
        for (int i = 0; i < studentCount; i++) {//Creating a loop
            String studentId = studentid[i];
            System.out.println("Student ID: " + studentId);
            System.out.println("Student Name: " + studentname[i]);

            Module studentModule = studentModules.get(studentId);//getting the module marks acccording to their ids
            if (studentModule != null) {
                List<Double> marks = studentModule.getMarks();
                double total = 0;
                for (int j = 0; j < marks.size(); j++) {
                    double mark = marks.get(j);
                    System.out.println("Module " + (j + 1) + " marks: " + mark);
                    total += mark;
                }

                System.out.println("Total: " + total);//printing the total mrks

                double average = studentModule.getAverage();
                System.out.println("Average: " + average); //getting the average

                System.out.print("Grade: ");
                studentModule.rank();//Getting the rank
            } else {
                System.out.println("No module information available for this student.");
            }

            System.out.println(); // Empty line for separation
        }
        if (studentCount==0){
            System.out.println("There are no students registered to sort the marks .");
        }
        else {
            // Sorting by average marks in descending order
            String[] tempIds = Arrays.copyOf(studentid, studentCount);
            String[] tempNames = Arrays.copyOf(studentname, studentCount);
            double[] tempAverages = Arrays.copyOf(StudentAverage, studentCount);

            for (int i = 0; i < studentCount - 1; i++) {//Creating a loop
                for (int j = 0; j < studentCount - i - 1; j++) {//Creating a loop
                    if (tempAverages[j] < tempAverages[j + 1]) {
                        // Swap averages
                        double tempAvg = tempAverages[j];
                        tempAverages[j] = tempAverages[j + 1];
                        tempAverages[j + 1] = tempAvg;

                        // Swap IDs
                        String tempId = tempIds[j];
                        tempIds[j] = tempIds[j + 1];
                        tempIds[j + 1] = tempId;

                        // Swap names
                        String tempName = tempNames[j];
                        tempNames[j] = tempNames[j + 1];
                        tempNames[j + 1] = tempName;
                    }
                }
            }

            // Display sorted results
            System.out.println("Students sorted by average marks (highest to lowest):");
            for (int i = 0; i < studentCount; i++) {
                System.out.println(tempIds[i] + " - " + tempNames[i] + " : " + tempAverages[i]);
            }
        }
    }

}





