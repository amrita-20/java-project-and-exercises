import java.util.ArrayList;
import java.util.Arrays;

public class Session {
    // Create an array of Student objects to hold up to 20 students
   private Student[] students = new Student[20];
    // Keep track of how many students have been added so far
   private int count = 0;

   // Method for adding a Student object to the array
    public void addStudent(Student student){
        if(count<students.length){
            students[count++] = student;
        }else{
            System.out.println("This session is already full");
        }
    }

    //Method for calculating the average quiz score for each student in the session
    public float[] calculateAverageQuizScore(){
        float [] averageScore = new float[20];
        for (int i=0; i<students.length; i++) {
            int totalQuizScore = 0;
            for(int score : students[i].getQuizScores()){
                totalQuizScore += score;
            }
            averageScore[i] = totalQuizScore/ students[i].getQuizScores().length;
        }
        return averageScore;
    }

    // Method for displaying quiz scores in ascending order
    public void displayQuizScoreInAsc(){
        int [][] quizList = new int[15][20];
        for(int i = 0; i < 20; i++){
            int[] score = students[i].getQuizScores();
            for(int j = 0 ;j <15; j++){
                quizList[j][i] = score[j];
            }
        }
       for(int[] quiz : quizList){
           Arrays.sort(quiz);
           System.out.println(Arrays.toString(quiz));
       }
    }

    // Method for displaying the names of part-time students
    public void displayPartTimeStudents(){
        for (int i = 0; i < 20; i++) {
            if (students[i].getType().equalsIgnoreCase("PT")) {
                System.out.println(students[i].getName());
            }
        }
    }

    // Method for displaying the exam scores of full-time students
    public void displayFullTimeStudentExamScore(){
        for (int i = 0; i < 20; i++) {
            if (students[i].getType().equalsIgnoreCase("FT")) {
                System.out.println(students[i].getName() + ": " + Arrays.toString(((FullTimeStudent)students[i]).getExamScores()));
            }
        }
    }
}
