package org.wbk.propertymanagement.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wbk.propertymanagement.entity.Family;
import org.wbk.propertymanagement.response.ServerResponse;
import org.wbk.propertymanagement.service.IFamilyService;

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
@RequestMapping("/family")
public class FamilyController {
    @Autowired
    private IFamilyService iFamilyService;

    /**
     * @Description  查找业主对应的家人信息   查看家人信息
     * @Author 王宝凯
     * @Date 2020/4/8
     **/
    @GetMapping(value = "/selectFamilyInfo")
    @ResponseBody
    public Map selectFamilyInfo(Integer page,Integer limit,@RequestParam String userCard){
        Map<String,Object> map = new HashMap<>();
        IPage<Family> familyInfo = iFamilyService.selectFamilyInfo(page,limit,userCard);
        map.put("code",0);
        map.put("msg","");
        map.put("count",familyInfo.getTotal());//总条数
        map.put("data",familyInfo.getRecords());//返回的数据
        return map;
    }

    /**
     * @Description  查看家人信息
     * @Author 王宝凯
     * @Date 2020/4/8
     **/
    @GetMapping(value = "/familyList")
    @ResponseBody
    public Map familyList(Integer page,Integer limit,String ownerCard){
        Map<String,Object> map = new HashMap<>();
        IPage<Family> familyList = iFamilyService.familyList(page,limit,ownerCard);
        map.put("code",0);
        map.put("msg","");
        map.put("count",familyList.getTotal());//总条数
        map.put("data",familyList.getRecords());//返回的数据
        return map;
    }

    /**
     * @Description 删除家人信息
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    @GetMapping(value = "/deleteFamily")
    @ResponseBody
    public ServerResponse deleteFamily(@RequestParam Integer id){
        return iFamilyService.deleteFamily(id);
    }

    /**
     * @Description 根据家人表的id查询家人信息 实现页面回显
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    @GetMapping(value = "/selectByIdFamily")
    @ResponseBody
    public ServerResponse selectByIdFamily(@RequestParam Integer id){
        return iFamilyService.selectByIdFamily(id);
    }

    /**
     * @Description 根据家人表的id更新家人信息
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    @PostMapping(value = "/reviseFamilyInfo")
    @ResponseBody
    public ServerResponse reviseFamilyInfo(@RequestBody Family familyInfo){
        return iFamilyService.reviseFamilyInfo(familyInfo);
    }

    /**
     * @Description 添加家人信息
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    @PostMapping(value = "/addFamilyList")
    @ResponseBody
    public ServerResponse addFamilyList(@RequestBody Family familyList){
        return iFamilyService.addFamilyList(familyList);
    }

}
