package search;

public class BinarySearch {

    private BinarySearch(){}

    public static <T extends Comparable<T>> int search(T[] data, T target){
        return binarySearch(data,0, data.length - 1,target);
    }

    public static <T extends Comparable<T>> int binarySearch(T[] data, int l, int r, T target){
        if (l > r)
            return - 1;
        int mid = l + (r - l) / 2;

        if (data[mid].equals(target)){
            return mid;
        }else if (data[mid].compareTo(target) >= 0){
            return binarySearch(data, l, mid -1, target);
        }else {
            return binarySearch(data, mid + 1, r, target);
        }
    }

    public static <T extends Comparable<T>> int upper(T[] arr, T target){
        int l = 0, r = arr.length;
        while (l < r){
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) <= 0){
                l = mid + 1;
            }else {
                r = mid;
            }
        }
        return l;
    }

    public static <T extends Comparable<T>> int lower(T[] arr, T target){
        int l = -1, r = arr.length -1;
        while (l < r){
            int mid = l + (r - l + 1) / 2;
            if (arr[mid].compareTo(target) < 0){
                l = mid;
            }else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static <T extends Comparable<T>> int upperCeil(T[] arr, T target){
        int p = upper(arr, target);
        if (p - 1 >= 0 && arr[p - 1].compareTo(target) == 0){
            return p - 1;
        }
        return p;
    }

    public static <T extends Comparable<T>> int lowerCeil(T[] arr, T target){
        int l = 0, r = arr.length;
        while (l < r){
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) >= 0){
                r = mid;
            }else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static <T extends Comparable<T>> int lowerFloor(T[] arr, T target){
        int p = lower(arr, target);
        if (p + 1 <= arr.length - 1 && arr[p + 1].compareTo(target) == 0){
            return p + 1;
        }
        return p;
    }

    public static <T extends Comparable<T>> int upperFloor(T[] arr, T target){

        int l = -1, r = arr.length - 1;
        while (l < r){
            int mid = l + (r - l + 1) / 2;
            if (arr[mid].compareTo(target) > 0){
                r = mid - 1;
            }else {
                l = mid;
            }
        }
        return l;
    }


    public static void main(String[] args) {

        Integer[] arr = new Integer[]{1, 2, 2, 2, 1};
        System.out.println(search(arr, 2));

    }

}
