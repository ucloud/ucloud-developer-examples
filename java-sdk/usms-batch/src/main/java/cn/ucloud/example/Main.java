package cn.ucloud.example;

import cn.ucloud.common.config.Config;
import cn.ucloud.common.credential.Credential;
import cn.ucloud.usms.client.USMSClient;
import cn.ucloud.usms.models.SendBatchUSMSMessageRequest;
import cn.ucloud.usms.models.SendBatchUSMSMessageResponse;

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

        SendBatchUSMSMessageRequest req = new SendBatchUSMSMessageRequest();
        // 批量发送内容，该参数是json数组的base64编码结果。发送内容json数组中，每个“模板+签名”组合作为一个子项，每个子项内支持多个号码，
        // 示例： 发送内容json数组（base64编码前）：
        // [{"TemplateId": "UTA20212831C85C", "SigContent": "UCloud", "Target": [{"TemplateParams": ["123456"], "Phone": "18500000123", "ExtendCode": "123", "UserId": "456"} ] } ] 。
        // json数组中各参数的定义："TemplateId":模板ID，"SigContent"短信签名内容，
        // "Target"具体到号码粒度的发送内容。
        // "Target"中的具体字段有：
        // "TemplateParams"实际发送的模板参数（若使用的是无参数模板，该参数不能传值），"Phone"手机号码, "ExtendCode"短信扩展码, "UserId"自定义业务标识ID。其中必传参数为"TemplateId", "SigContent", "Target"（"Target"中必传参数为"Phone"）。
        // 实际调用本接口时TaskContent传值（发送内容base64编码后）为：W3siVGVtcGxhdGVJZCI6ICJVVEEyMDIxMjgzMUM4NUMiLCAiU2lnQ29udGVudCI6ICJVQ2xvdWQiLCAiVGFyZ2V0IjogW3siVGVtcGxhdGVQYXJhbXMiOiBbIjEyMzQ1NiJdLCAiUGhvbmUiOiAiMTg1MDAwMDAxMjMiLCAiRXh0ZW5kQ29kZSI6ICIxMjMiLCAiVXNlcklkIjogIjQ1NiJ9IF0gfSBdIA==
        req.setTaskContent("...");
        req.setProjectId("...");

        SendBatchUSMSMessageResponse resp = null;
        try {
            resp = client.sendBatchUSMSMessage(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(resp);
    }
}
