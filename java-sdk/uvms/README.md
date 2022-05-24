# UVMS Java Example

## pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>cn.ucloud</groupId>
    <artifactId>test-ucloud-java-sdk</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>cn.ucloud</groupId>
            <artifactId>ucloud-sdk-java-uvms</artifactId>
            <version>1.0.0-release</version>
        </dependency>
    </dependencies>

</project>
```

## Main

```java
package cn.ucloud.example;

import cn.ucloud.common.config.Config;
import cn.ucloud.common.credential.Credential;
import cn.ucloud.uvms.client.UVMSClient;
import cn.ucloud.uvms.models.SendUVMSMessageRequest;
import cn.ucloud.uvms.models.SendUVMSMessageResponse;

public class Main {
    public static void main(String []args) {
        Config config = new Config();
        config.setRegion("cn-bj2");

        String publicKey = "...";
        String privateKey = "...";

        Credential credential = new Credential(
                publicKey,
                privateKey
        );
        UVMSClient client = new UVMSClient(config, credential);
        SendUVMSMessageRequest param = new SendUVMSMessageRequest();

        // 在这里完善请求参数，详见文档：
        // https://docs.ucloud.cn/api/uvms-api/send_uvms_message?id=%e8%af%b7%e6%b1%82%e5%8f%82%e6%95%b0
        param.setProjectId("...");
        param.setCalledNumber("...");
        param.setFromNumber("...");

        try {
            SendUVMSMessageResponse result = client.sendUVMSMessage(param);

            // 返回参数列表见文档：
            // https://docs.ucloud.cn/api/uvms-api/send_uvms_message?id=%e5%93%8d%e5%ba%94%e5%ad%97%e6%ae%b5
            System.out.println("返回参数："+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```