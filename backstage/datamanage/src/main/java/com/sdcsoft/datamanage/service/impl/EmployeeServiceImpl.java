package com.sdcsoft.datamanage.service.impl;

import com.sdcsoft.datamanage.mapper.EmployeeMapper;
import com.sdcsoft.datamanage.mapper.ResourceMapper;
import com.sdcsoft.datamanage.mapper.RoleMapper;
import com.sdcsoft.datamanage.model.Employee;
import com.sdcsoft.datamanage.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Employee findOneByLoginId(Integer id) {
        Employee employee=employeeMapper.findOneByLoginId(id+"");
        employee.setRoleList(roleMapper.getRoleListByEmployeeId(id));
        employee.setResourceList(resourceMapper.getResourceListByEmployeeId(id));
        return employee;
    }
}
