package search;

import model.Stu;

public class LinerSearch {

    private LinerSearch(){};
    
    public static <T> int search(T[] data, T target){
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(target)){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        Integer[] data = {24,18,12,9,16,66,32,4};
        int res1 = LinerSearch.search(data, 16);
        System.out.println(res1);

        int res2 = LinerSearch.search(data, 666);
        System.out.println(res2);

        Stu[] students = new Stu[]{
          new Stu("Alice"),
          new Stu("Bobo"),
          new Stu("Charles")
        };
        int res3 = LinerSearch.search(students,new Stu("Charles"));
        System.out.println(res3);

    }
    
}
