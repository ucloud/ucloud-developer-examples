package cn.ucloud.example;

import cn.ucloud.common.client.DefaultClient;
import cn.ucloud.common.config.Config;
import cn.ucloud.common.credential.Credential;
import cn.ucloud.common.exception.RetCodeException;
import cn.ucloud.common.exception.UCloudException;
import cn.ucloud.common.exception.ValidatorException;
import cn.ucloud.common.request.Request;
import cn.ucloud.common.response.Response;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Config config = new Config();
        config.setLogger(logger);
        String publicKey = "publicKey";
        String privateKey = "privateKey";
        String region = "cn-bj2";
        String zone = "cn-bj2-04";
        String resourceType = "uhost";
        String resourceId = "uhost-xxx";

        Credential credential =
                new Credential(
                        publicKey, privateKey);
        DefaultClient client = new DefaultClient(config, credential);

        List<String> metricNameList = new ArrayList<String>();
        metricNameList.add("CPUUtilization");
        Request request = new GetMetricParam(region, zone, resourceType, resourceId, metricNameList);
        GetMetricResult getMetricResult = null;
        try {
            getMetricResult = (GetMetricResult) client.invoke(request, GetMetricResult.class);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(new Gson().toJson(getMetricResult));
    }
}