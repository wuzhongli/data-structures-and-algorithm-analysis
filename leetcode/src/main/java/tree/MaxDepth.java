package tree;

/**
 * 104 二叉树的最大深度
 *
 * @author wzl
 */
public class MaxDepth {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.stringToBinaryNode("[3,9,20,null,null,15,7]");
        System.out.println(new MaxDepth().maxDepth(treeNode));;
    }
}
