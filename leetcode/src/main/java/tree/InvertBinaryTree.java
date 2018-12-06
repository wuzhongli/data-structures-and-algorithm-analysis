package tree;

/**
 * 226 翻转二叉树
 *
 * @author wzl
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.stringToBinaryNode("[4,2,7,1,3,6,9]");
        TreeNode.levelOrder(treeNode);
        System.out.println("翻转二叉树");
        TreeNode.levelOrder(new InvertBinaryTree().invertTree(treeNode));
    }
}
