package com.sdcsoft.datamanage.web;

import com.sdcsoft.datamanage.mapper.EmployeeMapper;
import com.sdcsoft.datamanage.model.Employee;
import com.sdcsoft.datamanage.service.EmployeeService;
import com.sdcsoft.datamanage.utils.Result;
import com.sdcsoft.datamanage.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping(value = "/login")
    public Result login(@RequestParam(name = "account") String account, @RequestParam(name = "passWord") String passWord) {
        Employee employee = employeeMapper.loginByAccount(account);
        if (null == employee)
            return ResultGenerator.genFailResult(0,"用户名或者密码输入错误");
        if (employee.getPassword().equals(passWord)) {
            if (Employee.STATUS_ENABLE == employee.getStatus()) {
                return ResultGenerator.genSuccessResult(1,"success",employee.getId());
            } else {
                return ResultGenerator.genFailResult(0,"您的用户账号被禁用，请联系系统管理人员！");
            }
        } else {
            return ResultGenerator.genFailResult(0,"用户名或者密码输入错误");
        }
    }

}
