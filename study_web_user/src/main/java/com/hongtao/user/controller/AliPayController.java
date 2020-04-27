package com.hongtao.user.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.hongtao.common.entity.CourseOrder;
import com.hongtao.common.entity.UserInfo;
import com.hongtao.user.configer.AlipayConfig;
import com.hongtao.user.service.OrderService;
import com.hongtao.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("api/user/pay")
public class AliPayController {

    @Resource
    OrderService orderService;

    @Resource
    UserService userService;

    /**
     * 结算界面
     * @return
     */
    @RequestMapping("addVip")
    public String  addVip (@RequestParam("uid") String uid,HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        String orderNo=getOrderIdByTime();
        //创建订单记录
        CourseOrder order=new CourseOrder();
        order.setId(orderNo);order.setUid(uid);
        order.setAddress("加入会员，无需填写");
        order.setPerson("加入会员，无需填写");
        order.setPhone("加入会员，无需填写");order.setStatus(1);order.setCount(1);
        order.setPrice(188.00);
        orderService.insert(order);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = orderNo;
        //付款金额，必填  ShopName
        String total_amount = "188";
        //订单名称，必填
        String subject = "加入THT云课堂会员费用";
        //商品描述，可空
        String body = "欢迎加入THT云课堂会员";
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String url = "";
        try {
            url = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return url;
    }


    @RequestMapping("buyCourse")
    public String  buyCourse (@RequestParam("uid")String uid,@RequestParam("cid") String cid,@RequestParam("price") double price,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        String orderNo=getOrderIdByTime();
        //创建订单记录
        CourseOrder order=new CourseOrder();
        order.setId(orderNo);
        order.setUid(uid);
        order.setCid(cid);
        order.setAddress("购买课程，无需填写");
        order.setPerson("购买课程，无需填写");
        order.setPhone("购买课程，无需填写");
        order.setStatus(1);
        order.setCount(1);
        order.setPrice(price);
        orderService.insert(order);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = orderNo;
        //付款金额，必填  ShopName
        String total_amount = price+"";
        //订单名称，必填
        String subject = "购买课程费用";
        //商品描述，可空
        String body = "感谢您对课程的支持。";
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String url = "";
        try {
            url = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return url;
    }


    @ResponseBody
    @RequestMapping(value = "payResult")
    public void payResult(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        Map<String,Object> resultMap=new HashMap<String,Object>();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {  //支付成功
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            CourseOrder order = orderService.query2(out_trade_no);
            //更新订单状态
            order.setPaystatus(1); order.setPaytime(new Date());
            orderService.update(order);

            System.out.println(order.getUid());
            //更新用户的的角色
            UserInfo userInfo=new UserInfo();
            userInfo.setId(order.getUid());
            userInfo.setRole(3);
            userService.update(userInfo);
            response.sendRedirect("127.0.0.1:8080/userInfo?tab=first");
        }else {
            response.sendRedirect("127.0.0.1:8080/userInfo?tab=first");
        }
        //return "加入成功";
    }

    public static String getOrderIdByTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;
    }
}
