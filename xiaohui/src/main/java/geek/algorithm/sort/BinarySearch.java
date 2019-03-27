package geek.algorithm.sort;

/**
 * 二分查找算法
 *
 * @author wzl
 */
public class BinarySearch {

    /**
     * 二分查找
     */
    public static int binarySearch(int[] a, int n, int value) {

        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    /**
     * 二分查找 查找第一个值等于给定值的元素
     */
    public static int binarySearchFirstEqual(int[] a, int n, int value) {

        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else if (mid == 0 || a[mid - 1] != value) {
                return mid;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 二分查找 查找最后一个值等于给定值的元素
     */
    public static int binarySearchLastEqual(int[] a, int n, int value) {

        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else if (mid == n - 1 || a[mid + 1] != value) {
                return mid;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    /**
     * 二分查找 查找第一个大于等于给定值的元素
     */
    public static int binarySearchFirstGreaterOrEqual(int[] a, int n, int value) {

        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if (mid == 0 || a[mid - 1] < value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    /**
     * 二分查找 查找最后一个小于等于给定值的元素
     */
    public static int binarySearchLastLessThanOrEqual(int[] a, int n, int value) {

        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == n - 1 || a[mid + 1] > value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] a1 = {1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
        int[] a2 = {1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
        int[] a3 = {3, 4, 6, 7, 10};
        int[] a4 = {3, 5, 6, 8, 9, 10};

        System.out.println("a1中第一个等于8的下标是：" + binarySearchFirstEqual(a1, a1.length, 8));
        System.out.println("a2中最后一个等于8的下标是：" + binarySearchLastEqual(a2, a2.length, 8));
        System.out.println("a3中第一个大于等于5的下标是：" + binarySearchFirstGreaterOrEqual(a3, a3.length, 5));
        System.out.println("a4中最后一个小于等于7的下标是：" + binarySearchLastLessThanOrEqual(a4, a4.length, 7));

        System.out.println(Integer.MAX_VALUE);
        System.out.println((Integer.MAX_VALUE + 1) / 2);
    }
}
