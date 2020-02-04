package com.example.testdemo;

import com.aliyuncs.push.model.v20160801.ListSummaryAppsRequest;
import com.aliyuncs.push.model.v20160801.ListSummaryAppsResponse;
import org.junit.Test;

public class AppTest extends BaseTest {

    /**
     * APP概览列表
     * 参考文档 ：https://help.aliyun.com/document_detail/48069.html
     * */
    @Test
    public void testListSummaryApps() throws Exception {
        ListSummaryAppsRequest request = new ListSummaryAppsRequest();
        ListSummaryAppsResponse response = client.getAcsResponse(request);
        for (ListSummaryAppsResponse.SummaryAppInfo summaryAppInfo : response.getSummaryAppInfos()){
            System.out.println(summaryAppInfo.getAppKey() +" " +summaryAppInfo.getAppName());
        }
    }

}
