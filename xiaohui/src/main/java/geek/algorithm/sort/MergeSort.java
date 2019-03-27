package geek.algorithm.sort;

/**
 * 归并排序算法
 *
 * @author wzl
 */
public class MergeSort {

    public void mergeSort(int[] a, int n) {
        mergeSortInternally(a, 0, n - 1);
    }

    private void mergeSortInternally(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = (p + r) / 2;
        mergeSortInternally(a, p, q);
        mergeSortInternally(a, q + 1, r);
        merge(a, p, q, r);
    }

    private void merge(int[] a, int p, int q, int r) {

        int n1 = q - p + 1;
        // r-(q+1)+1
        int n2 = r - q;

        int[] left = new int[n1];
        int[] right = new int[n2];

        for (int i = 0; i < n1; i++) {
//            left[i] =
        }
    }
}
