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

}
