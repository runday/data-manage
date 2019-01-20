package com.sdcsoft.datamanage.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdcsoft.datamanage.mapper.RoleMapper;
import com.sdcsoft.datamanage.mapper.RoleResourceMapper;
import com.sdcsoft.datamanage.model.Role;
import com.sdcsoft.datamanage.utils.Result;
import com.sdcsoft.datamanage.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    /**
     * 查询角色列表-分页
     * @param role
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/rolelistbyconditionandpage")
    public Result getRoleListByConditionAndPage(Role role, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return ResultGenerator.genSuccessResult(new PageInfo(roleMapper.getRoleListByCondition(role)));
    }

    /**
     * 查询角色列表-不分页
     * @param role
     * @return
     */
    @GetMapping(value = "/rolelistbycondition")
    public Result getRoleListByCondition(Role role) {
        return ResultGenerator.genSuccessResult(roleMapper.getRoleListByCondition(role));
    }


    /**
     * 通过用户id查询角色列表
     * @param employeeId
     * @return
     */
    @GetMapping(value = "/rolelistbyemployeeid")
    public Result getRoleListByEmployeeId(Integer employeeId) {
        return ResultGenerator.genSuccessResult(roleMapper.getRoleListByEmployeeId(employeeId));
    }

    /**
     * 编辑角色
     * @param role
     * @return
     */
    @PostMapping("/editrole")
    public Result editRole(@RequestBody Role role){
        if(role.getRoleId()!=null){
            roleMapper.updateRole(role);
        }else{
            roleMapper.insertRole(role);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 编辑角色资源
     * @param role
     * @return
     */
    @PostMapping("/editroleresource")
    public Result editRoleResource(@RequestBody Role role){
        roleResourceMapper.deleteRoleResourceByRoleId(role.getRoleId());
        if(null!=role.getRoleResourceList()&&role.getRoleResourceList().size()>0)
            roleResourceMapper.insertManyRoleResource(role.getRoleResourceList());
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @PostMapping(value = "/deleterolebyid")
    public Result deleteRoleById(@RequestParam int id){
        roleMapper.deleteRoleById(id);
        return ResultGenerator.genSuccessResult();
    }
}
