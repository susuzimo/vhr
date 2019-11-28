package com.jianghu.vhr.controller.system.basic;

import com.jianghu.vhr.mapper.RoleMapper;
import com.jianghu.vhr.model.Menu;
import com.jianghu.vhr.model.Role;
import com.jianghu.vhr.service.system.basic.PermissService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    PermissService permissService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return permissService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return permissService.getAllMenus();
    }


    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid){
        return permissService.getMidsByRid(rid);
    }
}
