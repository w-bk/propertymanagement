package org.wbk.propertymanagement.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wbk.propertymanagement.entity.Building;
import org.wbk.propertymanagement.response.ServerResponse;
import org.wbk.propertymanagement.service.IBuildingService;

import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private IBuildingService iBuildingService;

    /**
     * @Description 查询房屋信息
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    @GetMapping(value = "/selectBuild")
    @ResponseBody
    public Map selectBuild (){
        Map<String,Object> map = new HashMap<>();
        List<Building> buildingList = iBuildingService.selectBuild();
        if (buildingList.isEmpty()){
            map.put("code",false);
            map.put("msg","找不到房屋信息！");
        }else {
            map.put("code",true);
            map.put("data",buildingList);
        }
        /*System.out.println("buildingList:"+buildingList);*/
        return map;
    }

    /**
     * @Description 根据用户的身份证号查找房屋信息
     * @Author 王宝凯
     * @Date 2020/4/5
     **/
    @GetMapping(value = "/buildInfo")
    @ResponseBody
    public Map buildInfo (Integer page,Integer limit,@RequestParam String userCard){
        Map<String,Object> map = new HashMap<>();
        IPage<Building> buildInfo = iBuildingService.buildInfo(page,limit,userCard);
        map.put("code",0);//分页返回的数据规范，正确的成功状态码应为："code": 0
        map.put("msg","");
        map.put("count",buildInfo.getTotal());//总条数
        map.put("data",buildInfo.getRecords());//返回的数据
        return map;
    }

    /**
     * @Description 根据前端传过来的楼层表的id查询楼层号
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    @GetMapping(value = "/selectByIdBuildInfo")
    @ResponseBody
    public ServerResponse selectByIdBuildInfo(@RequestParam String buildingId){
        Building buildingInfo = iBuildingService.selectByIdBuildInfo(buildingId);
        if (buildingInfo == null){
            return ServerResponse.sendError("找不到数据！");
        }
        return ServerResponse.sendSuccess(buildingInfo);
    };

    /**
     * @Description 查询楼房表中的业主信息  用来显示楼房号
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    @GetMapping(value = "/selectBuildNumber")
    @ResponseBody
    public ServerResponse selectBuildNumber(){
        return iBuildingService.selectBuildNumber();
    }

}
