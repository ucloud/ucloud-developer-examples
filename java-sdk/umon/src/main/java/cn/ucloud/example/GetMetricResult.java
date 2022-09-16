package cn.ucloud.example;

import cn.ucloud.common.response.Response;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class GetMetricResult extends Response {
    @SerializedName("DataSets")
    public DataSets dataSets;
    public static class DataSets {
        @SerializedName("CPUUtilization")
        public List<MetricValue> cpuUtilization;
    }
    public static class MetricValue {
        @SerializedName("Timestamp")
        public String timestamp;
        @SerializedName("Value")
        public String value;
    }
}

