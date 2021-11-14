package util;

import java.util.function.Consumer;

public class SortingHelper {

    private SortingHelper(){}

    public static <T extends Comparable<T>> boolean isSorted(T[] arr){
        for (int i = 1; i < arr.length; i++){
            if (arr[i -1].compareTo(arr[i]) > 0){
                return false;
            }
        }
        return true;
    }

    public static <T extends Comparable<T>> void sortTest(Consumer<T[]> consumer, T[] arr) throws Exception {

        long startTime = System.nanoTime();

        consumer.accept(arr);

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;

        if (!SortingHelper.isSorted(arr)){
            throw new RuntimeException("failed: array unsorted");
        }

        System.out.printf("%s, n = %d : %f s\n", consumer.toString(), arr.length, time);
    }

}
