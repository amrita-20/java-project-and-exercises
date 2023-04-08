package behavioral_pattern.observer_method;

public class Canvas {
    public static void main(String... args){
        //First course
        Course encp = new Course("ENCP");

        Subscriber sub1 = new Subscriber("Joseph");
        Subscriber sub2 = new Subscriber("Sam");
        Subscriber sub3 = new Subscriber("Amani");

        encp.subscribe(sub1);
        encp.subscribe(sub2);
        encp.subscribe(sub3);

        encp.unsubscribe(sub2);

        sub1.subscribedCourse(encp);
        sub2.subscribedCourse(encp);

        encp.addAssignmentGrade("Assignment-1");
        encp.addAssignmentGrade("Assignment-2");
        encp.addAssignmentGrade("Assignment-3");


        System.out.println("\n");

        //Second Course
        Course aed = new Course("AED");

        Subscriber subscriber1 = new Subscriber("Jingy");
        Subscriber subscriber2 = new Subscriber("Bob");

        aed.subscribe(subscriber1);
        aed.subscribe(subscriber2);

        subscriber1.subscribedCourse(aed);
        subscriber2.subscribedCourse(aed);

        aed.addAssignmentGrade("Assignment-1: File I/O");
        aed.addAssignmentGrade("Assignment-2: JavaFX");
        aed.addAssignmentGrade("Assignment-3: MultiThreading");
    }
}
