package com.example.testdemo;

import com.aliyuncs.push.model.v20160801.*;
import com.aliyuncs.utils.ParameterHelper;
import org.junit.Test;

import java.util.Date;

public class StatTest extends BaseTest {
    /**
     * 任务维度推送统计
     * 查询: 发送数,到达数,打开数,删除数
     * 参考文档 ：https://help.aliyun.com/document_detail/48097.html
     */
    @Test
    public void testQueryPushStatByMsg() throws Exception {
        QueryPushStatByMsgRequest request = new QueryPushStatByMsgRequest();
        request.setAppKey(appKey);
        request.setMessageId(500028L);//消息推送后返回的MessageID

        QueryPushStatByMsgResponse response = client.getAcsResponse(request);
        System.out.printf("RequestId: %s\n" , response.getRequestId());

        for (QueryPushStatByMsgResponse.PushStat item : response.getPushStats()) {
            System.out.printf("MessageId: %s , SentCount: %s, ReceivedCount: %s, OpenedCount: %s, DeletedCount: %s\n",
                    item.getMessageId(), item.getSentCount() ,item.getReceivedCount(),item.getOpenedCount(),item.getDeletedCount());
        }
    }

    /**
     * APP维度推送统计
     * 参考文档 ：https://help.aliyun.com/document_detail/48093.html
     */
    @Test
    public void testQueryPushStatByApp() throws Exception {

        QueryPushStatByAppRequest request = new QueryPushStatByAppRequest();
        request.setAppKey(appKey);
        request.setGranularity("DAY");//DAY: 天粒度

        Date startDate = new Date(System.currentTimeMillis() - 3 * 24 * 3600 * 1000);
        String startTime = ParameterHelper.getISO8601Time(startDate);
        Date endDate = new Date(System.currentTimeMillis());
        String endTime = ParameterHelper.getISO8601Time(endDate);

        request.setStartTime(startTime);
        request.setEndTime(endTime);

        QueryPushStatByAppResponse response = client.getAcsResponse(request);
        System.out.printf("RequestId: %s\n",response.getRequestId());

        for (QueryPushStatByAppResponse.AppPushStat item : response.getAppPushStats()) {
            System.out.printf("Time: %s , SentCount: %s, ReceivedCount: %s, OpenedCount: %s, DeletedCount: %s\n",
                    item.getTime(), item.getSentCount() ,item.getReceivedCount(),item.getOpenedCount(),item.getDeletedCount());
        }
    }

    /**
     * 设备新增与留存
     * 参考文档 ：https://help.aliyun.com/document_detail/48094.html
     */
    @Test
    public void testQueryDeviceStat() throws Exception {
        QueryDeviceStatRequest request = new QueryDeviceStatRequest();
        request.setAppKey(appKey);
        request.setQueryType("NEW");//NEW: 新增设备查询, TOTAL: 留存设备查询
        request.setDeviceType("iOS");//iOS,ANDROID,ALL

        Date startDate = new Date(System.currentTimeMillis() - 7 * 24 * 3600 * 1000);
        String startTime = ParameterHelper.getISO8601Time(startDate);
        Date endDate = new Date(System.currentTimeMillis());
        String endTime = ParameterHelper.getISO8601Time(endDate);


        request.setStartTime(startTime);
        request.setEndTime(endTime);

        QueryDeviceStatResponse response = client.getAcsResponse(request);
        System.out.printf("RequestId: %s\n",response.getRequestId());

        for (QueryDeviceStatResponse.AppDeviceStat stat : response.getAppDeviceStats()) {
            System.out.printf("Time: %s, DeviceType: %s, Count: %s\n",
                    stat.getTime(), stat.getDeviceType(), stat.getCount());
        }
    }

    /**
     * 去重设备统计
     * 参考文档 ：https://help.aliyun.com/document_detail/48092.html
     */
    @Test
    public void testQueryUniqueDeviceStat() throws Exception {

        QueryUniqueDeviceStatRequest request = new QueryUniqueDeviceStatRequest();
        request.setAppKey(appKey);
        request.setGranularity("DAY");//DAY: 天粒度 MONTH: 月粒度

        Date startDate = new Date(System.currentTimeMillis() - 7 * 24 * 3600 * 1000);
        String startTime = ParameterHelper.getISO8601Time(startDate);
        Date endDate = new Date(System.currentTimeMillis());
        String endTime = ParameterHelper.getISO8601Time(endDate);


        request.setStartTime(startTime);
        request.setEndTime(endTime);

        QueryUniqueDeviceStatResponse response = client.getAcsResponse(request);
        System.out.printf("RequestId: %s\n",response.getRequestId());

        for (QueryUniqueDeviceStatResponse.AppDeviceStat stat : response.getAppDeviceStats()) {
            System.out.printf("Time: %s, Count: %s\n",
                    stat.getTime(), stat.getCount());
        }
    }
}
