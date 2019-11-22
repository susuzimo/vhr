package com.jianghu.vhr.config;




import com.jianghu.vhr.model.Menu;
import com.jianghu.vhr.model.Role;
import com.jianghu.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.logging.Filter;

/*
根据用户传来的地址，分析出请求需要的角色
 */
@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;
    AntPathMatcher antPathMatcher=new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
       String url=((FilterInvocation)object).getRequestUrl();
       List<Menu> menus= menuService.getAllMenusWithRole();
       for(Menu menu:menus){
           if(antPathMatcher.match(menu.getUrl(),url)){
               List<Role> roles = menu.getRoles();
              String[] str=new String[roles.size()];
              for(int i=0;i<roles.size();i++){
                  str[i]=roles.get(i).getName();
               }
               return SecurityConfig.createList(str);
           }
       }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
