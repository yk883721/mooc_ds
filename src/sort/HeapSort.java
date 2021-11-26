package sort;

import heap.MaxHeap;
import util.ArrayGenerator;
import util.SortingHelper;

import java.util.Arrays;

public class HeapSort {

    private HeapSort(){

    }

    public static <E extends Comparable<E>> void sort(E[] arr){
        if (arr.length <= 1){
            return ;
        }

        for (int i = (arr.length - 2) / 2; i >= 0; i--){
            siftDown(arr, i, arr.length - 1);
        }

        swap(arr, 0, arr.length - 1);
        for (int i = arr.length - 2; i > 0 ; i--){
            siftDown(arr, 0, i);
            swap(arr, 0,  i);
        }

    }

    private static <E extends Comparable<E>> void siftDown(E[] arr, int k, int n){
        while ((k * 2 + 1) <= n){
            int j = k * 2 + 1;
            if ((j + 1) <= n && arr[j + 1].compareTo(arr[j]) > 0){
                j = j + 1;
            }
            if (arr[k].compareTo(arr[j]) >= 0){
                break;
            }
            swap(arr, k, j);
            k = j;
        }

    }

    private static <E extends Comparable<E>> void swap(E[] arr, int i, int j){
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static <E extends Comparable<E>> void sort2(E[] arr){
        if (arr.length <= 1){
            return ;
        }
        MaxHeap<E> heap = new MaxHeap<>(arr.length);
        for (E e : arr) {
            heap.add(e);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = heap.extractMax();
        }

    }

    public static void main(String[] args) throws Exception {

        int n = 1000000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest(MergeSort::sort, arr);
        SortingHelper.sortTest(QuickSort2Ways::sort, arr2);
        SortingHelper.sortTest(QuickSort3Ways::sort, arr3);
        SortingHelper.sortTest(HeapSort::sort, arr4);
        SortingHelper.sortTest(HeapSort::sort2, arr4);
    }

}
