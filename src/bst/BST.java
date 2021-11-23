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

    // 向二分搜索树中添加新的元素e
    public void add(T value){
        root = add(root, value);
    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, T value){
        if (node == null){
            size++;
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

    // 看二分搜索树中是否包含元素e
    public boolean contains(T value){
        return contains(value, root);
    }

    // 看以node为根的二分搜索树中是否包含元素e, 递归算法
    public boolean contains(T value, Node node){
        if (node == null){
            return false;
        }

        if (value.compareTo(node.e) == 0){
            return true;
        }else if (value.compareTo(node.e) < 0){
            return contains(value, node.left);
        }else {
            return contains(value, node.right);
        }
    }

    // 二分搜索树的前序遍历
    public void preOrder(){
        preOrder(root);
    }

    // 前序遍历以node为根的二分搜索树, 递归算法
    private void preOrder(Node root) {
        if (root == null){
            return;
        }

        System.out.println(root.e);
        preOrder(root.left);
        preOrder(root.right);

    }

    // 二分搜索树的非递归前序遍历
    public void preOrderNR(){
        if (root == null){
            return ;
        }

        Object[] stack = new Object[size];

        int tail = -1;
        stack[++tail] = root;

        while (tail != -1){
            Node node = (Node) stack[tail--];
            System.out.println(node.e);

            if (node.right != null)
                stack[++tail] = node.right;
            if (node.left != null)
                stack[++tail] = node.left;
        }
    }

    // 二分搜索树的中序遍历
    public void inOrder(){
        inOrder(root);
    }

    // 中序遍历以node为根的二分搜索树, 递归算法
    private void inOrder(Node node) {
        if (node == null){
            return ;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    // 二分搜索树的后序遍历
    public void postOrder(){
        postOrder(root);
    }

    // 后序遍历以node为根的二分搜索树, 递归算法
    private void postOrder(Node node) {
        if (node == null){
            return ;
        }

        inOrder(node.left);
        inOrder(node.right);
        System.out.println(node.e);

    }

    // 二分搜索树的层序遍历
    public void levelOrder(){
        if (root == null){
            return ;
        }
        // 初始化
        Object[] queue = new Object[size];
        int front = 0, tail = 0;

        //根节点 入队
        queue[tail] = root;
        tail = (tail + 1) % queue.length;

        while (! (front == tail)){
            Node node = (Node) queue[front];
            front = (front + 1) % queue.length;

            System.out.println(node.e);
            if (node.left != null){
                queue[tail] = node.left;
                tail = (tail + 1) % queue.length;
            }
            if (node.right != null){
                queue[tail] = node.right;
                tail = (tail + 1) % queue.length;
            }
        }

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

    private class Node {

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

    // 寻找二分搜索树的最小元素
    public T minimum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty!");

        return minimum(root).e;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    // 寻找二分搜索树的最大元素
    public T maximum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");
        return maximum(root).e;
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node maximum(Node node) {
        if (node.right == null){
            return node;
        }
        return maximum(node.right);
    }

    // 从二分搜索树中删除最小值所在节点, 返回最小值
    public T removeMin(){
        T ret = minimum();
        removeMin(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除最大值所在节点
    public T removeMax(){
        T ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    // 从二分搜索树中删除元素为e的节点
    public void remove(T e){
        root = remove(root, e);
    }

    // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, T e) {
        if (node == null){
            return null ;
        }

        if (e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;
        }else if (e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }else { // e.compareTo(node.e) == 0

            // 待删除节点左子树为空的情况
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况
            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node minNode = minimum(node.right);
            minNode.right = removeMin(node.right);
            minNode.left = node.left;

            node.left = null;
            node.right = null;
            return minNode;
        }
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.add(9);
        tree.add(3);
        tree.add(10);
        tree.add(2);
        tree.add(8);
        tree.add(3);
        tree.add(5);
        tree.add(6);

        tree.levelOrder();

        System.out.println("------------");

        tree.remove(3);
        tree.levelOrder();

        System.out.println("------------");

        tree.remove(8);
        tree.levelOrder();

        System.out.println("------------");

        tree.remove(5);
        tree.levelOrder();
    }
}
