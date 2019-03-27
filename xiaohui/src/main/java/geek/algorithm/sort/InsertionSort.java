package geek.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序算法
 *
 * @author wzl
 */
public class InsertionSort {

    /**
     * 插入排序将数组分为已排序区间和未排序区间，初始已排序区间只有一个元素，就是数组第一个元素。算法核心思想是从左到右取未排序区间的元素，在排序区间找到合适的位置进行插入（中间可能会移动数组元素），
     * 并保证已排序区间一直有序，重复这个过程，直到未排序区间为空
     */
    public static void insertionSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        int compare = 0;
        int move = 0;

        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i - 1;

            // 从尾到头移动元素
            for (; j >= 0; j--) {
                compare++;
                if (a[j] > value) {
                    // 移动数据
                    a[j + 1] = a[j];
                    move++;
                } else {
                    break;
                }
            }

            // 插入数据
            a[j + 1] = value;
        }

        System.out.println("比较次数: " + compare);
        System.out.println("移动次数: " + move);
    }

    public static void main(String[] args) {
        int[] array = {4, 5, 6, 3, 2, 1};
        int[] ordered = {1, 2, 3, 4, 5, 6};
        int[] reverse = {6, 5, 4, 3, 2, 1};

        System.out.println("一般数组=======");
        insertionSort(array, array.length);
        System.out.println(Arrays.toString(array));
        System.out.println();

        System.out.println("有序数组=======");
        insertionSort(ordered, ordered.length);
        System.out.println(Arrays.toString(ordered));
        System.out.println();

        System.out.println("逆序数组=======");
        insertionSort(reverse, reverse.length);
        System.out.println(Arrays.toString(reverse));
    }
}
