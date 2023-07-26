package cn.ucloud.example;

import cn.ucloud.common.config.Config;
import cn.ucloud.common.credential.Credential;
import cn.ucloud.sts.client.STSClient;
import cn.ucloud.sts.models.AssumeRoleRequest;
import cn.ucloud.sts.models.AssumeRoleResponse;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();

        Credential credential = new Credential(
                "public-key",
                "private-key"
        );

        STSClient client = new STSClient(config, credential);
        AssumeRoleRequest req = new AssumeRoleRequest();
        req.setRoleUrn("ucs:iam::xxx:role/test123"); // URN
        req.setRoleSessionName("test");
        AssumeRoleResponse resp = null;
        try {
            resp = client.assumeRole(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String accessKeyId = resp.getCredentials().getAccessKeyId();
        String accessKeySecret = resp.getCredentials().getAccessKeySecret();
        String securityToken = resp.getCredentials().getSecurityToken();
        System.out.printf("accessKeyId: %v, accessKeySecret: %v, securityToken: %v", accessKeyId, accessKeySecret, securityToken);
    }
}
