package com.sdcsoft.datamanage.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdcsoft.datamanage.mapper.ResourceMapper;
import com.sdcsoft.datamanage.model.Resource;
import com.sdcsoft.datamanage.utils.Result;
import com.sdcsoft.datamanage.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {
    
    @Autowired
    private ResourceMapper resourceMapper;
    /**
     * 查询资源列表
     * @param resource
     * @return
     */
    @GetMapping(value = "/resourcelistbycondition")
    public Result getResourceListByCondition(Resource resource) {
        return ResultGenerator.genSuccessResult(resourceMapper.getResourceListByCondition(resource));
    }

    /**
     * 通过角色Id获得角色资源列表
     * @param roleId
     * @return
     */
    @GetMapping(value = "/resourceidlistbyroleid")
    public Result getResourceResIdListByRoleId(Integer roleId) {
        return ResultGenerator.genSuccessResult(resourceMapper.getResourceIdListByRoleId(roleId));
    }

    /**
     * 编辑资源
     * @param resource
     * @return
     */
    @PostMapping("/editresource")
    public Result editResource(@RequestBody Resource resource){
        if(resource.getId()!=null){
            resourceMapper.updateResource(resource);
        }else{
            resourceMapper.insertResource(resource);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除资源
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteresourcebyid")
    public Result deleteResourceById(@RequestParam int id){
        resourceMapper.deleteResourceById(id);
        return ResultGenerator.genSuccessResult();
    }
}
