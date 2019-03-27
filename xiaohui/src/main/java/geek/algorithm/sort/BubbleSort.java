package geek.algorithm.sort;

/**
 * 冒泡排序算法
 *
 * @author wzl
 */
public class BubbleSort {

    /**
     * 冒泡排序
     *
     * @param a 数组
     * @param n 数组大小
     */
    public static void bubbleSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        int compare = 0;
        int swap = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swap++;
                }
                compare++;
            }
        }
        System.out.println("普通冒泡：");
        System.out.println("比较次数: " + compare);
        System.out.println("交换次数: " + swap);
    }

    /**
     * 优化冒泡排序
     *
     * @param a 数组
     * @param n 数组大小
     */
    public static void batterBubbleSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        boolean flag = false;

        int swap = 0;
        int compare = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swap++;
                    flag = true;
                }
                compare++;
            }

            if (!flag) {
                break;
            }
        }
        System.out.println("优化后的冒泡：");
        System.out.println("比较次数: " + compare);
        System.out.println("交换次数: " + swap);
    }


    public static void main(String[] args) {
        int[] array = {4, 5, 6, 3, 2, 1};
        int[] arrayCopy = {4, 5, 6, 3, 2, 1};
        int[] ordered = {1, 2, 3, 4, 5, 6};
        int[] orderedCopy = {1, 2, 3, 4, 5, 6};
        int[] reverse = {6, 5, 4, 3, 2, 1};
        int[] reverseCopy = {6, 5, 4, 3, 2, 1};

        System.out.println("一般数组=======");
        bubbleSort(array, array.length);
        batterBubbleSort(arrayCopy, arrayCopy.length);
        System.out.println();

        System.out.println("有序数组=======");
        bubbleSort(ordered, ordered.length);
        batterBubbleSort(orderedCopy, orderedCopy.length);
        System.out.println();

        System.out.println("逆序数组=======");
        bubbleSort(reverse, reverse.length);
        batterBubbleSort(reverseCopy, reverseCopy.length);
    }
}
