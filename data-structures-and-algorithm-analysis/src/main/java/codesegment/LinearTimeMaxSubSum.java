package codesegment;

/**
 * 线性时间求最大子序列和
 */
public class LinearTimeMaxSubSum {

    public static void main(String[] args) {
        int[] arrry = {4, -3, 5, -2, -1, 2, 6, -2};
        System.out.println(maxSubSum4(arrry));
    }

    public static int maxSubSum4(int[] a) {
        int maxSum = 0;
        int thisSum = 0;

        for (int i = 0; i < a.length; i++) {
            thisSum += a[i];
            if (thisSum > maxSum) {
                maxSum = thisSum;
            }
            if (thisSum < 0) {
                thisSum = 0;
            }
        }
        return maxSum;
    }
}
