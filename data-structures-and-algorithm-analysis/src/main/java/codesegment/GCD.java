package codesegment;

public class GCD {

    public static void main(String[] args) {
        System.out.println(gcd(10000, 50000000));
    }

    public static int gcd(int m, int n) {
        while (n != 0) {
            int rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }
}
