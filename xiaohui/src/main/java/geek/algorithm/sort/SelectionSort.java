package geek.algorithm.sort;

/**
 * 选择排序算法
 *
 * @author wzl
 */
public class SelectionSort {

    /**
     * 选择排序将数组分为已排序区间和未排序区间，每次从未排序区间找到最小的元素，插入到已排序区间末尾（采用和未排序区间第一个元素交换的方式）
     */
    public static void selectionSort(int a[], int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n - 1; i++) {
            // 查找未排序区间最小值
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            // 交换
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }
    }
}
