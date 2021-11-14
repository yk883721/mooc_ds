package queue;



public class MyStack2 {

    LoopQueue<Integer> queue;

    private int lastValue;

    /** Initialize your data structure here. */
    public MyStack2() {
        queue = new LoopQueue<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        lastValue = x;
        queue.enqueue(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        LoopQueue<Integer> anotherQueue = new LoopQueue<>();

        int value = queue.dequeue();
        while (!queue.isEmpty()){
            lastValue = value;

            anotherQueue.enqueue(value);
            value = queue.dequeue();
        }

        this.queue = anotherQueue;
        return value;
    }

    /** Get the top element. */
    public int top() {
       return lastValue;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }

    public class LoopQueue<T>{

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

        public int getSize() {
            return tail >= front ? tail - front : data.length - (front - tail);
        }

        public int getCapacity(){
            return data.length - 1;
        }

        public boolean isEmpty() {
            return front == tail;
        }

        public void enqueue(T t) {
            if ( (tail + 1) % data.length == front){
                resize(getCapacity() * 2);
            }

            data[tail] = (Object) t;
            tail = (tail + 1) % data.length;
        }

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
    }

    public static void main(String[] args) {

        MyStack2 stack = new MyStack2();

        stack.push(1);

        int pop = stack.pop();
        System.out.println(pop);

        System.out.println(stack.empty());

    }

}
