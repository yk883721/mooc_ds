package LinkedList;


public class LinkedList<E> {

    private Node head;
    private int size;

    public LinkedList(){
        head = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(E value){

        head = new Node(value,head);
        size++;

    }

    public void addLast(E value){
        add(size,value);
    }

    public void add(int index,E value){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        if (index == 0){
            addFirst(value);
        }

        Node pre = head;
        for (int i = 0; i < index -1; i++) {
            pre = pre.next;
        }

        pre = new Node(value,pre);
        size ++;
    }



    private class Node{
        public E value;
        public Node next;

        Node(){
            this(null,null);
        }

        Node(E value){
            this(value,null);
        }

        Node(E value,Node next){
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

}
