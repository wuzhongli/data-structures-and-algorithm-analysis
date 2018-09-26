package com.wzl.datastructuresandalgorithmanalysis.codesegment;

/**
 * 分治法求解最大子序列和
 */
public class DivideAndConquer {

    public static void main(String[] args) {
        int[] arrry = {4, -3, 5, -2, -1, 2, 6, -2};
        System.out.println(maxSubSum3(arrry));
    }

    public static int maxSubSum3(int[] a) {
        return maxSumRec(a, 0, a.length - 1);
    }

    private static int maxSumRec(int[] a, int left, int right) {

        // 处理基准情况 数组只有一个元素
        if (left == right) {
            if (a[left] > 0) {
                return a[left];
            }
            return 0;
        }
        int center = (left + right) / 2;

        return max3(
                maxSumRec(a, left, center),
                maxSumRec(a, center + 1, right),
                maxBorderSum(a, left, right, center));
    }

    private static int max3(int a, int b, int c) {
        int max = a;
        if (b > a) {
            max = b;
        }
        if (c > max) {
            return c;
        }
        return max;
    }

    private static int maxBorderSum(int[] a, int left, int right, int center) {
        // 左边界最大子序列和
        int maxLeftBorderSum = 0;
        int leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += a[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        // 右边界最大子序列和
        int maxRightBorderSum = 0;
        int rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += a[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }

        return maxLeftBorderSum + maxRightBorderSum;
    }
}
