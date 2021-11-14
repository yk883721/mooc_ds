//package DummyLinkedList;
//
//
//import javafx.util.Pair;
//import org.w3c.dom.Node;
//
//public class LinkedListR<E> {
//
//    private Node head;
//
//    private int size;
//
//    public LinkedListR(){
//        this.head = null;
//        this.size = 0;
//    }
//
//    public int getSize(){
//        return size;
//    }
//
//    public boolean isEmpty(){
//        return size == 0;
//    }
//
//    public void add(int index, E e){
//
//        if(index < 0 || index > size)
//            throw new IllegalArgumentException("Add failed. Illegal index.");
//
//        head = add(head, index, e);
//        size++;
//    }
//
//    public Node add(Node node, int index, E e){
//        if (index == 0){
//            return new Node(e,node);
//        }
//
//        node.next = add(node.next,index-1,e);
//        return node;
//    }
//
//    public void addFirst(E e){
//        add(0,e);
//    }
//
//    public void addLast(E e){
//        add(size,e);
//    }
//
//    // 获得链表的第index(0-based)个位置的元素
//    public E get(int index){
//
//        if(index < 0 || index >= size)
//            throw new IllegalArgumentException("Get failed. Illegal index.");
//
//        return get(head, index);
//    }
//
//    public E get(Node node, int index){
//        if (index == 0){
//            return node.value;
//        }
//        return get(node.next,index-1);
//    }
//
//    // 获得链表的第一个元素
//    public E getFirst(){
//        return get(0);
//    }
//
//    // 获得链表的最后一个元素
//    public E getLast(){
//        return get(size - 1);
//    }
//
//    // 修改链表的第index(0-based)个位置的元素为e
//    public void set(int index, E e){
//        if(index < 0 || index >= size)
//            throw new IllegalArgumentException("Update failed. Illegal index.");
//
//        set(head, index, e);
//    }
//
//    public void set(Node node, int index, E e ){
//        if (index == 0){
//            node.value = e;
//            return;
//        }
//        set(node.next,index - 1,e);
//    }
//
//    // 查找链表中是否有元素e
//    public boolean contains(E e){
//        return contains(head, e);
//    }
//
//    public boolean contains(Node node, E e){
//        if (node == null){
//            return false;
//        }
//        if (node.value.equals(e)){
//            return true;
//        }
//        return contains(node.next,e);
//    }
//
//    // 从链表中删除index(0-based)位置的元素, 返回删除的元素
//    public E remove(int index){
//        if(index < 0 || index >= size)
//            throw new IllegalArgumentException("Remove failed. Index is illegal.");
//
//        Pair<Node, E> res = remove(head, index);
//        size --;
//        head = res.getKey();
//        return res.getValue();
//    }
//
//    public Pair<Node,E> remove(Node node, int index){
//        if (index == 0){
//            return new Pair<>(node.next,node.value);
//        }
//
//        Pair<Node, E> pair = remove(node.next, index - 1);
//        node.next = pair.getKey();
//
//        return new Pair<>(node, pair.getValue());
//    }
//
//    // 从链表中删除第一个元素, 返回删除的元素
//    public E removeFirst(){
//        return remove(0);
//    }
//
//    // 从链表中删除最后一个元素, 返回删除的元素
//    public E removeLast(){
//        return remove(size - 1);
//    }
//
//    public void removeElement(E e){
//        head = removeElement(head, e);
//    }
//
//    public Node removeElement(Node node, E e){
//        if (node == null){
//            return null;
//        }
//
//        node.next = removeElement(node,e);
//
//        if (node.value.equals(e)){
//            size --;
//            return node.next;
//        }
//
//        return node;
//    }
//
//    private class Node{
//
//        public E value;
//
//        public Node next;
//
//        public Node(E value, Node next){
//            this.value = value;
//            this.next = next;
//        }
//
//        public Node(E value){
//            this(value, null);
//        }
//
//        public Node(){
//            this(null, null);
//        }
//
//        @Override
//        public String toString(){
//            return value.toString();
//        }
//
//    }
//
//}
