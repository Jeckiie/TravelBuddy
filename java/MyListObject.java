package com.travelbuddy.jerko.travelbuddy;

public class MyListObject {
    private String name;
    private Boolean check;

    public MyListObject(String name, Boolean check) {
        this.name = name;
        this.check = check;
    }

    public String getName(){
        return name;
    }

    public boolean getChecked(){
        return check;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setChecked(boolean check){
        this.check = check;
    }

}
