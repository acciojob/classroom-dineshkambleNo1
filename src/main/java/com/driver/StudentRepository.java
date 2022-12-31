package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class StudentRepository {


    private static HashMap<String,Student> studentMap=new HashMap<String,Student>();
    private static HashMap<String,Teacher> teacherMap=new HashMap<String,Teacher>();
    private static HashMap<String, List<String>> studentTeacherMapping=new HashMap<String, List<String>>();

    public static void saveStudent(Student student){
        studentMap.put(student.getName(),student);
    }
    public static void saveTeacher(Teacher teacher ){
        teacherMap.put(teacher.getName(),teacher);
    }

    public static void saveStudentTeacherPair(String student,String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            List<String> studentsList=new ArrayList<>();
            if(studentTeacherMapping.containsKey(teacher)) studentsList=studentTeacherMapping.get(teacher);
            studentsList.add(student);
            studentTeacherMapping.put(teacher,studentsList);

        }
    }
    public static Student searchStudent(String name){
        return studentMap.get(name);
    }
    public static Teacher getTeacherName(String name){
        return teacherMap.get(name);
    }

    public static List<String> findStudentsFromTeacher(String teacher ){
        List StudentTeacherList=new ArrayList<>();
        if(studentTeacherMapping.containsKey(teacher)) StudentTeacherList=studentTeacherMapping.get(teacher);
        return StudentTeacherList;
    }
    public static List<String> searchAllStudents(){
        return new ArrayList<>(studentMap.keySet());
    }
    public static void deleteTeacherByName(String teacher){
        List<String> teacherlList=new ArrayList();
        if(studentTeacherMapping.containsKey(teacher)){
            teacherlList=studentTeacherMapping.get(teacher);
            for (String teachers:teacherlList){
                if(studentMap.containsKey(teachers)){
                    studentMap.remove(teachers);
                }
            }
        }

    }
    public static void deleteAllData(){
        HashSet<String> teachersSet=new HashSet<>();
        for(String teachers:studentTeacherMapping.keySet()){
            for(String students:studentTeacherMapping.get(teachers)){
                teachersSet.add(students);
            }
        }
        for(String student:teachersSet){
            if(studentMap.containsKey(student)){
                studentMap.remove(student);

            }
        }
    }


}