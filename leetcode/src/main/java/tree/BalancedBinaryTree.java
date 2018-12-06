package tree;

/**
 * 110 平衡二叉树
 *
 * @author wzl
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return height(root) != -2;
    }

    /**
     * -1为空节点的高 -2表示子树不平衡
     */
    private int height(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = height(root.left);
        if (leftHeight == -2) {
            return leftHeight;
        }
        int rightHeight = height(root.right);
        if (rightHeight == -2) {
            return rightHeight;
        }

        if (leftHeight - rightHeight > 1 || rightHeight - leftHeight > 1) {
            return -2;
        }

        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }


    public static void main(String[] args) {
        BalancedBinaryTree binaryTree = new BalancedBinaryTree();
        int[] arr = {3, 9, 20, -1, -1, 15, 7};

        int[] array = {1, 2, 2, 3, 3, -1, -1, 4, 4};

        System.out.println(
                binaryTree.isBalanced(TreeNode.buildBinaryTreeByArray(arr, 0, arr.length)));
        System.out.println(
                binaryTree.isBalanced(TreeNode.buildBinaryTreeByArray(array, 0, array.length)));
        System.out
                .println(binaryTree.isBalanced(TreeNode.buildBinaryTreeByArray(new int[0], 0, 0)));

        System.out.println(
                binaryTree.isBalanced(TreeNode.stringToBinaryNode("[1,2,2,3,3,null,null,4,4]")));
    }
}
