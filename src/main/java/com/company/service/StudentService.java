package com.company.service;

import com.company.entity.Student;
import com.company.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void saveStudent(Student student){
        studentRepository.save(student);
    }

    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    public void deleteStudent(int id){
        studentRepository.deleteById(id);
    }

    public Student findStudentById(int id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()){
            return studentOptional.get();
        }else{
            throw new NullPointerException("Student not found in this id = " + id + "!");
        }
    }

    public List<Student> getStudentsByHint(String hint){
        return studentRepository.findStudentsByNameLikeOrProfessionLike(hint);
    }
}
