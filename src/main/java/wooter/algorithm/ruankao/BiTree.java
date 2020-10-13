package wooter.algorithm.ruankao;

public class BiTree {
    public int data;
    public BiTree lchild;
    public BiTree rchild;

    public BiTree(){}

    public BiTree(int data){
        this(data, null, null);
    }

    public BiTree(int data, BiTree lchild, BiTree rchild){
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
    }

    static BiTree createCertainBiTree(){
        BiTree root = new BiTree(1);

        root.lchild = new BiTree(2);
        root.rchild = new BiTree(3);

        root.lchild.lchild = new BiTree(4);
        root.lchild.rchild = new BiTree(5);

        root.rchild.lchild = new BiTree(6);
        root.rchild.rchild = new BiTree(7);

        root.lchild.lchild.lchild = new BiTree(8);
        root.lchild.lchild.rchild = new BiTree(9);
        return root;
    }

    static void preOrder(BiTree root){
        if (root != null){
            System.out.print(root.data + ",");
            preOrder(root.lchild);
            preOrder(root.rchild);
        }
    }

    static void inOrder(BiTree root){
        if (root != null){
            inOrder(root.lchild);
            System.out.print(root.data + ",");
            inOrder(root.rchild);
        }
    }

    static void postOrder(BiTree root){
        if (root != null){
            postOrder(root.lchild);
            postOrder(root.rchild);
            System.out.print(root.data + ",");
        }
    }
}

class Main{
    public static void main(String[] args) {
        BiTree biTree = BiTree.createCertainBiTree();
        BiTree.inOrder(biTree);
    }
}
