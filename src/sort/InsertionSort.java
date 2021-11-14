package sort;

import util.ArrayGenerator;
import util.SortingHelper;

public class InsertionSort {

    private InsertionSort() {
    }

    public static <T extends Comparable<T>> void sort(T[] arr){
        // arr[0...i) 有序的，从 [i...n) 是无序的
        for (int i = 0; i < arr.length; i++){
            // 由 i 开始，向前遍历，寻找合适的插入位置
            for (int j = i; j >= 1; j--) {
                if (arr[j].compareTo(arr[j-1]) < 0){
                    swap(arr,j,j-1);
                }else {
                    break;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void sort2(T[] arr){
        // arr[0...i) 有序的，从 [i...n) 是无序的
        for (int i = 0; i < arr.length; i++){
            T temp = arr[i];

            // 由 i 开始，向前遍历，寻找合适的插入位置
            int j;
            for (j = i; j >= 1 && temp.compareTo(arr[j-1]) < 0; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = temp;
        }

    }


    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) throws Exception {

        int[] dataSize = new int[]{10000,100000};

        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generateRandomArray(n,n+100);
            SortingHelper.sortTest(InsertionSort::sort, arr);
        }
    }

}
