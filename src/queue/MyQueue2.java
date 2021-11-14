package queue;

public class MyQueue2 {

    MyStack inStack ;
    MyStack outStack ;

    /** Initialize your data structure here. */
    public MyQueue2() {
        inStack = new MyStack();
        outStack = new MyStack();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (outStack.isEmpty()){
            while (!inStack.isEmpty()){
                int value = inStack.pop();
                outStack.push(value);
            }
        }
        return outStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (outStack.isEmpty()){
            while (!inStack.isEmpty()){
                int value = inStack.pop();
                outStack.push(value);
            }
        }
        return outStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private class MyStack{

        int[] data ;
        int size;

        MyStack(){
            this.data = new int[100];
            size = 0;
        }

        public int size(){
            return size;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public void push(int value){
            data[size++] = value;
        }

        public int pop(){
            return data[--size];
        }

        public int peek(){
            return data[size-1];
        }

    }

    public static void main(String[] args) {

        MyQueue2 queue = new MyQueue2();
        for(int i = 0 ; i < 10 ; i ++){
            queue.push(i);
            System.out.println(queue);
            if(i % 3 == 2){
                queue.pop();
                System.out.println(queue);
            }
        }
    }

}
