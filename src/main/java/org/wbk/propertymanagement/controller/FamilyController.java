package org.wbk.propertymanagement.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wbk.propertymanagement.entity.Family;
import org.wbk.propertymanagement.response.ServerResponse;
import org.wbk.propertymanagement.service.IFamilyService;

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
@RequestMapping("/family")
public class FamilyController {
    @Autowired
    private IFamilyService iFamilyService;

    /**
     * @Description  查找业主对应的家人信息
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

}
