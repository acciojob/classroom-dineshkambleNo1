package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {

    // student ka details rekhega
     HashMap<String, Student> studentMap = new HashMap<>();

    // teacher ka details rekhega
     HashMap<String, Teacher> teacherMap =  new HashMap<>();

    // student-teacher ka details rekhega
     HashMap<String, List<String>> studentTeacherMapping = new HashMap<>();

    //pehle initilize krdo warna null pointer exception dega


    public  void saveStudent(Student student) {

       studentMap.put(student.getName(),student);
    }

    public void saveTeacher(Teacher teacher) {
       teacherMap.put(teacher.getName(), teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher) {
       //check kr lo ki student or teacher ka object he ki nhi

        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher))
        {

            List<String> studentsByCurrentTeacher = new ArrayList<>();

            // teacher k samne uss-se padhne wale bache ko betha do....

            if(studentTeacherMapping.containsKey(teacher)){
               studentsByCurrentTeacher = studentTeacherMapping.get(teacher);

                studentsByCurrentTeacher.add(student);

                studentTeacherMapping.put(teacher,studentsByCurrentTeacher);
            }

        }
    }


    // yaha pr ekk dikat ho skti h
    public Student searchStudent(String name) {
       return studentMap.get(name);
    }


    public Teacher getTeacherName(String name) {
        return teacherMap.get(name);
    }


    public List<String> findStudentsFromTeacher(String teacher) {

       List<String> studentList = new ArrayList<>();

       if(studentTeacherMapping.containsKey(teacher)) studentList = studentTeacherMapping.get(teacher);
       return studentList;
    }

    public List<String> searchAllStudents() {

      List<String > students= new ArrayList<>();

      for(String student : studentMap.keySet()){
          students.add(student);
      }
      return students;
    }

    public void deleteTeacherByName(String teacher) {

       List<String> students = new ArrayList<>();

       if(studentTeacherMapping.containsKey(teacher)){

           // pehle toh student pata krlo specific teacher k

           students = studentTeacherMapping.get(teacher);

           // fir teachers delete krdooo

           for (String student: students) {
                if(studentMap.containsKey(student))
                {
                    studentMap.remove(student);
                }
           }

           // pair ko delete krdoo

           studentTeacherMapping.remove(teacher);


       }


       if(teacherMap.containsKey(teacher))
       {
           teacherMap.remove(teacher);
       }
    }


    public void deleteAllData() {

       HashSet<String> set = new HashSet<>();
        teacherMap = new HashMap<>();

       for (String teacher: studentTeacherMapping.keySet()) {

            for(String student : studentTeacherMapping.get(teacher)){
                set.add(student);
            }
        }

        for (String student: set ) {
            if(studentMap.containsKey(student)){
                studentMap.remove(student);
            }
        }

        //studentTeacherMapping = new HashMap<>();

    }


}
