package com.sdcsoft.datamanage.mapper;

import com.sdcsoft.datamanage.model.Device;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeviceMapper {
    @Select("<script>" +
            "select * from Device "+
            "<where>"+
            " 1=1 "+
            "<if test='enterpriseId != null'> "+
            " AND EnterpriseId = #{enterpriseId} "+
            "</if>"+
            "<if test='customerNo != null and customerNo.length>0'> "+
            " AND SUBSTR(DeviceSuffix,1,5) = #{customerNo} "+
            "</if>"+
            "<if test='power != null'> "+
            " AND Power = #{power} "+
            "</if>"+
            "<if test='media != null'> "+
            " AND Media = #{media} "+
            "</if>"+
            "<if test='deviceSuffix != null and deviceSuffix.length>0 '> "+
            " AND DeviceSuffix LIKE CONCAT(CONCAT('%',#{deviceSuffix}),'%')"+
            "</if>"+
            "<if test='status != null'> "+
            " AND Status = #{status} "+
            "</if>"+
            "<if test='runStatus != null'> "+
            " AND RunStatus = #{runStatus} "+
            "</if>"+
            "<if test='saleDatetime != null'> "+
            " AND DATE_FORMAT(DATE_ADD(SaleDatetime,INTERVAL 0 DAY), '%Y-%m-%d')= DATE_FORMAT(DATE_ADD(#{saleDatetime},INTERVAL 0 DAY), '%Y-%m-%d')"+
            "</if>"+
            "<if test='importDatetime != null'> "+
            " AND DATE_FORMAT(DATE_ADD(ImportDatetime,INTERVAL 0 DAY), '%Y-%m-%d')= DATE_FORMAT(DATE_ADD(#{importDatetime},INTERVAL 0 DAY), '%Y-%m-%d')"+
            "</if>"+
            "</where>"+
            " order by importDatetime desc"+
            "</script>")
    List<Device> getDeviceListByCondition(Device device);

    @Select("<script>" +
            "select de.*,td.name as customerName from Device de"+
            " left join Enterprise ent on ent.Id=de.EnterpriseId "+
            " left join Token_Dict td  on td.code=SUBSTR(de.DeviceSuffix,1,5)"+
            "<where>"+
            " 1=1 "+
            "<if test='device.enterpriseId != null'> "+
            " AND de.EnterpriseId = #{device.enterpriseId} "+
            "</if>"+
            "<if test='device.power != null'> "+
            " AND de.Power = #{device.power} "+
            "</if>"+
            "<if test='device.media != null'> "+
            " AND de.Media = #{device.media} "+
            "</if>"+
            "<if test='device.customerNo != null and device.customerNo.length>0'> "+
            " AND SUBSTR(de.DeviceSuffix,1,5) = #{device.customerNo} "+
            "</if>"+
            "<if test='device.deviceSuffix != null and device.deviceSuffix.length>0 '> "+
            " AND de.DeviceSuffix LIKE CONCAT(CONCAT('%',#{device.deviceSuffix}),'%')"+
            "</if>"+
            "<if test='device.status != null'> "+
            " AND de.Status = #{device.status} "+
            "</if>"+
            "<if test='device.runStatus != null'> "+
            " AND de.RunStatus = #{device.runStatus} "+
            "</if>"+
            "<if test='device.saleDatetime != null'> "+
            " AND DATE_FORMAT(DATE_ADD(de.SaleDatetime,INTERVAL 0 DAY), '%Y-%m-%d')= DATE_FORMAT(DATE_ADD(#{device.saleDatetime},INTERVAL 0 DAY), '%Y-%m-%d')"+
            "</if>"+
            "</where>"+
            " order by de.DeviceSuffix, de.SaleDatetime desc"+
            "</script>")
    List<Device> getDeviceListByEnterpriseId(@Param("device") Device device);

    @Select("select DeviceNo,DeviceSuffix from Device where deviceSuffix between #{startSuffix} and #{endSuffix}")
    List<Device> getDeviceNoAndSuffixByStartSuffixAndEndSuffix(@Param("startSuffix") Long startSuffix,@Param("endSuffix") Long endSuffix);

    @Insert("insert into Device (EnterpriseId,DeviceNo,Media,Power,DevicePrefix,DeviceSuffix,DeviceType,ManufacturerId,Status,RunStatus,ImportDatetime,SaleDatetime) " +
            "values (#{enterpriseId},#{deviceNo},#{media},#{power},#{devicePrefix},#{deviceSuffix},#{deviceType},#{manufacturerId},#{status},#{runStatus},#{importDatetime},#{saleDatetime})")
    int insertDevice(Device device);


    @Insert("<script>"+
            "insert into Device(EnterpriseId,AreaId,DeviceNo,Media,Power,DevicePrefix,DeviceSuffix,DeviceType,ManufacturerId,Status,RunStatus,ImportDatetime,SaleDatetime)"
            + "values "
            + "<foreach collection =\"deviceList\" item=\"device\" index=\"index\" separator =\",\"> "
            + "(#{device.enterpriseId},#{device.areaId},#{device.deviceNo},#{device.media},#{device.power},#{device.devicePrefix},#{device.deviceSuffix},#{device.deviceType},#{device.manufacturerId},#{device.status},#{device.runStatus},#{device.importDatetime},#{device.saleDatetime}) "
            + "</foreach > " +
            "</script>")
    int insertManyDevice(@Param("deviceList") List<Device> deviceList);

    @Update("update Device set DeviceNo = #{deviceNo} where DeviceSuffix = #{deviceSuffix}")
    int updateDeviceNo(Device device);

    @Update("update Device set EnterpriseId=#{enterpriseId},DeviceNo=#{deviceNo},Media=#{media},Power=#{power},DevicePrefix=#{devicePrefix},DeviceSuffix=#{deviceSuffix}," +
            "DeviceType=#{deviceType},ManufacturerId=#{manufacturerId},Status=#{status},RunStatus=#{runStatus},ImportDatetime=#{importDatetime},SaleDatetime=#{saleDatetime} " +
            "where Id = #{id}")
    int updateDevice(Device device);

    @Delete("delete from Device where Id=#{id}")
    int deleteDeviceById(Integer id);
}
