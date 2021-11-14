package sort;

import util.ArrayGenerator;
import util.SortingHelper;

import java.util.Random;

public class QuickSort<T> {

    private QuickSort(){

    }

    public static <T extends Comparable<T>> void sort(T[] arr){
        Random random = new Random();
        sort(arr,0, arr.length - 1, random);
    }

    private static <T extends Comparable<T>> void sort(T[] arr, int l, int r, Random random){
        if (l >= r){
            return;
        }

        int p = partition(arr, l, r, random);
        sort(arr, l,  p - 1, random);
        sort(arr,p + 1, r, random);

    }

    private static <T extends Comparable<T>> int partition(T[] arr, int l, int r, Random random){

        // random [l, r]
        int p = l + random.nextInt(r - l + 1);
        swap(arr, l, p);

        int j = l;
        for (int i = l + 1; i <= r; i++){
            if (arr[i].compareTo(arr[l]) <= 0){
                j++;
                swap(arr, j, i);
            }
        }

        swap(arr, l, j);
        return j;
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {

        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

    public static void main(String[] args) throws Exception {

        int[] dataSize = new int[]{10000, 1000000};

        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generateOrderedArray(n);
            SortingHelper.sortTest(QuickSort::sort, arr);
        }

    }

}
