package wooter.algorithm.leetcode.bank.s111.二叉树最小深度;

/**
 * @author tao.wu9
 */
public class Solution2 {

    /**
     * 记录最小深度（根节点到最近的叶子节点的距离）
     */
    private int minDepth = Integer.MAX_VALUE;

    /**
     * 记录当前遍历到的节点深度
     */
    private int currentDepth = 0;

    /**
     * DSF
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 从根节点开始 DFS 遍历
        traverse(root);
        return minDepth;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // 前序位置进入节点时增加当前深度
        currentDepth++;

        // 如果当前节点是叶子节点，更新最小深度
        if (root.left == null && root.right == null) {
            minDepth = Math.min(minDepth, currentDepth);
        }

        traverse(root.left);
        traverse(root.right);

        // 后序位置离开节点时减少当前深度
        currentDepth--;
    }
}
