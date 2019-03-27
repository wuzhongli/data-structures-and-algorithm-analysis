import java.math.BigDecimal;

public class BigDecimalTest {

    public static void main(String[] args) {
        System.out.println(1.01 + 2.02);
        System.out.println(add(1.01, 2.02));

        int[] array = new int[520];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }


    public static double add(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.add(b2).doubleValue();
    }
}

