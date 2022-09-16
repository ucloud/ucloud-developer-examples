package cn.ucloud.example;

import cn.ucloud.common.annotation.NotEmpty;
import cn.ucloud.common.annotation.UCloudParam;
import cn.ucloud.common.request.Request;

import java.util.List;

public class GetMetricParam extends Request {
    @UCloudParam("Region")
    private String region;

    @UCloudParam("Zone")
    private String zone;

    @UCloudParam("ResourceType")
    private String resourceType;

    @UCloudParam("ResourceId")
    private String resourceId;

    @UCloudParam("MetricName")
    private List<String> metricNameList;

    public GetMetricParam(String region, String zone, String resourceType, String resourceId, List<String> metricNameList) {
        super.setAction("GetMetric");
        this.region = region;
        this.zone = zone;
        this.resourceType = resourceType;
        this.resourceId = resourceId;
        this.metricNameList = metricNameList;
    }
}
