package avl;

import util.FileOperation;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 判断该二叉树是否是一棵二分搜索树
    public boolean isBST(){

        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for(int i = 1 ; i < keys.size() ; i ++)
            if(keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys){

        if(node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 判断该二叉树是否是一棵平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }

    // 判断以Node为根的二叉树是否是一棵平衡二叉树，递归算法
    private boolean isBalanced(Node node){

        if(node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private int getHeight(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(Node node){
        if (node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key,V value){
        if (node == null){
            size++;
            return new Node(key,value);
        }

        if (key.compareTo(node.key) < 0){
            node.left = add(node.left, key, value);
        }else if (key.compareTo(node.key) > 0){
            node.right = add(node.right, key, value);
        }else{
            node.value = value;
        }

        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        int balanceFactor = getBalanceFactor(node);
//        if (Math.abs(balanceFactor) > 1){
//
//        }
        if (balanceFactor > 1){
            if (getBalanceFactor(node.left) >= 0){
                node = rightRotation(node);
            }else {
                node.left = leftRotation(node.left);
                node = rightRotation(node);
            }
        }else if (balanceFactor < -1  ){
            if (getBalanceFactor(node.right) <= -1){
                node = leftRotation(node);
            }else {
                node.right = rightRotation(node.right);
                node = leftRotation(node);
            }
        }

        return node;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotation(Node node){
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        leftNode.height = 1 + Math.max(getHeight(leftNode.left), node.height);

        return leftNode;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotation(Node node){
        Node replaceNode = node.right;
        node.right = replaceNode.left;
        replaceNode.left = node;

        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        replaceNode.height = 1 + Math.max(node.height, getHeight(replaceNode.right));

        return replaceNode;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){
        if (node == null){
            return null;
        }
        if (node.key.equals(key)){
            return node;
        }else if (key.compareTo(node.key) < 0){
            return getNode(node.left, key);
        }else
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V value){
        Node node = getNode(root, key);
        if (node == null){
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = value;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node);
        return node;
    }

    public V remove(K key){
        Node node = getNode(root,key);
        if (node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){
        if (node == null){
            return null;
        }
        Node retNode;
        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            retNode = node.left;
        }else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            retNode = node.right;
        }else {
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }else {
                Node minimum = minimum(node.right);
                minimum.right = remove(node.right, minimum.key);
                minimum.left = node.left;

                node.left = node.right = null;

                retNode = minimum;
            }
        }
        if (retNode == null){
            return null;
        }

        retNode.height = 1 + Math.max(getHeight(retNode.left),getHeight(retNode.right));
        int balanceFactor = getBalanceFactor(retNode);
        if (balanceFactor > 1){
            if (getBalanceFactor(retNode.left) >= 0){
                retNode = rightRotation(retNode);
            }else {
                retNode.left = leftRotation(retNode.left);
                retNode = rightRotation(retNode);
            }
        }else if (balanceFactor < -1  ){
            if (getBalanceFactor(retNode.right) <= -1){
                retNode = leftRotation(retNode);
            }else {
                retNode.right = rightRotation(retNode.right);
                retNode = leftRotation(retNode);
            }
        }

        return retNode;
    }

    class Node{
        private K key;
        private V value;
        private int height;

        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.height = 1;

            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST : " + map.isBST());
            System.out.println("is Balanced : " + map.isBalanced());

            for(String word: words){
                map.remove(word);
                if(!map.isBST() || !map.isBalanced())
                    throw new RuntimeException();
            }
        }

        System.out.println();
    }

}
