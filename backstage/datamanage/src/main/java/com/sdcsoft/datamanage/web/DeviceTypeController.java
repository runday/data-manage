package com.sdcsoft.datamanage.web;

import com.sdcsoft.datamanage.mapper.DeviceTypeMapper;
import com.sdcsoft.datamanage.mapper.TokenDictMapper;
import com.sdcsoft.datamanage.utils.Result;
import com.sdcsoft.datamanage.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/devicetype")
public class DeviceTypeController {
    
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    /**
     * 获得设备类型列表
     * @return
     */
    @GetMapping(value = "/getdevicetypelist")
    public Result getDeviceTypeList() {
        return ResultGenerator.genSuccessResult(deviceTypeMapper.getDeviceTypeList());
    }
}
