package com.sdcsoft.datamanage.service.impl;

import com.sdcsoft.datamanage.mapper.CustomerMapper;
import com.sdcsoft.datamanage.mapper.DeviceMapper;
import com.sdcsoft.datamanage.model.Device;
import com.sdcsoft.datamanage.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    /**
     * 通过条件查询设备列表
     * @param device
     * @return
     */
    @Override
    public List<Device> getDeviceListByCondition(Device device) {
        return deviceMapper.getDeviceListByCondition(device);
    }

    /**
     * 获取设备数据列表
     * @param device
     * @return
     */
    @Override
    public List<Device> getDeviceListByEnterpriseId(Device device) {
        return deviceMapper.getDeviceListByEnterpriseId(device);
    }

    /**
     * 加密多个设备编号
     * @param deviceList
     * @return
     */
    @Override
    public int updateManyDeviceNo(List<Device> deviceList) {
        try{
            int updateNum=0;
            for (int i=0;i<deviceList.size();i++){
                updateNum+=deviceMapper.updateDeviceNo(deviceList.get(i));
            }
            return updateNum;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
