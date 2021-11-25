package set;

import DummyLinkedList.LinkedList;

public class LinkedListSet<E> implements Set<E>{

    private LinkedList<E> list;

    public LinkedListSet(){
        list = new LinkedList<>();
    }


    @Override
    public void add(E e) {
        if (!list.contains(e)){
            list.addLast(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public int size() {
        return list.getSize();
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

}
