package kmp;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断T1 是不是T2的子树
 * 序列化两棵树，看是不是子串
 */
public class KMPProblem2 {
    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode root = makeATree();
        String s = serializeTree(root);
        System.out.println(s);
        TreeNode recon = unserialize(s);

        System.out.println(serializeTree(recon));
        inOrder(recon);

    }

    public static void inOrder(TreeNode root){
        if(root!=null){
            inOrder(root.left);
            System.out.println(root.val);
            inOrder(root.right);
        }
    }


    public static String serializeTree(TreeNode root) {
        if (root == null) {
            return "#_";
        }
        // 按层次序列化一个树
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            if (tmp == null) {
                sb.append("#_");
            } else {
                sb.append(tmp.val + "_");
                queue.offer(tmp.left);
                queue.offer(tmp.right);
            }
        }
        return sb.toString();
    }

    public static TreeNode unserialize(String s) {
        String[] arr = s.split("_");
        Queue<TreeNode> queue = new LinkedList<>();

        Queue<TreeNode> nodes = new LinkedList<>();
        for (String str : arr) {
            if ("#".equals(str)) {
                nodes.offer(null);
            } else {
                nodes.offer(new TreeNode(Integer.parseInt(str)));
            }
        }
        TreeNode root = nodes.poll();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            if (tmp == null) {
                continue;
            }
            TreeNode left = nodes.poll();
            TreeNode right = nodes.poll();
            tmp.left = left;
            tmp.right = right;
            queue.offer(left);
            queue.offer(right);
        }
        return root;


    }

    public static TreeNode makeATree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(8);

        return root;

    }
}
