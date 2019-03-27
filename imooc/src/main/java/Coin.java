/**
 * 硬币问题
 *
 * @author wzl
 */
public class Coin {

    public static void main(String[] args) {
        exhaustive();
        System.out.println("==================");
        better();
    }

    public static void exhaustive() {
        int count = 0;
        for (int a = 0; a <= 10; a++) {
            for (int b = 0; b <= 5; b++) {
                for (int c = 0; c <= 2; c++) {
                    count++;
                    if (1 * a + 2 * b + 5 * c == 10) {
                        System.out.println("1*" + a + " + 2*" + b + " + 5*" + c + "=" + 10);
                    }
                }
            }
        }
        System.out.println(count);
    }

    public static void better() {
        int count = 0;
        for (int x = 0; x <= 2; x++) {
            if (5 * x > 10) {
                break;
            }
            for (int y = 0; y <= 5; y++) {
                if (5 * x + 2 * y > 10) {
                    break;
                }
                for (int z = 0; z <= 10; z++) {
                    if (5 * x + 2 * y + z > 10) {
                        break;
                    }
                    count++;
                    if (5 * x + 2 * y + z == 10) {
                        System.out.println("5*" + x + " + 2*" + y + " + 1*" + z + "=" + 10);
                    }
                }
            }
        }
        System.out.println(count);
    }
}
