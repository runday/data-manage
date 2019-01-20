package com.sdcsoft.datamanage.mapper;

import com.sdcsoft.datamanage.model.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper{

    String TABLE_NAME_ROLE="Role";

    @Select("<script>" +
            "select * from "+TABLE_NAME_ROLE+" "+
            "<where>"+
            "1=1 "+
            "<if test='roleName != null and roleName.length>0 '> "+
            " AND RoleName LIKE CONCAT(CONCAT('%',#{roleName}),'%')"+
            "</if>"+
            "</where>"+
            "</script>")
    List<Role> getRoleListByCondition(Role role);

    @Select("select DISTINCT r.* from "+TABLE_NAME_ROLE+" r LEFT JOIN Employee_Role er on er.RoleId = r.RoleId where er.EmployeeId =#{employeeId}")
    List<Role> getRoleListByEmployeeId(Integer employeeId);

    @Update("update "+TABLE_NAME_ROLE+" set roleName=#{roleName},RoleDesc=#{roleDesc} where RoleId = #{roleId}")
    int updateRole(Role role);

    @Insert("insert into "+TABLE_NAME_ROLE+" (RoleName,RoleDesc) values (#{roleName},#{roleDesc})")
    int insertRole(Role role);

    @Delete("delete from "+TABLE_NAME_ROLE+" where RoleId=#{roleId}")
    int deleteRoleById(Integer roleId);
}
