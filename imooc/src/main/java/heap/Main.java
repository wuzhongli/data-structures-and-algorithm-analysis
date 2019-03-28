package heap;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int n = 20;
//        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(20));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }

        System.out.println(Arrays.toString(arr));
        System.out.println("Test MaxHeap completed.");
    }
}
