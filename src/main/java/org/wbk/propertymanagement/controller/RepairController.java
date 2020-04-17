package org.wbk.propertymanagement.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wbk.propertymanagement.entity.Repair;
import org.wbk.propertymanagement.entity.User;
import org.wbk.propertymanagement.response.ServerResponse;
import org.wbk.propertymanagement.service.IRepairService;

import javax.servlet.http.HttpServletRequest;
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
public class RepairController extends BaseController {
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

    /**
     * @Description 用户查看报修的信息
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
   @GetMapping(value = "/userRepairList")
   @ResponseBody
    public Map userRepairList(Integer page, Integer limit, Integer userRepairState, HttpServletRequest request){
        User user = getUser(request);
        Map<String,Object> map = new HashMap<>();
        IPage<Repair> repairList = iRepairService.userRepairList(page,limit,userRepairState,user.getUserPhone());
        map.put("code",0);
        map.put("msg","");
        map.put("count",repairList.getTotal());//总条数
        map.put("data",repairList.getRecords());//数据
        return map;
    }

    /**
     * @Description 用户添加维修信息
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @PostMapping(value = "/addRepairInfo")
    @ResponseBody
    public ServerResponse addRepairInfo(@RequestBody Repair repair){
        return iRepairService.addRepairInfo(repair);
    }

    /**
     * @Description 用户修改维修内容
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @PostMapping(value = "/userEditRepairInfo")
    @ResponseBody
    public ServerResponse userEditRepairInfo(@RequestBody Repair repairInfo){
        return iRepairService.userEditRepairInfo(repairInfo);
    }

    /**
     * @Description 用户删除未维修的信息
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @GetMapping(value = "/deleteRepair")
    @ResponseBody
    public ServerResponse deleteRepair(@RequestParam Integer id){
        return iRepairService.deleteRepair(id);
    }



}
