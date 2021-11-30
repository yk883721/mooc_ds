package sort;

import util.ArrayGenerator;
import util.SortingHelper;

public class ShellSort {

    private ShellSort(){

    }

    public static <T extends Comparable<T>> void sort(T[] arr){

        int h = arr.length / 2;
        while (h >= 1){
            for (int start = 0; start < h; start ++) {

                // 对 arr[start, start + h, start + 2h, start + 3h ... 进行插入排序 ]
                for (int i = start + h; i < arr.length; i += h) {
                    T temp = arr[i];
                    int j;

                    for (j = i; (j - h) >= 0 && temp.compareTo(arr[j - h])  < 0  ; j -= h) {
                        arr[j] = arr[j - h];
                    }
                    arr[j] = temp;
                }
            }
            h /= 2;
        }
    }

    public static void main(String[] args) throws Exception {

        int[] dataSize = new int[]{10000,10000};

        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generateRandomArray(n,n + 100);
            SortingHelper.sortTest(ShellSort::sort, arr);
        }
    }

}
