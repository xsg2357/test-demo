package com.example.testdemo;

import com.aliyuncs.push.model.v20160801.CheckDevicesRequest;
import com.aliyuncs.push.model.v20160801.CheckDevicesResponse;
import com.aliyuncs.push.model.v20160801.QueryDeviceInfoRequest;
import com.aliyuncs.push.model.v20160801.QueryDeviceInfoResponse;
import org.junit.Test;

public class DeviceTest extends BaseTest {
    /**
     * 查询设备信息
     * 参考文档：https://help.aliyun.com/document_detail/48098.html
     */
    @Test
    public void testQueryDeviceInfo() throws Exception {

        QueryDeviceInfoRequest request = new QueryDeviceInfoRequest();
        request.setAppKey(appKey);
        request.setDeviceId(deviceIds);

        QueryDeviceInfoResponse response = client.getAcsResponse(request);
        System.out.printf("RequestId: %s\n", response.getRequestId());
        QueryDeviceInfoResponse.DeviceInfo deviceInfo = response.getDeviceInfo();
//        System.out.print(JSON.toJSONString(deviceInfo));

    }

    @Test
    public void testCheckDevices() throws Exception {
        CheckDevicesRequest request = new CheckDevicesRequest();
        request.setAppKey(appKey);
        request.setDeviceIds(deviceIds);
        CheckDevicesResponse response = client.getAcsResponse(request);
//        System.out.print(JSON.toJSONString(response));
    }
}
