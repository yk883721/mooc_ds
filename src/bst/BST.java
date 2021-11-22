package bst;

public class BST<T extends Comparable<T>> {

    private Node root;
    private int size;

    public BST(){
        this.root = null;
        this.size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(T value){
        root = add(root, value);
        size++;
    }

    private Node add(Node node, T value){
        if (node == null){
            node = new Node(value);
            return node;
        }

        if (value.compareTo(node.e) < 0){
            node.left = add(node.left, value);
        }else if (value.compareTo(node.e) > 0){
            node.right = add(node.right, value);
        }

        return node;
    }

    public void print(){
        print(root);
    }

    private void print(Node root){
        if (root == null){
            return;
        }

        print(root.left);
        System.out.println(root.e);
        print(root.right);
    }

    private class Node{

        public T e;
        public Node left, right;

        public Node(T e){
            this.e = e;
            this.left = null;
            this.right = null;
        }

        public Node(T e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }

    }


    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.add(9);
        tree.add(3);
        tree.add(2);
        tree.add(8);
        tree.add(3);
        tree.add(5);

        tree.print();

    }
}
