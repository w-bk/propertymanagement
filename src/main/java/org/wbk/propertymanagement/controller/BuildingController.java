package org.wbk.propertymanagement.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wbk.propertymanagement.entity.Building;
import org.wbk.propertymanagement.entity.User;
import org.wbk.propertymanagement.response.ServerResponse;
import org.wbk.propertymanagement.service.IBuildingService;

import javax.servlet.http.HttpServletRequest;
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
public class BuildingController extends BaseController {

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
     * @Description 根据前端传过来的楼层表的id查询楼层信息（楼房号）
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
    /**
     * @Description 根据用户身份证号和用户的手机号  查询楼房表中的楼房信息
     * @Author 王宝凯
     * @Date 2020/4/11
     **/
    @PostMapping(value = "/selectBuildInfo")
    @ResponseBody
    public ServerResponse selectBuildInfo(@RequestBody User userInfo){
        Building building = iBuildingService.selectBuildInfo(userInfo.getUserCard(),userInfo.getUserPhone());
        if (building == null){
            return ServerResponse.sendError("找不到数据！");
        }
        return ServerResponse.sendSuccess(building);
    }

    /**
     * @Description 显示房屋列表信息 根据房屋状态进行搜索查询
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @GetMapping(value = "/buildingList")
    @ResponseBody
    public Map buildingList(Integer page,Integer limit,Integer buildingStatus){
        Map<String,Object> map = new HashMap<>();
        IPage<Building> buildingList = iBuildingService.buildingList(page,limit,buildingStatus);
        map.put("code",0);//分页返回的数据规范，正确的成功状态码应为："code": 0
        map.put("msg","");
        map.put("count",buildingList.getTotal());//总条数
        map.put("data",buildingList.getRecords());//返回的数据
        return map;
    }

    /**
     * @Description 停用房屋操作  修改房屋的使用状态
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @GetMapping(value = "/stopBuildingInfo")
    @ResponseBody
    public ServerResponse stopBuildingInfo(@RequestParam Integer id){
        return iBuildingService.stopBuildingInfo(id);
    }

    /**
     * @Description 启用房屋操作  修改房屋的使用状态
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @GetMapping(value = "/enableBuildingInfo")
    @ResponseBody
    public ServerResponse enableBuildingInfo(@RequestParam Integer id){
        return iBuildingService.enableBuildingInfo(id);
    }

    /**
     * @Description 编辑房屋操作
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @PostMapping(value = "/editBuildingInfo")
    @ResponseBody
    public ServerResponse editBuildingInfo(@RequestBody Building buildingInfo){
        return iBuildingService.editBuildingInfo(buildingInfo);
    }

    /**
     * @Description 添加房屋操作
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @PostMapping(value = "/addBuildingInfo")
    @ResponseBody
    public ServerResponse addBuildingInfo(@RequestBody Building buildingInfo){
        return iBuildingService.addBuildingInfo(buildingInfo);
    }

    /**
     * @Description 查找用户房屋信息
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @GetMapping(value = "/userBuildingList")
    @ResponseBody
    public Map userBuildingList(Integer page, Integer limit, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        User  user = getUser(request);
        IPage<Building> userBuildingList = iBuildingService.userBuildingList(page,limit,user.getUserPhone());
        map.put("code",0);
        map.put("msg","");
        map.put("count",userBuildingList.getTotal()); //总数
        map.put("data",userBuildingList.getRecords()); //数据
        return map;
    }

}
