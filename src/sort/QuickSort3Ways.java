package sort;

import util.ArrayGenerator;
import util.SortingHelper;

import java.util.Random;

public class QuickSort3Ways<T> {

    private QuickSort3Ways(){

    }

    public static <T extends Comparable<T>> void sort(T[] arr){
        Random random = new Random();
        sort(arr, 0, arr.length - 1, random);
    }

    public static <T extends Comparable<T>> void sort(T[] arr, int l, int r, Random random){
        if (l >= r){
            return ;
        }
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, l, p);

        // arr[l + 1 ... lt] < arr[l]
        // arr[lt + 1 ... i - 1] = arr[l]
        // arr[gt ... r] > arr[l]
        int i = l + 1, lt = l, gt = r + 1;
        while (i < gt){
            if (arr[i].compareTo(arr[l]) < 0){
                lt++;
                swap(arr, i, lt);
                i++;
            }else if (arr[i].compareTo(arr[l]) == 0){
                i++;
            }else if (arr[i].compareTo(arr[l]) > 0){
                gt--;
                swap(arr, i, gt);
            }
        }

        swap(arr, l, lt);

        // 交换完之后
        // arr[l,lt-1] < v
        // arr[lt, gt-1] = v
        // arr[gt, r] > v

        sort(arr, l, lt - 1, random);
        sort(arr, gt, r, random);

    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j){

        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }


    public static void main(String[] args) throws Exception {

        int n = 1000000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, 1);

        SortingHelper.sortTest(QuickSort::sort, arr);
        SortingHelper.sortTest(QuickSort2Ways::sort, arr);
        SortingHelper.sortTest(QuickSort3Ways::sort, arr);

    }

}
