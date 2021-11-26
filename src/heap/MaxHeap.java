package heap;

import array.Array;

import java.util.Arrays;

public class MaxHeap<E extends Comparable<E>> {

    private Object[] data;
    private int size;

    public MaxHeap(int capacity){
        data = new Object[capacity + 1];
        data[0] = null;
        size = 0;
    }

    public MaxHeap(E[] arr){
        data = new Object[arr.length + 1];
        data[0] = null;
        size = arr.length;

        System.arraycopy(arr, 0, data, 1, arr.length);

        int lastNode = (size) / 2;
        for (int i = lastNode; i >= 1 ; i--){
            siftDown(i);
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private int parent(int index){
        if (index <= 1){
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return index / 2;
    }

    private int leftChild(int index){
        return index * 2;
    }

    private int rightChild(int index){
        return index * 2 + 1;
    }

    public void add(E e){
        size++;
        data[size] = e;
        siftUp( size );
    }

    private void siftUp(int k){
        while (k > 1 && get(k).compareTo(get(parent(k))) > 0){
            swap(k, parent(k));
            k = parent(k);
        }
    }

    private void swap(int i, int j){
        E temp = get(i);
        data[i] = data[j];
        data[j] = temp;
    }

    public E findMax(){
        if(size == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return get(1);
    }

    public E extractMax(){
        E max = findMax();

        swap(1, size);
        data[size] = null;
        size--;

        siftDown(1);

        return max;
    }

    protected void siftDown(int k){
        while (leftChild(k) <= size ){
            int j = leftChild(k);
            if (j + 1 <= size && get(j + 1).compareTo(get(j)) > 0){
                j = j + 1;
            }

            if (get(k).compareTo(get(j)) > 0){
                break;
            }

            swap(k, j);
            k = j;
        }
    }

    public E replace(E e){
        E max = findMax();

        data[1] = e;
        siftDown(1);

        return max;
    }


    @SuppressWarnings("all")
    E get(int index){
        return (E)data[index];
    }


}
