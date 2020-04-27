package com.hongtao.admin.configer;

import com.hongtao.admin.service.UserService;
import com.hongtao.common.entity.UserInfo;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyUserDeatilsService implements UserDetailsService {

    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetails myUserDetails=new MyUserDetails();
        UserInfo userInfo =userService.userLogin(username);
        if(userInfo!=null)
        {
            myUserDetails=new MyUserDetails();
            myUserDetails.setUsername(userInfo.getUsername());
            myUserDetails.setPassword(userInfo.getPassword());
            myUserDetails.setEnabled(userInfo.getStatus()==1?true:false);
            myUserDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",","")));
            myUserDetails.setAccountNonLocked(true);
            myUserDetails.setAccountNonExpired(true);
            myUserDetails.setCredentialsNoexpired(true);
        }
        return myUserDetails;
    }

}
