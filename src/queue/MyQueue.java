package queue;

public class MyQueue {

    MyStack stack ;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new MyStack();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        MyStack anotherStack = new MyStack();

        while (!stack.isEmpty()){
            int pop = stack.pop();
            anotherStack.push(pop);
        }

        stack.push(x);

        while (!anotherStack.isEmpty()){
            int pop = anotherStack.pop();
            stack.push(pop);
        }

    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack.pop();
    }

    /** Get the front element. */
    public int peek() {
        return stack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
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

        MyQueue queue = new MyQueue();
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
