package sort;

import util.ArrayGenerator;
import util.SortingHelper;

public class BubbleSort {

    private BubbleSort(){

    }

    public static <T extends Comparable<T>> void sort2(T[] arr){

        for (int i = 0; i < arr.length - 1;) {
            int lastSwapIndex = 0;
            for (int j = 0; j + 1 <= arr.length - 1 - i; j++) {
                if (arr[j + 1].compareTo(arr[j]) < 0){
                    swap(arr,j,j + 1);
                    lastSwapIndex = j + 1;
                }
            }
            i = arr.length - lastSwapIndex;
        }

    }

    public static <T extends Comparable<T>> void sort(T[] arr){
        // arr[n-1,n) 已排好序
        // 通过冒泡在 arr[n - 1 - i] 位置上放合适的元素

        for (int i = 0; i < arr.length - 1;) {
            int lastSwapIndex = 0;
            for (int j = 0;j < arr.length - i - 1; j++) {
                if (arr[j + 1].compareTo(arr[j]) < 0){
                    swap(arr, j, j + 1);
                    lastSwapIndex = j + 1;
                }
            }
            i = arr.length - lastSwapIndex ;
        }
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) throws Exception {

        int n = 10000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);

        SortingHelper.sortTest(BubbleSort::sort2, arr);
    }


}
