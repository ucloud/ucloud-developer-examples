package cn.ucloud.example;

import cn.ucloud.common.config.Config;
import cn.ucloud.common.credential.Credential;
import cn.ucloud.usms.client.USMSClient;
import cn.ucloud.usms.models.SendUSMSMessageRequest;
import cn.ucloud.usms.models.SendUSMSMessageResponse;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();

        Credential credential = new Credential(
                "...",
                "..."
        );

        USMSClient client = new USMSClient(config, credential);

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("...");
        phoneNumbers.add("...");

        SendUSMSMessageRequest req = new SendUSMSMessageRequest();
        req.setSigContent("...");
        req.setProjectId("...");
        req.setTemplateId("template-id");
        req.setPhoneNumbers(phoneNumbers);

        List<String> templateParams = new ArrayList<>();
        templateParams.add("424242");
        req.setTemplateParams(templateParams);

        SendUSMSMessageResponse resp = null;
        try {
            resp = client.sendUSMSMessage(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(resp);
    }
}
