/**  
 * @Title: FjydLiveTest.java  
 * @Package com.fzs.business.modules.api.controller  
 * @Description: TODO 
 * @author superman  
 * @date 2018年2月4日  
 * @version V1.0  
 */ 
package spiderMans.test;

import org.junit.Test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageGetRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageGetResponse;

/**  
 * @ClassName: FjydLiveTest  
 * @Description: 福建移动生活相关工作测试开发   
 * @author 文泊山  
 * @date 2018年2月4日    
 */
public class FjydLiveTest {
    private String appid="2014052200006095";
    //    private String url="https://openapi.alipaydev.com/gateway.do";
    private String queryUrl="alipay.open.public.setting.category.query";
    //    private String uid="2088102175314848";
    private String alipay_private_key="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCXdQdI85b1TnBuRhoCBqeA6k+8Kg/Eb3bBS0IvkFeUoENUSdqsENiJtbZOVPlrqkL74YxPYkqgT3rD0xGoE7dgkFd7JGDqBjwyomUJKSlL1oGEVxlhqJAwpu4RtY6mB6GA8aiBztlRERfL23mHhHx4R0fPtWNHSoabeuuSy4boYu507LPgX4JkxymIwkJqSbqJ14UhrFUAttQBXPeIrRwDfD8oV+a9dHr+iWv5xPx97qilIAa9YDDkhz3INf/1YuFmIqycLgqlJ5614LF8bCMpN9M/5YE8oq1gks2+ZgzyXaVvDqDZRuw9nswOhAA1Bj929ccnY+wolNvxaDD5BZg9AgMBAAECggEBAIGS+XyMCWYsiTuOdcmt77f2veh5A6xGwA52WFVg48yfn3qJBK+YaejGsQUq5Ygazdu4BtYTfUzRMnI+LSjiVUwjsPQFF3SfiPi4vj+sG0xqY7vY2DoYf+NfrOUxsqyQcrdiB4umb31PZlBdoAc0bVhpgyJMSHdBZw+1hby8qto8DmfSGd60zZtPI581pmjSRH5RPFRzQl3lCT/ov2zyq2iMwOrNPkH5KqdnqB7jed3RNVl0zsHIh9JNv+OH2uVzpWx/LHgSBLvAIsWmT+e1S7hd8vrrhT78S/tszoBlGcrUHq/1m4tHdsr/0WN9LmEizMa4G1jY/y+2zfBi/9jnZoECgYEA+wIVI7Q0YOQyrhUqC1c5VsJdFBXPro2vhkVWTG0isR66OHkdUvGjISW/b3MOU9nCrv6ylpLdZeDEEog7F0e5cUuQ+4ZNPd+bHQlOFjQopMC0f6mP0Z1ii2aqsCO51pgbGhiJkwWebXmvBWxGJ3On0p0SVzpS0r6ccIkekFgM3tUCgYEAmngeLji0DRn0K3b8XW9UDNeULlvESsLXySxkeRN1abEW8eJktNtTfnsa0rDsIYWQ+fLLtqt0aYwqYmsQoaU/wH/NGT7xA6VBRHutHj2qUfJ8hGbWjYM6Kk4+fk3bq1Sy97tQmnhhrZBGgfdNKUl2nZubEVEKMgxXqAcaYO0Pl8kCgYEAwfHT/6hnqE3qUtQLjoOwzDaV0MUhmU/kxayhh5/z5ENHxAbg/4uxbhVKm4SdF3D0ml42A14rL2LCXUdh0RUcVkv7FD2rRRmXMpJUAZf/p302ekW72EzxLaEAVeTk3MjeeAVpl24KAbC56UjF1AnrLDMtTrTD9g5Nti5crU87cpkCgYAb8IoU9jCrBfzCN91h0Rc003F8CSEyHJgHTT5HIBvmqB9sVPS+OcWaZQ3u+g1b6trUOvRZ8B04UiAAeTki487qNLOWU2oorKVni68+fwzlkaSwFiHMquXEbS6kjdSj4sgUQw9LQWb5i9UVAPQ1+ZG45EJiGXRspQrd25sL2RUu8QKBgQD54G/vKmsTgip1taMwib2rfpG7LfXLRvN1/Ewp8TeNGoM0YRZIYw5EZv3PrvtwRAmYgoMJV2wesEzhvCZ1bmiYEODvpwbqDw4tLkmDeVhqtlIVqLuqHp4kAn6xC0UmXzCyKf3OlygU5TVWfmf9aBr7aA9JWEoTJbVS7Npxm4VW8w==";
    private String alipay_public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl3UHSPOW9U5wbkYaAgangOpPvCoPxG92wUtCL5BXlKBDVEnarBDYibW2TlT5a6pC++GMT2JKoE96w9MRqBO3YJBXeyRg6gY8MqJlCSkpS9aBhFcZYaiQMKbuEbWOpgehgPGogc7ZUREXy9t5h4R8eEdHz7VjR0qGm3rrksuG6GLudOyz4F+CZMcpiMJCakm6ideFIaxVALbUAVz3iK0cA3w/KFfmvXR6/olr+cT8fe6opSAGvWAw5Ic9yDX/9WLhZiKsnC4KpSeeteCxfGwjKTfTP+WBPKKtYJLNvmYM8l2lbw6g2UbsPZ7MDoQANQY/dvXHJ2PsKJTb8Wgw+QWYPQIDAQAB";
    @Test
    public void zhifubaoQueryTest() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(queryUrl,appid,alipay_private_key,"json","GBK",alipay_public_key,"RSA2");
        AlipayOpenPublicTemplateMessageGetRequest request = new AlipayOpenPublicTemplateMessageGetRequest();
        //        request.setBizContent("{" +
        //                "\"template_id\":\"TM000000223\"" +
        //                "  }");
        AlipayOpenPublicTemplateMessageGetResponse response = alipayClient.execute(request);
        System.out.println(response.getMsg());
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
