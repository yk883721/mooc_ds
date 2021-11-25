package model;

import com.sun.org.apache.xerces.internal.xs.ShortList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stu {

    private String name;

    public Stu(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o){

        if (this == o)
            return true;
        if (o == null)
            return false;
        if (this.getClass() != o.getClass())
            return false;

        Stu stu = (Stu)o;
        return name != null ? name.equals(stu.name) : stu.name == null;
    }


    public static void main(String[] args) {

        Stu stu1 = new Stu("张三");
        Stu stu2 = new Stu("张三");
        Stu stu3 = new Stu("李四");

        List<Stu> list = Arrays.asList(stu1,stu2,stu3);

        Map<String, List<Stu>> map
                = list.stream().collect(Collectors.groupingBy(Stu::getName));

        map.forEach((key,value) -> {
            System.out.println(key + ": " + value);
        });

    }


}
