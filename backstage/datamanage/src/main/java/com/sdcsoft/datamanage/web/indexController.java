package com.sdcsoft.datamanage.web;

import com.sdcsoft.datamanage.mapper.EmployeeMapper;
import com.sdcsoft.datamanage.model.Employee;
import com.sdcsoft.datamanage.utils.Result;
import com.sdcsoft.datamanage.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class indexController {

    @GetMapping(value = "/login")
    public String login() {
        return "index";
    }
}
