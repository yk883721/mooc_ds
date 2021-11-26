package sort;

import util.ArrayGenerator;
import util.SortingHelper;

import java.util.Arrays;

public class MergeSort<T> {

    private MergeSort() {
    }

    public static <T extends Comparable<T>> void sort(T[] arr){

        T[] temp = Arrays.copyOf(arr,arr.length);
        sort(arr, 0, arr.length - 1, temp);
    }

    private static <T extends Comparable<T>> void sort(T[] arr,int l, int r, T[] temp){

        if (l >= r){
            return;
        }
        int mid = l + (r - l) / 2;
        sort(arr, l, mid,temp);
        sort(arr, mid + 1, r,temp);
        if (arr[mid+1].compareTo(arr[mid]) < 0){
            merge(arr, l, mid, r, temp);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] arr,int l, int mid, int r, T[] temp){

        System.arraycopy(arr,l,temp,l,r-l+1);

        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++){
            if (i > mid){
                arr[k] = temp[j];
                j++;
            }else if (j > r){
                arr[k] = temp[i];
                i++;
            }else if (temp[i].compareTo(temp[j]) <= 0){
                arr[k] = temp[i];
                i++;
            }else {
                arr[k] = temp[j];
                j++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int[] dataSize = new int[]{100000,10000};

        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generateRandomArray(n,n + 10);
            SortingHelper.sortTest(MergeSort::sort, arr);
        }
    }

}
