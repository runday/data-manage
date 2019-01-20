package com.sdcsoft.datamanage.mapper;

import com.sdcsoft.datamanage.model.Customer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerMapper {

    @Select("<script>" +
            "select * from Customer "+
            "<where>"+
            " 1=1 "+
            "<if test='customerName != null and customerName.length>0 '> "+
            " AND CustomerName LIKE CONCAT(CONCAT('%',#{customerName}),'%')"+
            "</if>"+
            "</where>"+
            "</script>")
    List<Customer> getCustomerListByCondition(Customer customer);

    @Select("select cus.CustomerNo from Customer cus where cus.Id=#{id}")
    String getCustomerNoById(Integer id);

    @Update("update Customer set EnterpriseId=#{enterpriseId},CustomerName=#{customerName},Status=#{status},CustomerNo=#{customerNo} where Id = #{id}")
    void updateCustomer(Customer customer);

    @Insert("insert into Customer (EnterpriseId,CustomerName,Status,CustomerNo) values (#{enterpriseId},#{customerName},#{status},#{customerNo})")
    void insertCustomer(Customer customer);

    @Delete("delete from Customer where Id=#{id}")
    void deleteCustomerById(Integer id);
}
