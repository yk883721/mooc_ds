package queue;

public class LoopQueue<T> implements Queue<T> {

    private Object[] data;
    private int front, tail;

    public LoopQueue(int capacity) {
        this.data = new Object[capacity + 1];
        this.front = 0;
        this.tail = 0;
    }

    public LoopQueue() {
       this(10);
    }

    @Override
    public int getSize() {
        return tail >= front ? tail - front : data.length - (front - tail);
    }

    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(T t) {
        if ( (tail + 1) % data.length == front){
            resize(getCapacity() * 2);
        }

        data[tail] = (Object) t;
        tail = (tail + 1) % data.length;
    }

    @Override
    public T dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty");
        }

        T value = (T) data[front];
        data[front] = null;
        front = (front + 1) % data.length;

        if(getSize() == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);

        return value;
    }

    @Override
    public T getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty");
        }
        return (T) data[front];
    }

    private void resize(int newCapacity){
        T[] newData = (T[]) new Object[newCapacity];
        int j = 0;
        for (int i = front; i != tail ; i = (i + 1) % data.length, j++){
            newData[j] = (T) data[i];
        }

        this.data = newData;

        front = 0;
        tail = j;
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", getSize(), getCapacity()));
        res.append("front [");
        for(int i = front ; i != tail ; i = (i + 1) % data.length){
            res.append(data[i]);
            if((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args){

        LoopQueue<Integer> queue = new LoopQueue<>();
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
