package com.dgb.model;

import java.util.ArrayList;
import java.util.List;

public class DataCenter <T> {
    List<T> data;
    public DataCenter(){
        data = new ArrayList<>();
    }
    public  List<T> getData(){
        return data;
    }
}
