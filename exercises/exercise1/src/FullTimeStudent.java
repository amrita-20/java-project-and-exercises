public class FullTimeStudent extends Student{
    int[] examScores = new int[2];

    /**
     Constructor for FullTimeStudent class.
     @param name the name of the full-time student
     @param type the type of the student, in this case "FT" for full-time
     */
    public FullTimeStudent(String name, String type){
        super(name, type);
        //Add dummy exam scores using random method
        for(int k =0; k<examScores.length; k++){
            examScores[k] = ((int) (Math.random()*100+1));
        }
    }

    public int[] getExamScores() {
        return this.examScores;
    }
}
