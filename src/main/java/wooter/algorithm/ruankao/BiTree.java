package wooter.algorithm.ruankao;

import java.util.*;

public class BiTree {
    public int data;
    public BiTree lchild;
    public BiTree rchild;

    public BiTree(int data) {
        this(data, null, null);
    }

    public BiTree(int data, BiTree lchild, BiTree rchild) {
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
    }

    public static BiTree createCertainBiTree() {
        BiTree root = new BiTree(1);

        root.lchild = new BiTree(2);
        root.rchild = new BiTree(3);

        root.lchild.lchild = new BiTree(4);
        root.lchild.rchild = new BiTree(5);

        root.rchild.lchild = new BiTree(6);
        root.rchild.rchild = new BiTree(7);

        root.lchild.lchild.lchild = new BiTree(8);
        root.lchild.lchild.rchild = new BiTree(9);

        root.lchild.rchild.lchild = new BiTree(10);
        root.lchild.rchild.rchild = new BiTree(11);
        return root;
    }

    public static BiTree createCertainBiTree(LinkedList<Integer> dataCollect) {
        LinkedList<BiTree> levelCache = new LinkedList<>();
        Integer rootData = dataCollect.poll();
        if (rootData == null){
            return null;
        }
        BiTree root = new BiTree(rootData);
        levelCache.offer(root);

        while (!dataCollect.isEmpty()){
            BiTree levelNode = levelCache.poll();

            Integer ldata = dataCollect.poll();
            if (ldata != null){
                levelNode.lchild = new BiTree(ldata);
                levelCache.offer(levelNode.lchild);
            }

            Integer rdata = dataCollect.poll();
            if (rdata != null){
                levelNode.rchild = new BiTree(rdata);
                levelCache.offer(levelNode.rchild);
            }
        }

        return root;
    }

    public static void preOrder(BiTree root) {
        if (root != null) {
            System.out.print(root.data + ",");
            preOrder(root.lchild);
            preOrder(root.rchild);
        }
    }

    public static void inOrder(BiTree root) {
        if (root != null) {
            inOrder(root.lchild);
            System.out.print(root.data + ",");
            inOrder(root.rchild);
        }
    }

    public static void postOrder(BiTree root) {
        if (root != null) {
            postOrder(root.lchild);
            postOrder(root.rchild);
            System.out.print(root.data + ",");
        }
    }

    public static void preOderTraversal(BiTree root) {
        Stack<BiTree> stack = new Stack<>();
        if(root == null){
            return;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            BiTree node = stack.pop();
            System.out.print(node.data + ",");
            if (node.rchild != null) {
                stack.push(node.rchild);
            }
            if (node.lchild != null) {
                stack.push(node.lchild);
            }
        }
    }

    public static void inOrderTraversal(BiTree root) {
        Stack<BiTree> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.lchild;
            } else {
                root = stack.pop();
                System.out.print(root.data + ",");
                root = root.rchild;
            }
        }
    }

    public static void postOderTraversal(BiTree root) {
        Stack<Integer> res = new Stack<>();
        Stack<BiTree> stack = new Stack<>();
        if(root == null){
            return;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            BiTree node = stack.pop();
            res.push(node.data);
            if(node.lchild != null) {
                stack.push(node.lchild);
            }
            if(node.rchild != null) {
                stack.push(node.rchild);
            }
        }
        while (!res.isEmpty()) {
            System.out.print(res.pop() + ",");
        }
    }

    public static void levelOrder(BiTree root) {
        Queue<BiTree> queue = new LinkedList<>();
        if(root == null){
            return;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            BiTree node =  queue.poll();
            System.out.print(node.data + ",");
            if(node.lchild != null) {
                queue.offer(node.lchild);
            }
            if(node.lchild != null) {
                queue.offer(node.rchild);
            }
        }
    }
}

class BiTreeMain {
    public static void main(String[] args) {
        test_order(BiTree.createCertainBiTree());
        test_order(BiTree.createCertainBiTree(
                new LinkedList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11))
        ));
    }

    public static void test_order(BiTree biTree){
        BiTree.preOrder(biTree);
        System.out.println();
        BiTree.preOderTraversal(biTree);
        System.out.println();

        BiTree.inOrder(biTree);
        System.out.println();
        BiTree.inOrderTraversal(biTree);
        System.out.println();

        BiTree.postOrder(biTree);
        System.out.println();
        BiTree.postOderTraversal(biTree);
        System.out.println();

        BiTree.levelOrder(biTree);
        System.out.println();
    }
}
