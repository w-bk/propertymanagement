package org.wbk.propertymanagement.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wbk.propertymanagement.entity.PaymentInfo;
import org.wbk.propertymanagement.entity.Repair;
import org.wbk.propertymanagement.response.ServerResponse;
import org.wbk.propertymanagement.service.IRepairService;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王宝凯
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    private IRepairService iRepairService;

    /**
     * @Description 维修管理列表
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @GetMapping(value = "/repairList")
    @ResponseBody
    public Map repairList(Integer page, Integer limit, Integer repairState){
        Map<String,Object> map = new HashMap<>();
        IPage<Repair> repairList = iRepairService.repairList(page,limit,repairState);
        map.put("code",0);
        map.put("msg","");
        map.put("count",repairList.getTotal());//总条数
        map.put("data",repairList.getRecords());//数据
        return map;
    }

    /**
     * @Description 根据前端传过来的实体类 获得对象 给页面赋值
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @PostMapping(value = "/repairInfoSelectById")
    @ResponseBody
    public ServerResponse repairInfoSelectById(@RequestBody Repair repairInfo){
        return iRepairService.repairInfoSelectById(repairInfo);
    }

    /**
     * @Description 修改维修状态
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @PostMapping(value = "/editRepairInfo")
    @ResponseBody
    public ServerResponse editRepairInfo(@RequestBody Repair repairInfo){
        return iRepairService.editRepairInfo(repairInfo);
    }

}
