package com.company.service;

import com.company.entity.Teacher;
import com.company.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public void saveTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeacher(){
        return teacherRepository.findAll();
    }

    public void deleteTeacher(int id){
        teacherRepository.deleteById(id);
    }

    public Teacher findTeacherById(int id){
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isPresent()){
            return teacherOptional.get();
        }else{
            throw new NullPointerException("Teacher not found in this id = " + id + "!");
        }
    }

    public List<Teacher> getTeachersByHint(String hint){
        return teacherRepository.findTeachersByNameLikeOrSubjectLike(hint);
    }
}
