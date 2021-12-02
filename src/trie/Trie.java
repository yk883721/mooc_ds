package trie;

import set.BSTSet;
import util.FileOperation;

import java.util.ArrayList;
import java.util.TreeMap;

public class Trie {

    private Node root;
    private int size;

    public Trie(){
        this.root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    // 向Trie中添加一个新的单词word
    public void add(String word){
        Node p = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            p.next.putIfAbsent(c, new Node());

            p = p.next.get(c);
        }
        if (!p.isWord){
            size++;
            p.isWord = true;
        }
    }

    // 查询单词word是否在Trie中
    public boolean contains(String word){
        Node p = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!p.next.containsKey(c)){
                return false;
            }
        }
        return p.isWord;
    }

    // 查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix){
        Node p = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!p.next.containsKey(c)){
                return false;
            }
        }
        return p.isWord;
    }

    static class Node{
        boolean isWord;
        TreeMap<Character, Node> next;

        public Node() {
            this(false);
        }

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }
    }



    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)){

            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for(String word: words)
                set.add(word);

            for(String word: words)
                set.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.size());
            System.out.println("BSTSet: " + time + " s");

            // ---

            startTime = System.nanoTime();

            Trie trie = new Trie();
            for(String word: words)
                trie.add(word);

            for(String word: words)
                trie.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie: " + time + " s");
        }
    }

}
