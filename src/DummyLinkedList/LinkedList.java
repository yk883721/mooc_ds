package DummyLinkedList;

import org.w3c.dom.Node;

public class LinkedList<E> {

    private Node dummyNode;
    private int size;

    public LinkedList(){
        dummyNode = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index, E value){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        Node pre = dummyNode;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        pre.next = new Node(value,pre.next);
        size++;
    }

    public void addFirst(E value){
        this.add(0,value);
    }

    public void addLast(E value){
        this.add(size,value);
    }

    public E get(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }

        Node curr = dummyNode.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        return curr.value;
    }

    public E getFirst(){
        return this.get(0);
    }

    public E getLast(){
        return this.get(size-1);
    }

    public void set(int index, E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }
        Node curr = dummyNode.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        curr.value = e;
    }

    public boolean contains(E e){
        Node curr = dummyNode.next;
        if (e == null){
            while (curr != null){
                if (curr.value == null){
                    return true;
                }
                curr = curr.next;
            }
        }else {
            while (curr != null){
                if (e.equals(curr.value)){
                    return true;
                }
                curr = curr.next;
            }
        }
       return false;
    }

    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        Node pre = dummyNode;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        Node delNode = pre.next;
        pre.next = delNode.next;
        delNode.next = null;
        size--;

        return delNode.value;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    public void removeElement(E e){

        Node prev = dummyNode;
        while(prev.next != null){
            if(prev.next.value.equals(e))
                break;
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

//        Node cur = dummyHead.next;
//        while(cur != null){
//            res.append(cur + "->");
//            cur = cur.next;
//        }
        for(Node cur = dummyNode.next ; cur != null ; cur = cur.next)
            res.append(cur + "->");
        res.append("NULL");

        return res.toString();
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

        Node(E value, Node next){
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

}
