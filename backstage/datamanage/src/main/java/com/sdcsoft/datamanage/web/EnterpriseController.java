package com.sdcsoft.datamanage.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdcsoft.datamanage.mapper.EnterpriseMapper;
import com.sdcsoft.datamanage.model.Enterprise;
import com.sdcsoft.datamanage.utils.Result;
import com.sdcsoft.datamanage.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/enterprise")
public class EnterpriseController {
    @Autowired
    private EnterpriseMapper enterpriseMapper;

    /**
     * 查询字典列表-分页
     * @param enterprise
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/enterpriselistbyconditionandpage")
    public Result getEnterpriseListByConditionAndPage(Enterprise enterprise, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Enterprise> list =enterpriseMapper.getEnterpriseListByCondition(enterprise);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 查询字典列表-不分页
     * @param enterprise
     * @return
     */
    @GetMapping(value = "/enterpriselistbycondition")
    public Result getEnterpriseListByCondition(Enterprise enterprise) {
        return ResultGenerator.genSuccessResult(enterpriseMapper.getEnterpriseListByCondition(enterprise));
    }

    /**
     * 编辑字典
     * @param enterprise
     * @return
     */
    @PostMapping("/editenterprise")
    public Result editEnterprise(@RequestBody Enterprise enterprise){
        if(enterprise.getId()!=null){
            enterpriseMapper.updateEnterprise(enterprise);
        }else{
            enterpriseMapper.insertEnterprise(enterprise);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除字典
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteenterprisebyid")
    public Result deleteEnterpriseById(@RequestParam int id){
        enterpriseMapper.deleteEnterpriseById(id);
        return ResultGenerator.genSuccessResult();
    }
}
