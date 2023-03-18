import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try{
            List<Shape> shapes = new ArrayList<>();
            shapes.add(new Rectangle(10,9));
            shapes.add(new Circle(26.5));
            shapes.add(new Square(1000));
            shapes.add(new Triangle(4,5,3,8));

            // Create a new FileOutputStream object named fout and specify the file "shapes.txt" to write to
            FileOutputStream fout = new FileOutputStream("shapes.txt");
            //Create a new ObjectOutputStream object to write Java objects to the FileOutputStream object,
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(shapes);
            oos.flush();
            oos.close();
            fout.close();
            System.out.println("written successfully" + "\n");

        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            ArrayList<Shape> shapesList;
            // Create a new FileInputStream object named fin and specify the file "shapes.txt" to read from
            FileInputStream fin = new FileInputStream("shapes.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            shapesList = (ArrayList<Shape>) ois.readObject();
            ois.close();
            fin.close();
            for(Shape sh :shapesList){
                System.out.println(sh.getShapeName());
                sh.draw();
                System.out.println("Perimeter of " + sh.getShapeName() + " = " + sh.calculatePerimeter());
                System.out.println("Area of " + sh.getShapeName() + " = " + sh.calculateArea() + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}