package org.wbk.propertymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 页面跳转
 * @Author 王宝凯
 * @Date 2020/1/12
 */
@Controller
public class PageController {

    /**
     * @Description 跳转登录页面
     * @Author 王宝凯
     * @Date 2020/1/14
     **/
    @GetMapping("/")
    public String login(){
        return "login";
    }
    /**
     * @Description 跳转后台页面
     * @Author 王宝凯
     * @Date 2020/1/14
     **/
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    /**
     * @Description 跳转业主页面
     * @Author 王宝凯
     * @Date 2020/2/23
     **/
    @GetMapping("/owner")
    public String houseowner(){
        return "owner";
    }
    /**
     * @Description 跳转租户页面
     * @Author 王宝凯
     * @Date 2020/2/23
     **/
    @GetMapping("/tenant")
    public String tenant(){
        return "tenant";
    }
    /**
     * @Description 界面
     * @Author 王宝凯
     * @Date 2020/3/10
     **/
    @GetMapping("/welcome")
    public String welcome(){
        return "user/welcome";
    }
    @GetMapping("/LayuiForm")
    public String LayuiForm(){
        return "LayuiForm";
    }
    /**
     * @Description 前端公告界面
     * @Author 王宝凯
     * @Date 2020/3/1
     **/
    @GetMapping("/notice")
    public String notice(){
        return "notice/notice";
    }
    /**
     * @Description 空白前端公告界面
     * @Author 王宝凯
     * @Date 2020/3/1
     **/
    @GetMapping("/blanknotice")
    public String blanknotice(){
        return "notice/blanknotice";
    }
    /**
     * @Description 后端公告界面
     * @Author 王宝凯
     * @Date 2020/3/10
     **/
    @GetMapping("/noticePageList")
    public String noticePageList(){
        return "notice/noticeList";
    }
    /**
     * @Description 用户基本资料
     * @Author 王宝凯
     * @Date 2020/3/8
     **/
    @GetMapping("/userInformation")
    public String userInformation(){
        return "user/userInformation";
    }
    /**
     * @Description 公告的编辑页面
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    @GetMapping("/updateNotice")
    public String updateNotice(){
        return "notice/updateNotice";
    }
    /**
     * @Description 公告的添加页面
     * @Author 王宝凯
     * @Date 2020/3/14
     **/
    @GetMapping("/insertNotice")
    public String insertNotice(){
        return "notice/insertNotice";
    }
    /**
     * @Description 修改密码界面
     * @Author 王宝凯
     * @Date 2020/3/29
     **/
    @GetMapping("/ChangePassword")
    public  String ChangePassword(){
        return "user/ChangePassword";
    }
    /**
     * @Description 业主信息界面
     * @Author 王宝凯
     * @Date 2020/3/31
     **/
    @GetMapping("/ownerList")
    public String ownerList(){
        return "owner/ownerList";
    }
    /**
     * @Description 租户信息界面
     * @Author 王宝凯
     * @Date 2020/3/31
     **/
    @GetMapping("/tenantList")
    public String tenantList(){
        return "tenant/tenantList";
    }
    /**
     * @Description 管理员信息界面
     * @Author 王宝凯
     * @Date 2020/3/31
     **/
    @GetMapping("/adminList")
    public String adminList(){
        return "admin/adminList";
    }
    /**
     * @Description 用户的编辑页面
     * @Author 王宝凯
     * @Date 2020/4/2
     **/
    @GetMapping("/editOperation")
    public String editOperation(){
        return "userPublicOperation/editOperation";
    }
    /**
     * @Description 用户的添加页面
     * @Author 王宝凯
     * @Date 2020/4/2
     **/
    @GetMapping("/addOperation")
    public String addOperation(){
        return "userPublicOperation/addOperation";
    }
    /**
     * @Description 用户的房屋信息
     * @Author 王宝凯
     * @Date 2020/4/2
     **/
    @GetMapping("/buildInfo")
    public String buildInfo(){
        return "userPublicOperation/buildInfo";
    }
    /**
     * @Description 用户对应的公共缴费操作
     * @Author 王宝凯
     * @Date 2020/4/11
     **/
    @GetMapping("/costInfo")
    public String costInfo(){
        return "userPublicOperation/costInfo";
    }
    /**
     * @Description 业主对应的家人信息界面
     * @Author 王宝凯
     * @Date 2020/4/3
     **/
    @GetMapping("/familyInfo")
    public String familyInfo(){
        return "owner/familyInfo";
    }
    /**
     * @Description 后端家人信息表
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    @GetMapping("/familyList")
    public String familyList(){
        return "family/familyList";
    }
    /**
     * @Description 后端家人信息的编辑页面
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    @GetMapping("/editFamilyInfo")
    public String editFamilyInfo(){
        return "family/editFamilyInfo";
    }
    /**
     * @Description 后端家人信息的添加页面
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    @GetMapping("/addFamilyInfo")
    public String addFamilyInfo(){
        return "family/addFamilyInfo";
    }
    /**
     * @Description 缴费类型页面
     * @Author 王宝凯
     * @Date 2020/4/12
     **/
    @GetMapping("/costTypes")
    public String costTypes(){
        return "cost/costTypes";
    }
    /**
     * @Description 添加缴费类型页面
     * @Author 王宝凯
     * @Date 2020/4/12
     **/
    @GetMapping("/addCostType")
    public String addCostType(){
        return "cost/addCostType";
    }
    /**
     * @Description 缴费管理界面
     * @Author 王宝凯
     * @Date 2020/4/13
     **/
    @GetMapping("/paymentList")
    public String paymentList(){
        return "payment/paymentList";
    }
    /**
     * @Description 修改缴费状态
     * @Author 王宝凯
     * @Date 2020/4/13
     **/
    @GetMapping("/editPayment")
    public String editPayment(){
        return "payment/editPayment";
    }
    /**
     * @Description 房屋管理列表
     * @Author 王宝凯
     * @Date 2020/4/13
     **/
    @GetMapping("/buildingList")
    public String buildingList(){
        return "building/buildingList";
    }
    /**
     * @Description 修改房屋信息页面
     * @Author 王宝凯
     * @Date 2020/4/13
     **/
    @GetMapping("/editBuildingInfo")
    public String editBuildingInfo(){
        return "building/editBuildingInfo";
    }
    /**
     * @Description 添加房屋信息页面
     * @Author 王宝凯
     * @Date 2020/4/13
     **/
    @GetMapping("/addBuildingInfo")
    public String addBuildingInfo(){
        return "building/addBuildingInfo";
    }
    /**
     * @Description 维修管理页面
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @GetMapping("/repairList")
    public String repairList(){
        return "repair/repairList";
    }
    /**
     * @Description 修改维修状态页面
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @GetMapping("/editRepair")
    public String editRepair(){
        return "repair/editRepair";
    }
    /**
     * @Description 用户房屋信息页面
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @GetMapping("/userBuildingList")
    public String userBuildingList(){
        return "building/userBuildingList";
    }
    /**
     * @Description 业主界面的家人信息的显示
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @GetMapping("/ownerFamilyList")
    public String ownerFamilyList(){
        return "family/ownerFamilyList";
    }
    /**
     * @Description 用户查看缴费信息界面
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @GetMapping("/userPaymentList")
    public String userPaymentList(){
        return "payment/userPaymentList";
    }
    /**
     * @Description 用户维修页面
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @GetMapping("/userRepairList")
    public String userRepairList(){
        return "repair/userRepairList";
    }
    /**
     * @Description 添加维修页面
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @GetMapping("/addRepair")
    public String addRepair(){
        return "repair/addRepair";
    }
    /**
     * @Description 用户修改页面
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @GetMapping("/userEditRepair")
    public String userEditRepair(){
        return "repair/userEditRepair";
    }


}
