package com.sdcsoft.datamanage.mapper;

import com.sdcsoft.datamanage.model.EmployeeRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeRoleMapper {

    @Insert("<script>"+
            "insert into Employee_Role(EmployeeId,RoleId)"
            + "values "
            + "<foreach collection =\"employeeRoleList\" item=\"employeeRole\" index=\"index\" separator =\",\"> "
            + "(#{employeeRole.employeeId},#{employeeRole.roleId}) "
            + "</foreach > " +
            "</script>")
    void insertManyEmployeeRole(@Param("employeeRoleList") List<EmployeeRole> employeeRoleList);

    @Delete("delete from Employee_Role where EmployeeId=#{employeeId}")
    void deleteEmployeeRoleByEmployeeId(@Param("employeeId") Integer employeeId);
}
