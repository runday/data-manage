package com.sdcsoft.datamanage.mapper;

import com.sdcsoft.datamanage.model.RoleResource;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleResourceMapper {

    String TABLE_NAME_ROLE_RESOURCE="Role_Resource_";

    @Insert("<script>"+
            "insert into "+TABLE_NAME_ROLE_RESOURCE+"(RoleId,ResourceId)"
            + "values "
            + "<foreach collection =\"roleResourcesList\" item=\"roleResources\" index=\"index\" separator =\",\"> "
            + "(#{roleResources.roleId},#{roleResources.resourceId}) "
            + "</foreach > " +
            "</script>")
    void insertManyRoleResource(@Param("roleResourcesList") List<RoleResource> roleResourcesList);

    @Delete("delete from "+TABLE_NAME_ROLE_RESOURCE+" where RoleId=#{roleId}")
    void deleteRoleResourceByRoleId(@Param("roleId") Integer roleId);
}
