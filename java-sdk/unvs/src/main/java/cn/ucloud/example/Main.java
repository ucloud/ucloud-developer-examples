package cn.ucloud.example;

import javax.validation.constraints.NotEmpty;

import com.google.gson.annotations.SerializedName;

import cn.ucloud.common.pojo.Account;
import cn.ucloud.common.client.UcloudClient;
import cn.ucloud.common.pojo.UcloudConfig;
import cn.ucloud.common.client.DefaultUcloudClient;
import cn.ucloud.common.annotation.UcloudParam;
import cn.ucloud.common.pojo.BaseRequestParam;
import cn.ucloud.common.pojo.BaseResponseResult;


class GetMobileParam extends BaseRequestParam {
    @UcloudParam("BusinessId")
    private String businessId;

    @UcloudParam("Token")
    private String token;

    public GetMobileParam(
            @NotEmpty(message = "businessId can not be empty") String businessId,
            @NotEmpty(message = "token can not be empty") String token
    ) {
        super("GetMobile");
        this.businessId = businessId;
        this.token = token;
    }
}


class GetMobileResult extends BaseResponseResult {
    @SerializedName("Data")
    private PhoneInfo data;

    public class PhoneInfo {
        @SerializedName("Phone")
        private String phone;
    }
}


public class Main {
    public static void main(String[] args) {
        UcloudClient client = new DefaultUcloudClient(new UcloudConfig(
                new Account(
                        System.getenv("UCLOUD_PRIVATE_KEY"),
                        System.getenv("UCLOUD_PUBLIC_KEY")
                )
        ));

        GetMobileParam param = new GetMobileParam(
                "...",
                "...."
        );

        GetMobileResult result = null;
        try {
            result = (GetMobileResult) client.doAction(param, GetMobileResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
