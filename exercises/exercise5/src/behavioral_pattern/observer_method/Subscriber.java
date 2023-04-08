package behavioral_pattern.observer_method;

public class Subscriber implements Observer{
    private String name;
    private Course course;

    Subscriber(String name){
        this.name = name;
    }
    /*
       Implementation of the Observer update method, which is
        called by the Subject when a new assignment is graded
     */
    @Override
    public void update(String assignmentTitle, String courseName) {
        System.out.println(this.name + "\s" + courseName +" Assignment graded: "
                + assignmentTitle + " is graded you can check your grade on canvas");
    }

    public void subscribedCourse(Course course){
        this.course = course;
    }
}
