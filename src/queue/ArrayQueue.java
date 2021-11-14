package queue;

import array.Array;

public class ArrayQueue<T> implements Queue<T>{

    private Array<T> array;

    public ArrayQueue(int capacity) {
        this.array = new Array<>(capacity);
    }

    public ArrayQueue() {
        this.array = new Array<>();
    }

    public int getCapacity(){
        return array.getCapacity();
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
    public void enqueue(T t) {
        array.addLast(t);
    }

    @Override
    public T dequeue() {
        return array.removeFirst();
    }

    @Override
    public T getFront() {
        return array.getFirst();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: ");
        sb.append("front [");

        for(int i = 0 ; i < array.getSize() ; i ++){
            sb.append(array.get(i));
            if(i != array.getSize() - 1)
                sb.append(", ");
        }

        sb.append("] tail");
        return sb.toString();
    }
    public static void main(String[] args) {

        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);
            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
