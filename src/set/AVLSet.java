package set;

import avl.AVLTree;

public class AVLSet<E extends Comparable<E>> implements Set<E> {

    AVLTree<E,Object> tree;

    public AVLSet(){
        tree = new AVLTree<>();
    }

    @Override
    public void add(E e) {
        tree.add(e, null);
    }

    @Override
    public void remove(E e) {
        tree.remove(e);
    }

    @Override
    public int size() {
        return tree.getSize();
    }

    @Override
    public boolean contains(E e) {
        return tree.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }
}
