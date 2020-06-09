package com.dgb;

import com.dgb.model.DataCenter;
import com.dgb.model.Student;
import com.dgb.view.MainView;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        DataCenter<Student> dataCenter = new DataCenter<>();
        try {
            MainView mainView=new MainView(dataCenter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
