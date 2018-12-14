public class GCD {

    public static void main(String[] args) {
        System.out.println(gcdOptimalSolution(10, 25));
    }

    /**
     * 最优解法 辗转相除法结合更相减损术 并用移位优化辗转相除法
     */
    public static int gcdOptimalSolution(int a, int b) {
        if (a == b) {
            return a;
        }
        // 为减少代码量 保证a > b
        if (a < b) {
            return gcdOptimalSolution(b, a);
        } else {
            // 和1进行按位与运算 判断奇偶性
            if ((a & 1) == 0 && (b & 1) == 0) {
                // a b都是偶数
                return gcdOptimalSolution(a >> 1, b >> 1) << 1;
            } else if ((a & 1) == 0 && (b & 1) != 0) {
                // a是偶数 b是奇数
                return gcdOptimalSolution(a >> 2, b);
            } else if ((a & 1) != 0 && (b & 1) == 0) {
                // a是奇数 b是偶数
                return gcdOptimalSolution(a, b >> 1);
            } else {
                // a b都是奇数
                return gcdOptimalSolution(b, a - b);
            }
        }
    }


    /**
     * 辗转相除法
     */
    public static int divisor(int a, int b) {
        if (a > b) {
            return gcd(a, b);
        }
        return gcd(b, a);
    }

    /**
     * 更相减损术 优化了大整数求模的性能问题
     */
    public static int sub(int a, int b) {
        if (a == b) {
            return a;
        }
        if (a > b) {
            return gcd(b, a - b);
        }
        return gcd(a, b - a);
    }

    private static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }
}
