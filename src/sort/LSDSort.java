package sort;

public class LSDSort {

    private LSDSort(){

    }

    public static void sort(String[] arr, int W){

        for (String s : arr) {
            if (s.length() != W){
                throw new IllegalArgumentException("All Strings' length must be the same.");
            }
        }

        for (int r = W - 1; r >= 0; r--) {
            int R = 256;
            int[] cnt = new int[R];
            int[] index = new int[R + 1];

            String[] temp = new String[arr.length];
            for (String s : arr) {
                cnt[s.charAt(r)]++;
            }

            for (int i = 0; i < cnt.length; i++) {
                index[i + 1] = index[i] + cnt[i];
            }

            for (String s : arr) {
                temp[index[s.charAt(r)]] = s;
                index[s.charAt(r)]++;
            }
            System.arraycopy(temp, 0, arr, 0, arr.length);

        }
    }

    public static void main(String[] args) {

        String[] arr = {"BCA", "CAB", "ACB", "BAC", "ABC", "CBA"};
        LSDSort.sort(arr, 3);
        for(String s: arr)
            System.out.println(s);
    }

}
