package com.sdcsoft.datamanage.mapper;

import com.sdcsoft.datamanage.model.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeMapper {

    @Select("<script>" +
            "select ee.* from Employee ee "+
            "<where>"+
            " 1=1 "+
            "<if test='status != null'> "+
            " AND Status = #{status}"+
            "</if>"+
            "<if test='realName != null and realName.length>0 '> "+
            " AND ee.RealName LIKE CONCAT(CONCAT('%',#{realName}),'%')"+
            "</if>"+
            "<if test='mobile != null and mobile.length>0 '> "+
            " AND ee.Mobile LIKE CONCAT(CONCAT('%',#{mobile}),'%')"+
            "</if>"+
            "</where>"+
            "</script>")
    List<Employee> getEmployeeListByCondition(Employee employee);

    @Select("select em.Id,em.Password,em.status from Employee em where em.mobile=#{account} or em.email=#{account}")
    Employee loginByAccount(@Param("account") String account);

    @Select("select em.*, " +
            " case em.OrgType " +
            " when 1 then com.CompanyName " +
            " when 2 then ent.EnterpriseName " +
            " when 3 then cut.CustomerName " +
            " end as organizationName " +
            " from Employee em " +
            " left join Enterprise ent on ent.Id=em.OrgId " +
            " left join Customer cut on cut.Id=em.OrgId " +
            " left join Company com on com.Id=em.OrgId " +
            " where em.Id=#{id}")
    Employee findOneByLoginId(@Param("id") String id);

    @Insert("insert into Employee (OrgType,OrgId,Password,Mobile,Email,WeiXin,QQ,RealName,Status,LastLoginDatetime,Mark) values (#{orgType},#{orgId},#{password},#{mobile},#{email},#{weiXin},#{qQ},#{realName},#{status},#{lastLoginDatetime},#{mark})")
    int insertEmployee(Employee employee);

    @Update("update Employee set OrgType=#{orgType},OrgId=#{orgId},Password=#{password},Mobile=#{mobile},Email=#{email},WeiXin=#{weiXin},QQ=#{qQ},RealName=#{realName},Status=#{status},LastLoginDatetime=#{lastLoginDatetime},Mark=#{mark} where Id = #{id}")
    int updateEmployee(Employee employee);

    @Delete("delete from Employee where Id=#{id}")
    int deleteEmployeeById(Integer id);

    @Update("update Employee set Password=#{password} where Id = #{id}")
    int updateEmployeePass(Employee employee);
}
