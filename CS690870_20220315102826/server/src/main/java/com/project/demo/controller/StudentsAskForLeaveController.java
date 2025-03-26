package com.project.demo.controller;

import com.project.demo.entity.StudentsAskForLeave;
import com.project.demo.service.StudentsAskForLeaveService;
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
 *学生请假：(StudentsAskForLeave)表控制层
 *
 */
@RestController
@RequestMapping("/students_ask_for_leave")
public class StudentsAskForLeaveController extends BaseController<StudentsAskForLeave,StudentsAskForLeaveService> {

    /**
     *学生请假对象
     */
    @Autowired
    public StudentsAskForLeaveController(StudentsAskForLeaveService service) {
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
