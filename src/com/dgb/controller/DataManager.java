package com.dgb.controller;

import com.dgb.Utils.Constants;
import com.dgb.model.DataCenter;
import com.dgb.model.Student;

import javax.xml.crypto.Data;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class DataManager {


    public void initData(long numbers) throws IOException {
        FileWriter fw = new FileWriter(Constants.PATH_FILE);
        for (int i = 1; i <= numbers; i++) {
            String record = i + Constants.FIELD_SPLIT + "Stu00" + i + Constants.FIELD_SPLIT
                    + Constants.GENDER[new Random().nextInt(Constants.GENDER.length)]
                    + Constants.RECORD_SPLIT;
            fw.write(record);
        }
        fw.close();
        System.out.println("Data initialized successful ..!");
    }

    public void retrieveData(DataCenter<Student> students) throws IOException {
        FileReader fr = new FileReader(Constants.PATH_FILE);
        int ch;
        StringBuilder st = new StringBuilder();
        while ((ch=fr.read())!=-1){
            st.append((char) ch);
        }
        String[] records = st.toString().split(Constants.RECORD_SPLIT);
        for (String record:records){
            String[] field=record.split(Constants.FIELD_SPLIT);
            Student student=new Student(Integer.parseInt(field[0]),field[1],field[2]);
            students.getData().add(student);
        }
        System.out.println("Data retrieved successfully..!");
    }
    public void commit(DataCenter<Student> studentData) throws IOException{
        FileWriter fw = new FileWriter(Constants.PATH_FILE);
        String st="";
        for (Student student:studentData.getData()){
            st+=student.toString();
        }
        fw.write(st);
        fw.close();
        System.out.println("Data stored successful ..!");
    }
    public List<Student> selectRecordByPage(int page, int limit, DataCenter<Student> studentData){
        int offset= (page-1)*limit;

        return studentData.getData().subList(offset,limit+offset);
        //1 = 0
        // 2 = 10 = (page - 1) * limit
        // 3 = 20 = 3 - 1 * 10
    }


}
