package custom;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;

public class CustomList<T> extends AbstractList<T> implements RandomAccess, Cloneable {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    private Object[] data;

    private int size;

    public CustomList(){
        this.data = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public CustomList(int initialCapacity){
        if (initialCapacity < 0){
            throw new IllegalArgumentException("参数错误");
        }else if( initialCapacity == 0){
            this.data = EMPTY_ELEMENTDATA;
        }else {
            this.data = new Object[initialCapacity];
        }
    }

    public void trimToSize(){
        modCount ++;
        if ( size < data.length ){
            data = (size == 0)
                ? this.EMPTY_ELEMENTDATA
                : Arrays.copyOf(data,size);
        }

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == this.data.length - 1;
    }

    public Object[] toArray(){
        return Arrays.copyOf(data,size);
    }

    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] a){
        if (a.length < size){
            return (E[]) Arrays.copyOf(data,size,a.getClass());
        }

        System.arraycopy(data,0, a,0,size);
        if (a.length > size){
            a[size] = null;
        }

        return a;
    }


    public T get(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        return elementData(index);
    }

    public T set(int index, T element){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        T oldValue = elementData(index);
        data[index] = element;

        return oldValue;
    }

    public boolean add(T element){
        ensureCapacityInternal(size + 1);

        this.data[size++] = element;
        return true;
    }

    public void add(int index, T element){
        if ( index < 0 || index > size ){
            throw new IndexOutOfBoundsException();
        }

        ensureCapacityInternal(size + 1);

        System.arraycopy(data, index, data,index+  1,size - index );
        data[index] = element;
        size++;
    }

    public T remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException();
        }

        T old = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0){
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }

        data[--size] = null;

        return old;
    }

    public boolean remove(Object o){
        if (o == null){
            for (int i = 0; i < size; i++) {
                if(elementData(i) == null){
                    fastRemove(i);
                    return true;
                }
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData(i))){
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    private void fastRemove(int index){
        int numMoved = size - index - 1;
        if (numMoved > 0){
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }

        data[--size] = null;
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }






    public boolean addFirst(T element){
      this.add(0,element);
      return true;
    }

    public boolean addLast(T element){
        this.add(size, element);
        return true;
    }








    public T removeLast(){
        return remove(size - 1);
    }

    public T removeFirst(){
        return remove(0);
    }

    public boolean contains(Object o){
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o){
        if (o == null){
            for (int i = 0; i < size; i++) {
                if (elementData(i) == null){
                    return i;
                }
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData(i))){
                    return i;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o){
        if (o == null){
            for (int i = size-1; i >= 0; i--) {
                if (elementData(i) == null){
                    return i;
                }
            }
        }else {
            for (int i = size-1; i >= 0; i--) {
                if (o.equals(elementData(i))){
                    return i;
                }
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public T elementData(int index){
        return (T) this.data[index];
    }


    private void ensureCapacityInternal(int minCapacity){
        if(this.data == DEFAULTCAPACITY_EMPTY_ELEMENTDATA){
            minCapacity = Math.max(minCapacity, DEFAULT_CAPACITY);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity){
        // todo: modCount

        if( minCapacity > data.length){
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity){
        int oldCapacity = data.length;

        // 扩容1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < minCapacity){
            newCapacity = minCapacity;
        }

        this.data = Arrays.copyOf(data,newCapacity);
    }

}
