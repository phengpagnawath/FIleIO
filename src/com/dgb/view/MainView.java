package com.dgb.view;

import com.dgb.Utils.Constants;
import com.dgb.Utils.Utils;
import com.dgb.controller.DataManager;
import com.dgb.model.DataCenter;
import com.dgb.model.Student;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainView {
    Scanner scanner =new Scanner(System.in);
    DataCenter<Student> students;
    DataManager dataManager;

    public MainView(DataCenter<Student> dataCenter) throws IOException{
        students=dataCenter;
        dataManager=new DataManager();
        displayMainMenu();
    }

    private void displayMainMenu() throws IOException{
        System.out.println(Constants.MENU_VIEW);
        System.out.print("Enter choice ->");
        int ch=Integer.parseInt(scanner.nextLine());
        switch (ch){
            case 1:{
                int num=Utils.inputInteger(Constants.INPUT_NUMBER);
                dataManager.initData(num);
                students.getData().clear();
                break;
            }
            case 2:{
                dataManager.commit(students);
                students=dataManager.retrieveData(students);
                for(Student student:students.getData())
                    System.out.println(student);
                break;
            }
            case 3:{
                System.out.println(students.getData().get(0));
                break;
            }
            case 4:{
                int lastIndex =students.getData().size()-1;
                System.out.println(students.getData().get(lastIndex));
                break;
            }
            case 5:{
                int id=Utils.inputInteger(Constants.INPUT_ID);
                System.out.println(students.getData().get(id+1));
                break;
            }
            case 6:{
                int page = Utils.inputInteger(Constants.INPUT_PAGE);
                int limit=Utils.inputInteger(Constants.INPUT_OFFSET);
                List<Student> stuPage=dataManager.selectRecordByPage(page,limit,students);
                for (Student student : stuPage) System.out.println(student);
                break;
            }
            case 7:{
                int id = Utils.inputInteger(Constants.INPUT_ID);
                int index=id-1;
                students.getData().remove(index);
                for(Student student:students.getData())
                    System.out.println(student);
                break;
            }
            case 8:{
                int id = Utils.inputInteger(Constants.INPUT_ID);
                int index=id-1;
                Student newStudent = updateStudent(id);
                students.getData().set(index,newStudent);
                for(Student student:students.getData())
                    System.out.println(student);
                break;
            }
            case 9:{
                int index= students.getData().size();
                Student student = insertOneStudent(index+1);
                students.getData().add(index+1,student);
                break;
            }
            case 11:{
                System.out.println(Constants.GOODBYE);
                dataManager.commit(students);
                System.exit(0);
            }
            default:{
                System.out.println(Constants.CHOSE_WRONG_OPTION);
                break;
            }
        }
        Utils.pressKeyEnter(Constants.PRESS_KEY_ENTER);
        displayMainMenu();
    }

    public Student updateStudent(int id){
        String name=Utils.inputString(Constants.INPUT_NAME);
        String gender=Utils.inputString(Constants.INPUT_GENDER);
        return new Student(id,name,gender);
    }
    public Student insertOneStudent(int id){
        String name=Utils.inputString(Constants.INPUT_NAME);
        String gender=Utils.inputString(Constants.INPUT_GENDER);
        return new Student(id,name,gender);
    }
}
