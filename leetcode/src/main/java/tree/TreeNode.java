package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树节点类
 *
 * @author wzl
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public static void levelOrder(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.println(current.val);

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
    }

    /**
     * 使用数组构建二叉树 例如 [3, 9, 20, -1, -1, 15, 7]
     */
    public static TreeNode buildBinaryTreeByArray(int[] arr, int index, int n) {
        TreeNode treeNode = null;
        if (index < n && arr[index] != -1) {
            treeNode = new TreeNode(arr[index]);
            treeNode.left = buildBinaryTreeByArray(arr, 2 * index + 1, n);
            treeNode.right = buildBinaryTreeByArray(arr, 2 * index + 2, n);
        }
        return treeNode;
    }

    /**
     * 使用字符串构建二叉树 例如"[1,2,2,3,3,null,null,4,4]"
     */
    public static TreeNode stringToBinaryNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }
}
