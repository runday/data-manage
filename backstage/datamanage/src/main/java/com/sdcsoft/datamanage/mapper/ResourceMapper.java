package com.sdcsoft.datamanage.mapper;

import com.sdcsoft.datamanage.model.Resource;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ResourceMapper {

    String TABLE_NAME_RESOURCE="Resource_";
    String TABLE_NAME_ROLE_RESOURCE="Role_Resource_";

    @Select("<script>" +
            "select * from "+TABLE_NAME_RESOURCE+" "+
            "<where>"+
            " 1=1 "+
            "</where>"+
            " order by Sort asc"+
            "</script>")
    List<Resource> getResourceListByCondition(Resource resource);

    @Select("select DISTINCT re.* from "+TABLE_NAME_RESOURCE+" re " +
            "LEFT JOIN "+TABLE_NAME_ROLE_RESOURCE+" rr on rr.ResourceId = re.Id " +
            "LEFT JOIN Employee_Role er on rr.RoleId = er.RoleId " +
            "LEFT JOIN Employee ee on ee.Id = er.EmployeeId " +
            "where ee.Id=#{employeeId} order by Sort asc")
    List<Resource> getResourceListByEmployeeId(Integer employeeId);

    @Select("<script>select re.id from "+TABLE_NAME_RESOURCE+" re LEFT JOIN "+TABLE_NAME_ROLE_RESOURCE+" rr on rr.ResourceId = re.Id where rr.RoleId=#{roleId} and re.ParentId<![CDATA[ <> ]]>0</script>")
    List<Resource> getResourceIdListByRoleId(@Param("roleId") Integer roleId);

    @Update("update "+TABLE_NAME_RESOURCE+" set Name=#{name},ParentId=#{parentId} ,RouteUrl=#{routeUrl},BindPageUrl=#{bindPageUrl},Hidden=#{hidden},Sort=#{sort},CreateTime=#{createTime} where Id=#{id}")
    int updateResource(Resource resource);

    @Insert("insert into "+TABLE_NAME_RESOURCE+" (Name,ParentId,RouteUrl,BindPageUrl,Hidden,Sort,CreateTime) values (#{name},#{parentId},#{routeUrl},#{bindPageUrl},#{hidden},#{sort},#{createTime})")
    int insertResource(Resource resource);

    @Delete("delete from "+TABLE_NAME_RESOURCE+" where Id=#{id}")
    int deleteResourceById(Integer id);
}
