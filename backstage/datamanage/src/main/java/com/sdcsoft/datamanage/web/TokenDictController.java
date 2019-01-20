package com.sdcsoft.datamanage.web;

import com.sdcsoft.datamanage.mapper.TokenDictMapper;
import com.sdcsoft.datamanage.utils.Result;
import com.sdcsoft.datamanage.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tokendict")
public class TokenDictController {
    
    @Autowired
    private TokenDictMapper tokenDictMapper;

    /**
     * 通过企业Id获得客户列表
     * @param enterpriseId
     * @return
     * */
    @GetMapping(value = "/getcustomerlistbyenterpriseid")
    public Result getCustomerListByEnterpriseId(@RequestParam("enterpriseId") Integer enterpriseId) {
        return ResultGenerator.genSuccessResult(tokenDictMapper.getCustomerListByEnterpriseId(enterpriseId));
    }

    /**
     * 通过类型获得客户列表或者企业列表
     * @param organizationType
     * @return
     * */
    @GetMapping(value = "/getcustomerorenterpriselist")
    public Result getCustomerOrEnterpriseList(@RequestParam("organizationType") Integer organizationType) {
        return ResultGenerator.genSuccessResult(tokenDictMapper.getCustomerOrEnterpriseList(organizationType));
    }
}
