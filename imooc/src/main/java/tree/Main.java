package tree;

public class Main {

    public static void main(String[] args) {

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }

        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        bst.levelOrder();
        bst.removeMin();
        bst.removeMax();
        bst.levelOrder();
//        bst.preOrder();
//        System.out.println();
//
//        bst.preOrderNonRecursive();
//        bst.inOrder();
//        System.out.println();
//
//        bst.postOrder();
//        System.out.println();
    }
}
