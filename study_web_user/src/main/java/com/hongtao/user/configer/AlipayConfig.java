package com.hongtao.user.configer;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092800612701";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCSa4OvN1q+nrdbFivOf09wyuqQOGtrVVy/P0shnWO1ejZ9+1A+lpu/HXv+SrIaZ8qd/9W6pApVF2R2JvzmuZY6JeCBJXv9046YAIvKInevwL6IyUdshjN5DHKtMVv8zGtcstvj5oafSkTZ3ENI1FK6TLOdiTXfJ7AwMaiiU+g+aIig09/R+ECU6YAzKjB6rUt1mHy0qi1CYDu9XFJBVvfFsfhDgSc/I62lN+C5nozUsFxMHd8BKoLvjd17FJ/jD6+B6ZzQialz24kUEm4gMFEAK2vGRaSH6e++LFdk7kmJGCTdtlLxQxRx2+7KbhtVeAPrvOlpheL2wuWZ0GT7clI5AgMBAAECggEALxABxqrrLwQC+XMZ54jCNwXx+SM5W8mXQx2L/6UALI+4viuuMj/klbxbY3QM5THhTYGy6AfcDlezgcqsVVjnsY8FJ12uCfsbBT5/sBv+Dm7Mj1jq7TYBi3H11jdcUtg91hDjXtB1CvQrRz9mHR2p7ZnDzeoRd8Xg+syrYGnJ52zidOKOxt7b2RaVaMPkl9YtItI3qXSGzyhjuoMG66KzKiFuOHibTAjrv6hcXEmE7YlodbqpvktQUP7z1wqWN+U0dgYfCCopCEvo2/x86X4S0+kZfXtONGm+t47IuZNsFoTzd8hR+Z97MWnuU9RETDYyu6Fcad2Rp/Q5QK8Cgk/VlQKBgQDezXD5Z6xntCbmcPKwDI86nq6Vyf2UmHyXBw2NFm3+7DFqPjF6KexrRwF93T2UdCXRnXVHoPa39Qh1+Ra+4sq5K1qrqnbFXOJsBQbmRlbNRKd+lOypdcb11qLHuWxrnH2sOEMUKtG81eSh2+KHIRQUoS3DyyDRsPMWFdcSIpMwNwKBgQCoPIt7iarQJAVf4euBZ9lwXGuzarbmFMiLPa5up89CUNLpEjg8biK8VtizFg7HHvK3UWuaPpAAWaaCvg7gqsTUkAKQSXXo+1aUSq/jHoN0zxgvwlJQCPABaUZzHlZ/VtfeIDusUl1/s+r005U9KPkJ8c5Bl4Q4l87+bTuUI/v5DwKBgHcZiQ+5bL7O3ZCHxiN3w0TJuHfXzxcU4Fz3ZzYJTGleJxgtANPV/0xlW5XFo3ptEB5aNmxAzkIN2ST/LzqBD28mjsR/Thg4i8zEbyi/Zv05evO+djgr/lxCDSgIpb1snP1n4euw7Acuq5YebZxggVR5yHYGapfNKFSg/iPymY3HAoGAPprpPPup+PKunVOxE+LVY57A5W9cyJoLVuWJN9xyoDb7hYgmLTa+7l9THLGkpiy6HcXKyQdnBvpVoGmhl7F0tbUxfwgXPK/rNBvnABmmyZ0XXw5tGyN4TM4kTUqI3bIvepV8H1A3QwJs+NaTvsK0iiLVoksEBRtZCA0WErahWbkCgYEAy6yWuReoth6BwxBowCisDOBOTOPT2SmioycY0eJEV/4loSZ7DUaBgXQ1SMTGt6UbnF7VRv3WNGrsC5VNe9/KnpAhcWPn2WJvk8Dpkrj89KZfy4ldtnLek0qL6Hernl7inESqFtPLTkONdcGryEJMQ5TAWjprWfzKm2mPVadBhG0=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7FIkrKLJZQfCCBR4vbtjP/thP848aFbeSPS/J7J0dYvazKA2fjt5JUXY3sgPXy+foDbqhnaNhgWZr1/jpOKnL+u8Y00tEoSdzhmbt8wdzOLaZqMRi1wQ9+OdZoSVk9AKKdMZTrSDOCh6vtOHOKWtAp16VFCmOx4fEX/Tg1wBEhUFshReqnHPDBOV/d0t+ib0siqjp67iOjnJStnxWj5KMwSUr2ewruI0I4FXb8GtvNm436w7BI7eGn7eFuOH9AEGmhv+prQRJ6shV2A/7kbhSJIgSgA63S9T6e+x0GmXUDk36ASRrhVyocWXTn+9g9FtzNaK+eik9/Rmvbe7j/nHZwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://127.0.0.1:9200/study-user/api/user/pay/payResult";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://127.0.0.1:9200/study-user/api/user/pay/payResult";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
