package map;

import util.FileOperation;

import java.util.ArrayList;

public class LinkedListMap<K, V> implements Map<K, V> {

    private Node head;
    private int size;

    public LinkedListMap(){
        this.head = new Node();
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null){
            head.next = new Node(key, value, head.next);
            size++;
        }else {
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node p = head;
        while (p.next != null){
            if (p.next.key.equals(key)){
                Node nextNode = p.next;
                p.next = nextNode.next;
                nextNode.next = null;
                size--;
                return nextNode.value;
            }
            p = p.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        Node node = getNode(key);
        return node != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node == null){
            throw new RuntimeException(key + " doesn't exist!");
        }
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

    private Node getNode(K key){
        Node p = head.next;
        while (p != null){
            if (key.equals(p.key)){
                return p;
            }
            p = p.next;
        }
        return null;
    }


    private class Node{

        public K key;
        public V value;
        public Node next;

        public Node(){}

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString(){
            return key.toString() + " : " + value.toString();
        }

    }


    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            LinkedListMap<String, Integer> map = new LinkedListMap<>();
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
