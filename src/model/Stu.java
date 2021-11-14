package model;

public class Stu {

    private String name;

    public Stu(String name){
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



}
