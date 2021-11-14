package sort;

import util.ArrayGenerator;
import util.SortingHelper;

import java.util.Arrays;

public class MergeSortBU<T> {

    private MergeSortBU() {
    }

    public static <T extends Comparable<T>> void sortBU(T[] arr){
        int n = arr.length;
        T[] temp = Arrays.copyOf(arr,arr.length);

        for (int sz = 1; sz < n; sz += sz){

            // 合并区间：[l, l + sz -1] 和 [l+sz, l + 2 * sz - 1]
            for (int l = 0; l + sz - 1 < n; l += 2 * sz){
                int mid = l + sz - 1;
                int r = Math.min(l + 2 * sz -1, n - 1);
                merge(arr, l, mid, r, temp);
            }
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
            SortingHelper.sortTest(MergeSortBU::sortBU, arr);
        }
    }

}
