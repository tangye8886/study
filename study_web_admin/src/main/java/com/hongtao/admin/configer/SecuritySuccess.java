package com.hongtao.admin.configer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hongtao.admin.service.UserService;
import com.hongtao.common.entity.AjaxResponse;
import com.hongtao.common.entity.UserInfo;
import com.hongtao.common.utils.TokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecuritySuccess  extends SavedRequestAwareAuthenticationSuccessHandler {

    private static ObjectMapper objectMapper=new ObjectMapper();

    @Resource
    UserService userService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        MyUserDetails myUserDetails=(MyUserDetails)authentication.getPrincipal();
        UserInfo userInfo = userService.userLogin(myUserDetails.getUsername());
        Map<String,Object> map=new HashMap<>();
        map.put("userInfo",userInfo);
        try {
            String token = TokenUtils.makeToken(userInfo.getId(), userInfo.getUsername());
            map.put("token",token);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        response.getWriter().write(objectMapper.writeValueAsString(AjaxResponse.success(map)));
    }

}
