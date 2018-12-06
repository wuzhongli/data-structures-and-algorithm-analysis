package codesegment;

import java.util.Arrays;

/**
 * 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        Integer[] array = {4, -3, 5, -2, -1, 2, 6, -2};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        System.out.println(binarySearch(array, -1));
    }

    public static <T extends Comparable<? super T>> int binarySearch(T[] a, T t) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (a[mid].compareTo(t) < 0) {
                low = mid + 1;
            } else if (a[mid].compareTo(t) > 0) {
                high = mid - 1;
            } else {
                // Found
                return mid;
            }
        }
        // Not Found
        return -1;
    }
}
