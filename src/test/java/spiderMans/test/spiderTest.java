package spiderMans.test;

import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class spiderTest {
    public static void main(String[] args) {
        //        String resp=HttpClientUtil.doGet("https://www.jd.com/allSort.aspx");
        //        System.out.println(resp);
        //        Map<String, Integer> map=new HashMap<>();
        //        map.put("112.114.93.91", 8118);
        //        map.put("119.144.14.71", 8118);
        //        map.put("112.114.95.74", 8118);
        //        checkProxyIp(map, "http://www.douban.com");
        String s="https://www.douban.com/people/152912257/";
        System.out.println();
    }
    /**
     * 批量代理IP有效检测
     *
     * @param proxyIpMap
     * @param reqUrl
     */
    public static void checkProxyIp(Map<String, Integer> proxyIpMap, String reqUrl) {

        for (String proxyHost : proxyIpMap.keySet()) {
            Integer proxyPort = proxyIpMap.get(proxyHost);

            int statusCode = 0;
            try {
                HttpClient httpClient = new HttpClient();
                httpClient.getHostConfiguration().setProxy(proxyHost, proxyPort);

                // 连接超时时间（默认10秒 10000ms） 单位毫秒（ms）
                int connectionTimeout = 10000;
                // 读取数据超时时间（默认30秒 30000ms） 单位毫秒（ms）
                int soTimeout = 30000;
                httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
                httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

                HttpMethod method = new GetMethod(reqUrl);

                statusCode = httpClient.executeMethod(method);
            } catch (Exception e) {
                System.out.println("ip " + proxyHost + " is not aviable");
            }
            if(statusCode>0){
                System.out.format("%s:%s-->%sn", proxyHost, proxyPort,statusCode);
            }

        }
    }

}
