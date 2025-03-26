package com.project.demo.controller;

import com.project.demo.entity.StudentDiary;
import com.project.demo.service.StudentDiaryService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *学生日记：(StudentDiary)表控制层
 *
 */
@RestController
@RequestMapping("/student_diary")
public class StudentDiaryController extends BaseController<StudentDiary,StudentDiaryService> {

    /**
     *学生日记对象
     */
    @Autowired
    public StudentDiaryController(StudentDiaryService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
