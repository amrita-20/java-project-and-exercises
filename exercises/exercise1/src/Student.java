import javax.swing.*;

public class Student {
    private int []quizScores = new int[15];
    protected String name;
    private String type;

    /**
     * Constructor for Student class.
     *
     * @param name the name of the student
     * @param type the type of the student, either "FT" for full-time or "PT" for part-time
     */
    public Student(String name, String type){
        this.name = name;
        this.type = type;
        //Add dummy quiz scores using random method
        for(int j=0; j<quizScores.length; j++){
            quizScores[j] = ((int) (Math.random()*100+1));
        }
    }

    public String getType() {
        return type;
    }

    public int[] getQuizScores() {
        return quizScores;
    }

    public String getName() {
        return name;
    }
}
