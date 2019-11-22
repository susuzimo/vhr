package com.jianghu.vhr.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyDecisionManager implements AccessDecisionManager {
    /*
    authentication 用户登录时的信息
    collection 需要的角色
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        for(ConfigAttribute configAttribute :collection){
            String attribute = configAttribute.getAttribute();
            if("ROLE_LOGIN".equals(attribute)){
                if(authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("尚未登录，请登录");
                }else{
                    return;
                }
            }
            //获取当前用户的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for(GrantedAuthority authority:authorities){
                if(authority.getAuthority().equals(attribute)){
                    return;
                }
            }

        }
        throw  new AccessDeniedException("权限不足，请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
