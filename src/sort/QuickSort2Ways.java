package sort;

import util.ArrayGenerator;
import util.SortingHelper;

import java.util.Random;

public class QuickSort2Ways<T> {

    private QuickSort2Ways(){

    }

    public static <T extends Comparable<T>> void sort(T[] arr){

        Random random = new Random();
        sort(arr, 0, arr.length - 1,random);

    }

    public static <T extends Comparable<T>> void sort(T[] arr, int l, int r, Random random){
        if (l >= r){
            return ;
        }

        int p = partition(arr, l, r, random);

        sort(arr, l, p - 1, random);
        sort(arr, p + 1, r, random);

    }

    public static <T extends Comparable<T>> int partition(T[] arr, int l, int r, Random random){

        int p = l + random.nextInt( r - l + 1);
        swap(arr, l, p);

        int i = l + 1, j = r;
        // arr[l + 1, i -1] <= v, arr[j + 1, r] <= v
        while (true){
            while (i <= j && arr[i].compareTo(arr[l]) < 0)
                i++;
            while (i <= j && arr[j].compareTo(arr[l]) > 0)
                j--;
            if (i >= j)
                break;
            swap(arr, i, j);
            i++;
            j--;
        }

        swap(arr, l, j);
        return j;
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j){

        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }


    public static void main(String[] args) throws Exception {

        int[] dataSize = new int[]{10000};

        for (int n : dataSize) {
//            Integer[] arr = ArrayGenerator.generateRandomArray(n, n + 10);

            Integer[] arr = ArrayGenerator.generateOrderedArray(n);

            SortingHelper.sortTest(QuickSort2Ways::sort, arr);
        }

    }

}
