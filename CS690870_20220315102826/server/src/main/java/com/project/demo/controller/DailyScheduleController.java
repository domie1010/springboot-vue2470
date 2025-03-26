package com.project.demo.controller;

import com.project.demo.entity.DailySchedule;
import com.project.demo.service.DailyScheduleService;
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
 *每日安排：(DailySchedule)表控制层
 *
 */
@RestController
@RequestMapping("/daily_schedule")
public class DailyScheduleController extends BaseController<DailySchedule,DailyScheduleService> {

    /**
     *每日安排对象
     */
    @Autowired
    public DailyScheduleController(DailyScheduleService service) {
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
