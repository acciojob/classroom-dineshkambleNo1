package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService{

    @Autowired
    StudentRepository studentRepository;

    public void addStudent(Student student) {
        studentRepository.saveStudent(student);
    }

    public void addTeacher(Teacher teacher) {
        studentRepository.saveTeacher(teacher);
    }

    public void student_teacher_pair(String student, String teacher) {
        studentRepository.saveStudentTeacherPair(student,teacher);
    }

    public Student getStudent(String name) {
        return studentRepository.searchStudent(name);
    }

    public Teacher getTeacherName(String name) {
        return studentRepository.getTeacherName(name);
    }

    public List<String> findStudentFromTeacher(String teacher) {
        return studentRepository.findStudentsFromTeacher(teacher);
    }

    public List<String> searchAllStudents() {
        return studentRepository.searchAllStudents();
    }

    public void deleteTeacherByName(String teacher) {
        studentRepository.deleteTeacherByName(teacher);
    }

    public void deleteAllData() {
        studentRepository.deleteAllData();
    }


}
