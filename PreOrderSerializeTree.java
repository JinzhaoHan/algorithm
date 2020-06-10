package serializeTree;

import java.util.LinkedList;
import java.util.Queue;

public class PreOrderSerializeTree {
    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
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

    public static void main(String[] args) {
        TreeNode root = makeATree();
        String s = preSerialize(root);
        System.out.println(s);

        TreeNode reconstruct = reconstruct(s);

//        TreeNode reconstruct = reconstruct(s);
        System.out.println(preSerialize(reconstruct));


    }

    public static TreeNode reconstruct(String s){
        if("#_".equals(s) ){
            return null;
        }
        String[] arr = s.split("_");

        Queue<TreeNode> queue = new LinkedList<>();
        for(String str:arr){
            if("#".equals(str)){
                queue.offer(null);
            }else{
                queue.offer(new TreeNode(Integer.parseInt(str)));
            }
        }
        return doReconstruct(queue);

    }
    public static TreeNode doReconstruct(Queue<TreeNode> queue){
        TreeNode root = queue.poll();
        if(root!=null){
            root.left = doReconstruct(queue);
            root.right =doReconstruct(queue);
        }
        return root;


    }

    public static String preSerialize(TreeNode root){
        if(root==null){
            return "#_";
        }
        return root.val+"_"+preSerialize(root.left)+preSerialize(root.right);

    }
}
