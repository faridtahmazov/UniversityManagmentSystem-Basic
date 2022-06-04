package com.company.controller;

import com.company.entity.Teacher;
import com.company.entity.util.Hint;
import com.company.service.TeacherService;
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
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teachers")
    public String teachers(Model model, @ModelAttribute Hint hint){
        List<Teacher> teacherList;
        if (hint.getHint() == null){
            teacherList = teacherService.getAllTeacher();
        }else{
            teacherList = teacherService.getTeachersByHint(hint.getHint());
        }

        model.addAttribute("teachers", teacherList);
        return "teacher-list";
    }

    @GetMapping("/new-teacher")
    public String newTeacher(){
        return "new-teacher";
    }

    @PostMapping("/addTeacher")
    public String addTeacher(@ModelAttribute Teacher teacher, HttpSession session){
        teacherService.saveTeacher(teacher);
        String successMsg = "Teacher added successfully!";
        session.setAttribute("msg", successMsg);

        return "redirect:/teachers";
    }

    @GetMapping("teacher/delete/{id}")
    public String deleteTeacher(@PathVariable int id, HttpSession session){
        teacherService.deleteTeacher(id);

        String successMsg = "Teacher deleted successfully!";
        session.setAttribute("msg", successMsg);

        return "redirect:/teachers";
    }

    @GetMapping("/teacher/edit/{id}")
    public String editTeacher(@PathVariable int id, Model model){
        Teacher teacher = teacherService.findTeacherById(id);
        model.addAttribute("teacher", teacher);

        return "edit-teacher";
    }

    @PostMapping("/updateTeacher")
    public String updateTeacher(@ModelAttribute Teacher teacher, HttpSession session){
        teacherService.saveTeacher(teacher);
        String successMsg = "Teacher updated successfully!";
        session.setAttribute("msg", successMsg);

        return "redirect:/teachers";
    }
}
