import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        //First Approach to populate student name and type

        Session session1 = new Session();    //Creating an instance of session
        //parsing a CSV file into the constructor of Scanner class
        Scanner sc = new Scanner(new File("Names.csv"));
        Student student;     //creating reference of Student type
        while (sc.hasNext()) {
            String [] studentDetail = sc.next().split(",");  //setting comma as delimiter pattern
            // Check if student is full-time or part-time and create object accordingly
            if(studentDetail[1].equalsIgnoreCase("FT")){
                student = new FullTimeStudent(studentDetail[0],studentDetail[1]);   //creating object of full-time student
            }else{
                student = new PartTimeStudent(studentDetail[0], studentDetail[1]);  //Creating object of part-time student

            }
            session1.addStudent(student);   //Adding students to a session
        }
        sc.close();   //closing the scanner

        //Call all methods of session

        System.out.println("Average quiz score of each students for a session:");
        System.out.println(Arrays.toString(session1.calculateAverageQuizScore()));
        System.out.println("****************************************************");

        System.out.println("List of quiz scores in ascending order:");
        session1.displayQuizScoreInAsc();
        System.out.println("*****************************************************");

        System.out.println("Names of part time students:");
        session1.displayPartTimeStudents();
        System.out.println("*****************************************************");

        System.out.println("Full time students with their exam scores:");
        session1.displayFullTimeStudentExamScore();
        System.out.println("*****************************************************");


        //Second Approach to populate student name and type

        /*Session session1 = new Session();
        for(int i=0; i<20; i++){
            if(i%2 != 0){
                FullTimeStudent fullTimeStudent = new FullTimeStudent("FullTimeStudent"+i, "FT");
                session1.addStudent(fullTimeStudent);
            }else{
                PartTimeStudent partTimeStudent = new PartTimeStudent("PartTimeStudent"+i, "PT");
                session1.addStudent(partTimeStudent);
            }
        }

        System.out.println(Arrays.toString(session1.calculateAverageQuizScore()));
        System.out.println();
        session1.displayQuizScoreInAsc();
        System.out.println();
        session1.displayPartTimeStudents();
        session1.displayFullTimeStudentExamScore();*/
    }
}