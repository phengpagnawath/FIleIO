package com.dgb.controller;

import com.dgb.Utils.Constants;
import com.dgb.model.DataCenter;
import com.dgb.model.Student;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.List;
import java.util.Random;

public class DataManager {


    public void initData(long numbers) throws IOException {

        FileOutputStream fout= new FileOutputStream(Constants.PATH_FILE,true);
        ObjectOutputStream outputStream= new ObjectOutputStream(fout){
            @Override
            protected void writeStreamHeader() throws IOException {
            }
        };
        for (int i = 1; i <= numbers; i++) {
            Student student = new Student(i,"Stu0"+i,Constants.GENDER[new Random().nextInt(Constants.GENDER.length)]);
            outputStream.writeObject(student);
        }
        outputStream.flush();
        System.out.println(Constants.SUCCESSFUL(Constants.INITIALIZE+" "));
        /*
        FileWriter fw = new FileWriter(Constants.PATH_FILE);

        for (int i = 1; i <= numbers; i++) {
            String record = i + Constants.FIELD_SPLIT + "Stu00" + i + Constants.FIELD_SPLIT
                    + Constants.GENDER[new Random().nextInt(Constants.GENDER.length)]
                    + Constants.RECORD_SPLIT;
            fw.write(record);
        }
        fw.close();
        System.out.println("Data initialized successful ..!");
        */
    }

    public DataCenter<Student> retrieveData(DataCenter<Student> students) throws IOException {
        FileInputStream fileInput=new FileInputStream(Constants.PATH_FILE);
        ObjectInputStream inputStream= new ObjectInputStream(fileInput){
            @Override
            protected void readStreamHeader() throws IOException, StreamCorruptedException { }
        };
        try {
            while (true){
                Student student = (Student) inputStream.readObject();
                //System.out.println(teacher);
                students.getData().add(student);
            }
        } catch (EOFException e) {
            System.out.println("Data retrieved successfully..!");
            return students;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
        /*FileReader fr = new FileReader(Constants.PATH_FILE);
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
        }*/
    }
    public void commit(DataCenter<Student> studentData) throws IOException{
        FileOutputStream fileOutput= new FileOutputStream(Constants.PATH_FILE,true);
        ObjectOutputStream outputStream= new ObjectOutputStream(fileOutput){
            @Override
            protected void writeStreamHeader() throws IOException { }
        };
        for(Student student:studentData.getData())
            outputStream.writeObject(student);
        outputStream.flush();
        System.out.println(Constants.SUCCESSFUL(Constants.INITIALIZE+" "));

        /* FileWriter fw = new FileWriter(Constants.PATH_FILE);
        String st="";
        for (Student student:studentData.getData()){
            st+=student.toString();
        }
        fw.write(st);
        fw.close();
        System.out.println("Data stored successful ..!");*/
    }
    public List<Student> selectRecordByPage(int page, int limit, DataCenter<Student> studentData){
        int offset= (page-1)*limit;

        return studentData.getData().subList(offset,limit+offset);
        //1 = 0
        // 2 = 10 = (page - 1) * limit
        // 3 = 20 = 3 - 1 * 10
    }


}
