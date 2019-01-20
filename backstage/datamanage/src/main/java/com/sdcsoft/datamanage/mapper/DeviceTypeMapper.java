package com.sdcsoft.datamanage.mapper;

import com.sdcsoft.datamanage.model.DeviceType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeviceTypeMapper {

    @Select("select * from DeviceType ")
    List<DeviceType> getDeviceTypeList();
}
