import com.oop.act.Scientist;
import com.oop.act.Secretary;
import com.oop.shapes.Shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class App {

    public static void main(String[] args) throws IOException {
        ArrayList<Shape> shapes = Secretary.getInstance().getListOfShapes();
        Scientist.getInstance().calculateParameters(shapes);
    }

}
