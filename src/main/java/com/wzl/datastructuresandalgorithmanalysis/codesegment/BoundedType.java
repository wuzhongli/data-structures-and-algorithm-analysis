package com.wzl.datastructuresandalgorithmanalysis.codesegment;

public class BoundedType {

    public static <T extends Comparable<? super T>> T findMax(T[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }
}
