package stack;

import array.Array;

public class ArrayStack<T> implements Stack<T> {

    private Array<T> array;

    public ArrayStack(){
        this.array = new Array<>();
    }

    public ArrayStack(int initialCapacity){
        this.array = new Array<>(initialCapacity);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(T value) {
        array.addLast(value);
    }

    @Override
    public T pop() {
        return array.removeLast();
    }

    @Override
    public T peek() {
        return array.getLast();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: ");
        sb.append("[");

        for (int i = 0; i < array.getSize(); i++) {
            sb.append(array.get(i));
            if (i != array.getSize()-1)
                sb.append(", ");
        }

        sb.append("] top");
        return sb.toString();
    }

}
