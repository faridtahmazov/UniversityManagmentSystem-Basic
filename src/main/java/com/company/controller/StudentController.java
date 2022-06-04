package com.company.controller;

import com.company.entity.Student;
import com.company.entity.util.Hint;
import com.company.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String students(Model model, @ModelAttribute Hint hint){
        List<Student> studentList;

        if (hint.getHint() == null){
            studentList = studentService.getAllStudent();
        }else{
            System.out.println("Hint isn't null!");
            studentList = studentService.getStudentsByHint(hint.getHint());
        }
        model.addAttribute("students", studentList);
        return "student-list";
    }

    @GetMapping("/new-student")
    public String newStudent(){
        return "new-student";
    }

    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute Student student, HttpSession session){
        studentService.saveStudent(student);
        String successMsg = "Student added successfully!";
        session.setAttribute("msg", successMsg);

        return "redirect:/students";
    }

    @GetMapping("student/delete/{id}")
    public String deleteStudent(@PathVariable int id, HttpSession session){
        studentService.deleteStudent(id);

        String successMsg = "Student deleted successfully!";
        session.setAttribute("msg", successMsg);

        return "redirect:/students";
    }

    @GetMapping("/student/edit/{id}")
    public String editStudent(@PathVariable int id, Model model){
        Student student = studentService.findStudentById(id);
        model.addAttribute("student", student);

        return "edit-student";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute Student student, HttpSession session){
        studentService.saveStudent(student);

        String successMsg = "Student updated successfully!";
        session.setAttribute("msg", successMsg);

        return "redirect:/students";
    }

}
