package sort;

import util.ArrayGenerator;
import util.SortingHelper;

public class SelectionSort {

    private SelectionSort(){}

    public static <T extends Comparable<T>> void sort(T[] arr){

        // arr[0...i) 是有序的, arr[i...n) 是无序的
        for (int i = 0; i < arr.length; i++){

            //选择 arr[i...n) 中的最小值的索引
            int minIndex = i;
            for (int j = i+1; j < arr.length;j++){
               if (arr[j].compareTo(arr[minIndex]) < 0){
                   minIndex = j;
               }
            }
            if (minIndex != i){
                swap(arr,i,minIndex);
            }
        }
    }

    private static <T> void swap(T[] arr, int i, int minIndex) {
        T temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }

    public static void main(String[] args) throws Exception {

        int[] dataSize = new int[]{10000,100000};

        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generateRandomArray(n,n);
            SortingHelper.sortTest(SelectionSort::sort ,arr);
        }

    }

}
