import java.util.ArrayList;
import java.util.List;

public class Test2 {

    public static void main(String[] args) {
        List<Number> list = new ArrayList<>();
        list.add(12);
        long t = 12;
        if (list.contains(t)) {
            System.out.println("contains " + t);
        }
    }
}
