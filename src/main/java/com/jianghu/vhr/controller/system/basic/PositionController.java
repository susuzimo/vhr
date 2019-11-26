package com.jianghu.vhr.controller.system.basic;

import com.jianghu.vhr.model.Position;
import com.jianghu.vhr.model.RespBean;
import com.jianghu.vhr.service.system.basic.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    PositionService positionService;
    @GetMapping("/")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions() ;
    }

    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        if(positionService.addPosition(position)==1){
            return RespBean.ok("新增成功");
        }
        return RespBean.error("新增失败");
    }



    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if(positionService.updatePosition(position)==1){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }


    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        if(positionService.deletePosition(id)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer [] ids){
        if(positionService.deletePositionByIds(ids)==ids.length){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
