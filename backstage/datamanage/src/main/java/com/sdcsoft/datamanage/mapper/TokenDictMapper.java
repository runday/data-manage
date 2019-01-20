package com.sdcsoft.datamanage.mapper;

import com.sdcsoft.datamanage.model.TokenDict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TokenDictMapper {

    @Select("select Code,Name from Token_Dict where Type=2 and SUBSTR(Code, 1,2) = " +
            "(select Code from Token_Dict where Type =1 and orgId =#{enterpriseId} );")
    List<TokenDict> getCustomerListByEnterpriseId(@Param("enterpriseId") Integer enterpriseId);

    @Select("select Code,Name from Token_Dict where Type=#{organizationType}")
    List<TokenDict> getCustomerOrEnterpriseList(@Param("organizationType")Integer organizationType);
}
