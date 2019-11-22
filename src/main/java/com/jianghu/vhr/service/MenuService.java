package com.jianghu.vhr.service;

import com.jianghu.vhr.mapper.MenuMapper;
import com.jianghu.vhr.model.Hr;
import com.jianghu.vhr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;
    public List<Menu> getMenuById() {
        return menuMapper.getMenuById(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    //@Cacheable
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }
}
