package com.wzl.datastructuresandalgorithmanalysis.codesegment;

import java.io.Serializable;
import java.util.Comparator;

public class FunctionObject {

    public static void main(String[] args) {
        String[] arr = {"ZEBRA", "alligator", "crocodile"};
        System.out.println(findMax(arr, new CaseInsensitiveComparator()));
    }

    public static <T> T findMax(T[] arr, Comparator<? super T> comparator) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (comparator.compare(arr[i], (arr[maxIndex])) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    static class CaseInsensitiveComparator implements Serializable, Comparator<String> {

        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
        }
    }
}
