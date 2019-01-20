package com.sdcsoft.datamanage.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sdcsoft.datamanage.mapper.DeviceMapper;
import com.sdcsoft.datamanage.model.Device;
import com.sdcsoft.datamanage.service.DeviceService;
import com.sdcsoft.datamanage.utils.Result;
import com.sdcsoft.datamanage.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    
    @Autowired
    private DeviceMapper deviceMapper;

    /**
     * 查询设备列表-分页
     * @param device
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/devicelistbyconditionandpage")
    public Result getDeviceListByConditionAndPage(Device device, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return ResultGenerator.genSuccessResult(new PageInfo(deviceService.getDeviceListByCondition(device)));
    }

    /**
     * 查询设备列表-分页
     * @param device
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getdevicelistbyenterpriseidandpage")
    public Result getDeviceListByEnterpriseIdAndPage(Device device, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return ResultGenerator.genSuccessResult(new PageInfo(deviceService.getDeviceListByEnterpriseId(device)));
    }

    /**
     * 批量插入设备数据
     * @param deviceList
     * @return
     */
    @PostMapping("/insertmanydevice")
    public Result insertManyDevice(@RequestBody List<Device> deviceList){
        return ResultGenerator.genSuccessResult("成功添加"+deviceMapper.insertManyDevice(deviceList)+"个设备");
    }

    /**
     * 批量更新加密的设备编号
     * @param deviceList
     * @return
     */
    @PostMapping("/updatemanydeviceno")
    public Result updateManyDeviceNo(@RequestBody List<Device> deviceList){
        return ResultGenerator.genSuccessResult("成功加密"+deviceService.updateManyDeviceNo(deviceList)+"个设备");
    }

    /**
     * 编辑设备
     * @param device
     * @return
     */
    @PostMapping("/editdevice")
    public Result editDevice(@RequestBody Device device){
        if(device.getId()!=null){
            deviceMapper.updateDevice(device);
        }else{
            deviceMapper.insertDevice(device);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除设备
     * @param id
     * @return
     */
    @PostMapping(value = "/deletedevicebyid")
    public Result deleteDeviceById(@RequestParam int id){
        deviceMapper.deleteDeviceById(id);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/generateqrcode")
    public void generateQRCode(Long startSuffix, Long endSuffix, @RequestParam(name = "width",defaultValue = "200",required = false) int width,
                               @RequestParam(name = "height",defaultValue = "200",required = false) int height, HttpServletRequest request, HttpServletResponse response) throws WriterException, IOException {
        MatrixToImageConfig DEFAULT_CONFIG = new MatrixToImageConfig();
        int index = 0;
        List<Device> list = deviceMapper.getDeviceNoAndSuffixByStartSuffixAndEndSuffix(startSuffix,endSuffix);
        BufferedImage image2 = new BufferedImage(2440, 265+list.size()/11*265, BufferedImage.TYPE_INT_RGB);
        //获取图形上下文,graphics想象成一个画笔
        Graphics2D graphics = (Graphics2D) image2.getGraphics();
        graphics.fillRect(0, 0, 2440, 265+list.size()/11*265);
        List<String> suffixList = new ArrayList<>();
        for (int a = 0; a < list.size(); a++) {
            int row = a/11;
            Device device = list.get(a);
            String content = "http://www.sdcsoft.com.cn/app/gl/gl.apk?id=" + device.getDeviceNo();
            Map<EncodeHintType, Object> config = new HashMap<>();
            config.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            config.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            config.put(EncodeHintType.MARGIN, 0);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, config);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix, DEFAULT_CONFIG);
            //消除线条锯齿
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.drawImage(image, 200 * (index-row*11) + 20 * (index-row*11) + 20,  20+row *200+65*row, 200, 200, null);
            graphics.setColor(Color.BLACK);
            graphics.drawString(device.getDeviceNo(), 70 + 200 * (index-row*11) + 20 * (index-row*11) + 20, 235+row *200+65*row);
            graphics.drawString( device.getDeviceNo()+"-"+device.getDeviceSuffix().substring(5),20 + 200 * (index-row*11) + 20 * (index-row*11) + 20, 265+row *200+65*row);
            index++;
            suffixList.add(device.getDeviceSuffix());
        }
        //释放此图形的上下文并释放它所使用的所有系统资源
        graphics.dispose();
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image2, "JPEG", os);
        os.flush();
        os.close();
    }
}
