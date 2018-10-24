package com.wzl.datastructuresandalgorithmanalysis.imooc.linkedlist.linked_list_problems_in_leetcode;

public class Sum {

    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    private static int sum(int[] arr, int left) {
        if (left == arr.length - 1) {
            return arr[left];
        }
        return arr[left] + sum(arr, left + 1);
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(sum(array));
    }
}
