package queue;



public class MyStack3 {

    LoopQueue queue;

    /** Initialize your data structure here. */
    public MyStack3() {
        queue = new LoopQueue(100);
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.enqueue(x);

        for (int i = 0; i < queue.getSize() - 1; i++) {
            queue.enqueue(queue.dequeue());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
       return queue.dequeue();
    }

    /** Get the top element. */
    public int top() {
       return queue.getFront();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }


    public class LoopQueue{
        private byte[] data;
        private int front, tail;

        public LoopQueue(int capacity) {
            this.data = new byte[capacity + 1];
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

        public void enqueue(int t) {
            if ( (tail + 1) % data.length == front){
                resize(getCapacity() * 2);
            }

            data[tail] = (byte) t;
            tail = (tail + 1) % data.length;
        }

        public int dequeue() {
            if(isEmpty()){
                throw new IllegalArgumentException("queue is empty");
            }

            int value =  data[front];
            front = (front + 1) % data.length;

            if(getSize() == getCapacity() / 4 && getCapacity() / 2 != 0)
                resize(getCapacity() / 2);

            return value;
        }

        public int getFront() {
            if(isEmpty()){
                throw new IllegalArgumentException("queue is empty");
            }
            return data[front];
        }

        private void resize(int newCapacity){
            byte[] newData = new byte[newCapacity];
            int j = 0;
            for (int i = front; i != tail ; i = (i + 1) % data.length, j++){
                newData[j] = data[i];
            }

            this.data = newData;

            front = 0;
            tail = j;
        }
    }

    public static void main(String[] args) {

        MyStack3 stack = new MyStack3();

        stack.push(1);

        int pop = stack.pop();
        System.out.println(pop);

        System.out.println(stack.empty());

    }

}
