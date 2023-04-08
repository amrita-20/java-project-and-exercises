package behavioral_pattern.observer_method;

import java.util.ArrayList;

public class Course implements Subject{

    ArrayList <Subscriber> subscribers = new ArrayList<>();
    private String assignmentTitle;
    private String courseName;

    Course(String courseName) {
        this.courseName = courseName;
    }

    /*
       Implementation of the Subject subscribe method,
       which adds a subscriber to the list of subscribers
     */
    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    /*
      Implementation of the Subject unsubscribe method,
      which removes a subscriber from the list of subscribers
     */
    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifyObserver() {
        for(Subscriber sub : subscribers){
            sub.update(this.assignmentTitle, this.courseName);
        }
    }

    /*
        Implementation of the Subject notifyObserver method,
        which notifies all subscribers when a new assignment is graded
     */
    void addAssignmentGrade(String title){
        this.assignmentTitle = title;
        notifyObserver();
    }
}
