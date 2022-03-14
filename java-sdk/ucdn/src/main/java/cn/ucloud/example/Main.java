package cn.ucloud.example;

import java.util.ArrayList;
import java.util.List;

import cn.ucloud.common.pojo.Account;
import cn.ucloud.ucdn.client.DefaultUcdnClient;
import cn.ucloud.ucdn.client.UcdnClient;
import cn.ucloud.ucdn.model.PrefetchNewUcdnDomainCacheParam;
import cn.ucloud.ucdn.model.PrefetchNewUcdnDomainCacheResult;
import cn.ucloud.ucdn.pojo.UcdnConfig;

public class Main {
    public static void main(String[] args) {
        UcdnClient client = new DefaultUcdnClient(new UcdnConfig(
                new Account("private key","public key")
        ));
		List<String> urlList = new ArrayList<>();
        urlList.add("...");
        PrefetchNewUcdnDomainCacheParam param = new PrefetchNewUcdnDomainCacheParam(urlList);
        PrefetchNewUcdnDomainCacheResult result = null;
        try {
            result = client.prefetchNewUcdnDomainCache(param);
            System.out.println("短信返回信息代码："+result.getRetCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
