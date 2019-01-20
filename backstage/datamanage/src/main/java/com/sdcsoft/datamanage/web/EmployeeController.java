package com.sdcsoft.datamanage.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdcsoft.datamanage.mapper.EmployeeMapper;
import com.sdcsoft.datamanage.mapper.EmployeeRoleMapper;
import com.sdcsoft.datamanage.model.Employee;
import com.sdcsoft.datamanage.service.EmployeeService;
import com.sdcsoft.datamanage.utils.Result;
import com.sdcsoft.datamanage.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;


    @PostMapping(value = "/getemployeeinfo")
    public Result getEmployeeInfo(@RequestParam(name = "id") Integer id) {
        return ResultGenerator.genSuccessResult(employeeService.findOneByLoginId(id));
    }
    /**
     * 查询用户列表-分页
     * @param employee
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/employeelistbyconditionandpage")
    public Result getEmployeeListByConditionAndPage(Employee employee, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return ResultGenerator.genSuccessResult(new PageInfo(employeeMapper.getEmployeeListByCondition(employee)));
    }

    /**
     * 编辑用户
     * @param employee
     * @return
     */
    @PostMapping("/editemployee")
    public Result editEmployee(@RequestBody Employee employee){
        if(employee.getId()!=null){
            employeeMapper.updateEmployee(employee);
        }else{
            employeeMapper.insertEmployee(employee);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 编辑用户角色
     * @param employee
     * @return
     */
    @PostMapping("/editemployeerole")
    public Result editEmployeeRole(@RequestBody Employee employee){
        employeeRoleMapper.deleteEmployeeRoleByEmployeeId(employee.getId());
        if(null!=employee.getEmployeeRoleList()&&employee.getEmployeeRoleList().size()>0){
            employeeRoleMapper.insertManyEmployeeRole(employee.getEmployeeRoleList());
        }
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteemployeebyid")
    public Result deleteEmployeeById(@RequestParam int id){
        employeeMapper.deleteEmployeeById(id);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 编辑用户密码
     * @param employee
     * @return
     */
    @PostMapping("/editemployeepass")
    public Result editUserPass(@RequestBody Employee employee){
        employeeMapper.updateEmployeePass(employee);
        return ResultGenerator.genSuccessResult();
    }
}
