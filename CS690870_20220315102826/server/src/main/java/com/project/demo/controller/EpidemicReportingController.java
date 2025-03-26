package com.project.demo.controller;

import com.project.demo.entity.EpidemicReporting;
import com.project.demo.service.EpidemicReportingService;
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
 *疫情上报：(EpidemicReporting)表控制层
 *
 */
@RestController
@RequestMapping("/epidemic_reporting")
public class EpidemicReportingController extends BaseController<EpidemicReporting,EpidemicReportingService> {

    /**
     *疫情上报对象
     */
    @Autowired
    public EpidemicReportingController(EpidemicReportingService service) {
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
