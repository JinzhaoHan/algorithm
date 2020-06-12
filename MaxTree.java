package maxtree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 构建一棵树，使其头节点大于左右子树，类似堆的结构
 * 方法1： 堆
 * 方法2： 单调栈
 */
public class MaxTree {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }

    }

    public static void main(String[] args) {
        int[] data = {1,2,3,4,5,6,7,8};
//        Node root = makeByHeaP(data);
//        levelOrder(root);
        Node root = getByStack(data);
        levelOrder(root);
    }

    public static Node getByStack(int[] arr) {
        int len = arr.length;
        // 先生成所有的数组
        Node[] nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(arr[i]);
        }
        // lr 存放左右两边比他大的树的下标。
        Integer[][] lr = new Integer[len][2];
//        不存在重复的数，所以栈内存放一个下标即可
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            int num = arr[i];
            while (!stack.isEmpty() && num >arr[stack.peek()]){
                int mid = stack.pop();
                lr[mid][1] = i;
                if(!stack.isEmpty()){
                    lr[mid][0] = stack.peek();
                }
            }
            stack.push(i);
        }
        // 最后如果栈内不空，特殊处理情况， 每pop出一个，如果栈内不空，则pop出的左边大的为此时栈顶
        while(!stack.isEmpty()){
            int mid = stack.pop();
            if(!stack.isEmpty()){
                lr[mid][0] = stack.peek();
            }
        }

        // 次数仅供测试结果单调栈是否正确
//        System.out.println("一个元素对应的其左右两边比它大的数的下标是 ");
//        for(int i=0; i<len; i++){
//            System.out.println(Arrays.toString(lr[i]));
//        }

        Node root = null;
        // 开始根据以上的结果构建数
        for(int i=0; i<len; i++){
            if(lr[i][0] ==null && lr[i][1]==null){
                // 代表该数是最大值
                root = nodes[i];
            }else if(lr[i][0]==null){
                // 只有一个数 在其右边比它大
                int idx = lr[i][1];  // 比它大的数对应的下标是idx;
                if(nodes[idx].left==null){
                    nodes[idx].left = nodes[i];
                }else{
                    nodes[idx].right = nodes[i];
                }
            }else if(lr[i][1]==null){
                // 只有左边的数比他大
                int idx = lr[i][0];  // 比它大的数对应的下标是idx;
                if(nodes[idx].left==null){
                    nodes[idx].left = nodes[i];
                }else{
                    nodes[idx].right = nodes[i];
                }
            }else{
                //左右两边各有一个比他大，选择挂在小的后面
                int idx = arr[lr[i][0]] < arr[lr[i][1]] ? lr[i][0]: lr[i][1] ;
                if(nodes[idx].left==null){
                    nodes[idx].left = nodes[i];
                }else{
                    nodes[idx].right = nodes[i];
                }
            }
        }
        return root;
    }


    public static Node makeByHeaP(int[] arr) {
        // 先把数组按照堆的规则，排列，然后从堆构建二叉树
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            heapify(arr, i);
        }
        System.out.println("heap arr");
        System.out.println(Arrays.toString(arr));
        Node[] nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(arr[i]);
        }

        // 已经构建成了堆，然后按照对的方式构建左右子树即可
        // 构建出来的肯定使一颗完全二叉树
        for (int i = 0; i < len / 2; i++) {
            if (2 * i + 1 < len) {
                nodes[i].left = nodes[2 * i + 1];
            }
            if (2 * i + 2 < len) {
                nodes[i].right = nodes[2 * i + 2];
            }
        }
        return nodes[0];
    }

    private static void heapify(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * 层序遍历二叉树 查看结果
     *
     * @param root
     */
    public static void levelOrder(Node root) {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            if(tmp!=null){
                System.out.print(tmp.val+"   ");
                queue.offer(tmp.left);
                queue.offer(tmp.right);
            }else{
                System.out.print("null  ");
            }
        }
        System.out.println();


    }
}
