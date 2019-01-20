package com.sdcsoft.datamanage.service;

import com.sdcsoft.datamanage.model.Employee;

public interface EmployeeService {

    Employee findOneByLoginId(Integer id);
}
