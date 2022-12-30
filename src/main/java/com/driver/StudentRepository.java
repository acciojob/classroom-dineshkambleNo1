package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {

    // student ka details rekhega
    private HashMap<String, Student> studentMap;

    // teacher ka details rekhega
    private HashMap<String, Teacher> teacherMap;

    // student-teacher ka details rekhega
    private HashMap<String, List<String>> studentTeacherMapping;

    //pehle initilize krdo warna null pointer exception dega
   public StudentRepository() {
        super();
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.studentTeacherMapping = new  HashMap<String, List<String>>();
    }

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


    // yaha pr ekk dikat h
    public Student getStudent(String name) {

       return studentMap.get(name);
    }

    public Teacher getTeacherName(String name) {
       return teacherMap.get(name);
    }


    public List<String> findStudentsFromTeacher(String teacher) {

       List<String> studentList = new ArrayList<String>();

       if(studentTeacherMapping.containsKey(teacher)) studentList = studentTeacherMapping.get(teacher);
       return studentList;
    }

    public List<String> searchAllStudents() {
       return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacherByName(String teacher) {

       List<String> students = new ArrayList<String>();

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
       // yaha galat hogaaa

       if(teacherMap.containsKey(teacher))
       {
           teacherMap.remove(teacher);
       }
    }


    public void deleteAllData() {

       HashSet<String> set = new HashSet<>();

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

        studentTeacherMapping = new HashMap<>();
        teacherMap = new HashMap<>();
    }
}
