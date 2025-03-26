package com.project.demo.controller;

import com.project.demo.entity.PunchInEveryDay;
import com.project.demo.service.PunchInEveryDayService;
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
 *每日打卡：(PunchInEveryDay)表控制层
 *
 */
@RestController
@RequestMapping("/punch_in_every_day")
public class PunchInEveryDayController extends BaseController<PunchInEveryDay,PunchInEveryDayService> {

    /**
     *每日打卡对象
     */
    @Autowired
    public PunchInEveryDayController(PunchInEveryDayService service) {
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
