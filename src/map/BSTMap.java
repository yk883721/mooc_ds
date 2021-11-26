package map;


import org.w3c.dom.Node;
import util.FileOperation;

import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(key, value, root);
    }

    public Node add(K key, V value, Node node) {
        if (node == null){
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0){
            node.left = add(key, value, node.left);
        }else if (key.compareTo(node.key) > 0){
            node.right = add(key, value, node.right);
        }else {
            node.value = value;
        }
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    public Node remove(Node node, K key) {
        if (node == null){
            return null;
        }

        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
        }else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
        }else {

            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node minNode = minimum(node.right);
            minNode.right = removeMin(node.right);
            minNode.left = node.left;

            node.left = node.right = null;

            return minNode;

        }

        return node;
    }

    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;

    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(Node node, K key){
        if (node == null){
            return null;
        }

        if (key.compareTo(node.key) < 0){
            return getNode(node.left, key);
        }else if (key.compareTo(node.key) > 0){
            return getNode(node.right, key);
        }else {
            return node;
        }

    }

    private class Node{

        public K key;
        public V value;

        public Node left;
        public Node right;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }


    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BSTMap<String, Integer> map = new BSTMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.size());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }

}
