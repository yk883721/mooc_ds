package search;

public class BinarySearch {

    private BinarySearch(){}

    public static void main(String[] args) {

        System.out.println(search(new Integer[]{-1,0,3,5,9,12},2));
    }

    public static <T extends Comparable<T>> int search(T[] data, T target){
        return binarySearch(data,0, data.length - 1,target);
    }

    public static <T extends Comparable<T>> int binarySearch(T[] data, int l, int r, T target){
        if (l > r)
            return -1;
        int mid = l + (r - l) / 2;

        if (data[mid].equals(target)){
            return mid;
        }else if (data[mid].compareTo(target) < 0){
            return binarySearch(data, mid + 1, r, target);
        }else {
            return binarySearch(data, l, mid -1, target);
        }
    }

}
