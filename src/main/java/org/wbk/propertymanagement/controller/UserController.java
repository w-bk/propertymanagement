package org.wbk.propertymanagement.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.wbk.propertymanagement.entity.Building;
import org.wbk.propertymanagement.entity.Family;
import org.wbk.propertymanagement.entity.User;
import org.wbk.propertymanagement.response.ServerResponse;
import org.wbk.propertymanagement.service.IBuildingService;
import org.wbk.propertymanagement.service.IFamilyService;
import org.wbk.propertymanagement.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王宝凯
 * @since 2020-01-12
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Value("${uploadFile.location}")
    private String location;

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IBuildingService iBuildingService;
    @Autowired
    private IFamilyService iFamilyService;
    /**
     * @Description 登录界面 如果用户名或密码不正确，会给出用户相应提示；
     *              用户名和密码正确的话 返回给前端json  根据权限跳转页面
     * @Author 王宝凯
     * @Date 2020/1/14
     *
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse userLogin(@RequestBody User user, HttpServletRequest request){
        return iUserService.selectUserLogin(user,request);
    }

    /**
     * @Description 根据用户id来查询用户的基本信息
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    @GetMapping(value = "/userInforMation")
    public String userInforMation(Model model,HttpServletRequest request){
        Integer userId = getUserId(request);
        User userInforMation = iUserService.userInforMation(userId);
        //将user类放入session 用来获取当前登录的用户信息
        request.getSession().setAttribute("userInformation",userInforMation);
        /*System.out.println(userInforMation);*/
        model.addAttribute("userInformation",userInforMation);
        return "user/userInformation";
    }

    /**
     * @Description 根据用户的id更新用户头像
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    @PostMapping(value = "/avatarUpload")
    @ResponseBody
    public Map avatarUpload(MultipartFile file, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //获取当前登录用户
        User  user = getUser(request);
        // 源文件名称包括后缀
        String fileName = file.getOriginalFilename();
        //从右向左到. 获取下标
        int pointIndex =  fileName.lastIndexOf(".");
        // 文件后缀
        String fileSuffix = fileName.substring(pointIndex);
        // 获取当前时间戳
        String fileAlias = System.currentTimeMillis()+"";
        // 保存到本地的文件名称
        String saveFileName = fileAlias.concat(fileSuffix);
        File savedFile = new File(location,saveFileName);
        if (!file.isEmpty()){
            try {
                // 把上传过来的文件拷贝到本地新文件
                FileUtils.copyInputStreamToFile(file.getInputStream(),savedFile);
                user.setAvatar(saveFileName);
                int avatarUpdate =  iUserService.avatarUpdate(user);
                if (avatarUpdate>0){
                    map.put("code",0);
                    /*System.out.println("map的值："+map.toString());*/
                    return map;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        map.put("code",1);
        return map;
    }

    /**
     * @Description 用户修改个人信息和修改房屋表中的信息
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    @PostMapping(value = "/updateUserInfo")
    @ResponseBody
    public Map updateUserInfo(@RequestBody User userInfo, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //判断手机号是否已存在
        User user = iUserService.selectUserPhone(userInfo.getUserPhone());
        if (user != null && !(userInfo.getUserCard()).equals(user.getUserCard()) ){
            map.put("code",false);
            map.put("msg","手机号已存在！");
            return map;
        }
        User oldUserInfo = getUser(request);
        Building buildInfo = new Building();
        buildInfo.setUserPhone(userInfo.getUserPhone());
        buildInfo.setUpdateTime(new Date());
        userInfo.setUpdateTime(new Date());
        int updateUserNumber = iUserService.updateUserInfo(userInfo);
        //根据用户修改前的手机号更新房屋表中的手机号
        int updateBuildNum = iBuildingService.updateBuildPhone(buildInfo,oldUserInfo.getUserPhone());
        if (updateBuildNum > 0 && updateUserNumber > 0){
            map.put("code",true);
            map.put("msg","用户信息更新成功！");
        }else {
            map.put("code",false);
            map.put("msg","用户信息更新失败！");
        }
       /* System.out.println(map);*/
        return map;
    }

    /**
     * @Description 根据用户的id修改用户密码
     * @Author 王宝凯
     * @Date 2020/3/29
     **/
    @PostMapping(value = "/updatePassword")
    @ResponseBody
    public Map updatePassword(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        String oldPassword = request.getParameter("oldPassword");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        //获取当前登录的用户
        User  user = getUser(request);
        String userPassword = user.getUserPassword();
        int passwordNumber;
        if (oldPassword.equals(userPassword)){
            if (password.equals(repassword)){
                user.setUserPassword(password);
                passwordNumber = iUserService.updatePassword(user);
            }else {
                map.put("code",false);
                map.put("msg","新输入的两次密码不一致！");
                return map;
            }
        }else {
            map.put("code",false);
            map.put("msg","原密码不一致！");
            return map;
        }
        if (passwordNumber > 0){
            map.put("code",true);
            map.put("msg","修改成功，请重新登录！");
        }else {
            map.put("code",false);
            map.put("msg","修改失败，请重新填写！");
        }
        /*System.out.println(map);*/
        return map;
    }

    /**
     * @Description 租户列表界面
     * @Author 王宝凯
     * @Date 2020/3/31
     **/
    @GetMapping(value = "/tenantList")
    @ResponseBody
    public Map tenantList(Integer page,Integer limit,String tenantCard){
        Map<String,Object> map = new HashMap<>();
        IPage<User> tenantList = iUserService.tenantList(page,limit,tenantCard);
        map.put("code",0);//分页返回的数据规范，正确的成功状态码应为："code": 0
        map.put("msg","");
        map.put("count",tenantList.getTotal());//总条数
        map.put("data",tenantList.getRecords());//返回的数据
        return map;
    }

    /**
     * @Description 业主列表界面
     * @Author 王宝凯
     * @Date 2020/3/31
     **/
    @GetMapping(value = "/ownerList")
    @ResponseBody
    public Map ownerList(Integer page,Integer limit,String ownerCard){
        Map<String,Object> map = new HashMap<>();
        IPage<User> ownerList = iUserService.ownerList(page,limit,ownerCard);
        map.put("code",0);//分页返回的数据规范，正确的成功状态码应为："code": 0
        map.put("msg","");
        map.put("count",ownerList.getTotal());//总条数
        map.put("data",ownerList.getRecords());//返回的数据
        return map;
    }

    /**
     * @Description 管理员列表界面
     * @Author 王宝凯
     * @Date 2020/3/31
     **/
    @GetMapping(value = "/adminList")
    @ResponseBody
    public Map adminList(Integer page,Integer limit,String adminCard){
        Map<String,Object> map = new HashMap<>();
        IPage<User> adminList = iUserService.adminList(page,limit,adminCard);
        map.put("code",0);//分页返回的数据规范，正确的成功状态码应为："code": 0
        map.put("msg","");
        map.put("count",adminList.getTotal());//总条数
        map.put("data",adminList.getRecords());//返回的数据
        return map;
    }

    /**
     * @Description 根据用户传过来的id 删除信息
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    @GetMapping(value = "/deleteUser")
    @ResponseBody
    public Map deleteUser(@RequestParam int id){
        Map<String,Object> map = new HashMap<>();
        //根据id查询用户信息
        User userInfo = iUserService.userInforMation(id);
        //根据用户的身份证号和手机号 查询楼房信息
        Building buildInfo = iBuildingService.selectBuildInfo(userInfo.getUserCard(),userInfo.getUserPhone());
        //根据业主户的身份证号和楼房号 查询家人信息
        List<Family> familyList = iFamilyService.selectFamilyList(userInfo.getUserCard(),buildInfo.getBuildingNumber());
        int deletFamNum =0;
        if (userInfo.getStatus() == 1 && !familyList.isEmpty()){
            //删除业主对应的家人表中的信息
            deletFamNum = iFamilyService.deletFamily(userInfo.getUserCard(),buildInfo.getBuildingNumber());
        }
        //删除用户操作 修改房屋的状态
        int buildSta = iBuildingService.updateBuildSta(buildInfo);
        int deleteUserNumber = iUserService.deleteUser(id);
        if (userInfo.getStatus() == 1 && !familyList.isEmpty()){
            if (deletFamNum > 0 && buildSta > 0 && deleteUserNumber > 0){
                map.put("code",true);
                map.put("msg","删除成功！");
            }else {
                map.put("code",false);
                map.put("msg","删除失败！");
            }
        }else {
            if (buildSta > 0 && deleteUserNumber > 0){
                map.put("code",true);
                map.put("msg","删除成功！");
            }else {
                map.put("code",false);
                map.put("msg","删除失败！");
            }
        }
        return map;
    }

    /**
     * @Description 用户编辑页面数据显示
     * @Author 王宝凯
     * @Date 2020/4/2
     **/
    @GetMapping(value = "/selectByIdUser")
    @ResponseBody
    public Map selectByIdUser(@RequestParam int id,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //根据id查询用户信息
        User userInfo = iUserService.userInforMation(id);
        request.getSession().setAttribute("editUserInfo",userInfo);
        //根据用户身份证号和用户的手机号  查询楼房表中的楼房信息
        Building buildInfo = iBuildingService.selectBuildInfo(userInfo.getUserCard(),userInfo.getUserPhone());
        request.getSession().setAttribute("buildInfo",buildInfo);
     /* System.out.println(buildInfo);
        System.out.println(buildInfo.getId());*/
        if (userInfo != null && buildInfo != null){
            map.put("code",true);
            map.put("data",userInfo);
            map.put("buildId",buildInfo.getId());
        }else {
            map.put("code",false);
            map.put("msg","找不到数据！");
        }
        return map;
    }

    /**
     * @Description 实现用户编辑页面的修改
     * @Author 王宝凯
     * @Date 2020/4/3
     **/
    @PostMapping(value = "/editUserInfo")
    @ResponseBody
    public Map editUserInfo(@RequestBody User userInfo,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        User editUser = editUser(request);
        /*System.out.println(editUser);*/
        if(!(editUser.getUserPhone()).equals(userInfo.getUserPhone())){
            User user = iUserService.selectUserPhone(userInfo.getUserPhone());
            if (user != null){
                map.put("code",false);
                map.put("msg","手机号已存在！");
                return map;
            }
        }
        //获取到前端传的楼房表的id
        String buildingId = userInfo.getBuildingNumber();
       /* System.out.println("buildingNumberId:"+buildingNumberId);*/
        //根据楼层表的id查询楼层信息
        Building buildingInfo = iBuildingService.selectByIdBuildInfo(buildingId);
        //获取楼房号
        String buildingNumber = buildingInfo.getBuildingNumber();
        //获取用户修改之前的房屋信息
        Building oldBuildInfo = getBuilding(request);

        buildingInfo.setUserCard(userInfo.getUserCard());
        buildingInfo.setUserPhone(userInfo.getUserPhone());
        buildingInfo.setBuildingStatus(oldBuildInfo.getBuildingStatus());
        buildingInfo.setBuildingInfo(oldBuildInfo.getBuildingInfo());
        buildingInfo.setLeaseTime(oldBuildInfo.getLeaseTime());
        buildingInfo.setDueTime(oldBuildInfo.getDueTime());
        buildingInfo.setUpdateTime(new Date());
        //根据用户id获取用户信息  用来判断修改用户的权限
        User userInforMation = iUserService.userInforMation(userInfo.getId());
        int editFamilyNum = 0;
        if (userInforMation.getStatus() == 1){
            //获取到业主身份证号
            String ownerCard = userInfo.getUserCard();
            //更新业主对应家人表的楼房号
            editFamilyNum = iFamilyService.editFamilyInfo(ownerCard,buildingNumber);
        }
        userInfo.setUpdateTime(new Date());
        //将用户之前的房屋信息全部清空
        int editBuildUserNum = iBuildingService.updateBuildSta(oldBuildInfo);
        /*System.out.println("清空房屋表"+editBuildUserNum);*/
        //根据房屋id修改房屋表中的信息
        int editBuildNum = iBuildingService.editBuildInfo(buildingInfo);
        //修改用户信息
        int editUserNum = iUserService.updateUserInfo(userInfo);
       /* System.out.println("修改用户信息："+editUserNum);*/
        if (userInforMation.getStatus() == 1){
            if(editFamilyNum > 0 && editBuildUserNum >0 && editBuildNum >0 && editUserNum >0){
                map.put("code",true);
                map.put("msg","修改成功！");
            }else {
                map.put("code",false);
                map.put("msg","修改失败！");
            }
        }else {
            if(editBuildUserNum >0 && editBuildNum >0 && editUserNum >0){
                map.put("code",true);
                map.put("msg","修改成功！");
            }else {
                map.put("code",false);
                map.put("msg","修改失败！");
            }
        }
        return map;
    }

    /**
     * @Description 实现用户添加页面
     * @Author 王宝凯
     * @Date 2020/4/3
     **/
    @PostMapping(value = "/addUserInfo")
    @ResponseBody
    public Map addUserInfo(@RequestBody User userInfo){
        Map<String,Object> map = new HashMap<>();
        //获取用户的手机号
        String userPhone = userInfo.getUserPhone();
        //判断手机号是否已存在
        User user = iUserService.selectUserPhone(userPhone);
        if (user != null){
            map.put("code",false);
            map.put("msg","手机号已存在！");
            return map;
        }
        //获取到前端传的楼房表的id
        String buildingId = userInfo.getBuildingNumber();
        //根据楼房表的id获取楼房信息
        Building buildInfo = iBuildingService.selectByIdBuildInfo(buildingId);
        userInfo.setCreateTime(new Date());
        userInfo.setUpdateTime(new Date());
        //获取日期范围
        String rangeDate = userInfo.getRangeDate();
        String leaseTime = null;
        String dueTime = null;
        if(StringUtils.isNotEmpty(rangeDate)){
            String[] strsplit = rangeDate.split("~");
            for (int i = 0;i < strsplit.length; i++){
                leaseTime =strsplit[0];
                dueTime = strsplit[1];
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (userInfo.getStatus() == 0){
            buildInfo.setBuildingInfo("管理员");
            buildInfo.setLeaseTime(sdf.format(new Date()));
            buildInfo.setDueTime(null);
        }else if(userInfo.getStatus() == 1){
            buildInfo.setBuildingInfo("自住");
            buildInfo.setLeaseTime(sdf.format(new Date()));
            buildInfo.setDueTime(null);
        }else if (userInfo.getStatus() == 2){
            buildInfo.setBuildingInfo("出租");
            buildInfo.setLeaseTime(leaseTime);
            buildInfo.setDueTime(dueTime);
        }
        buildInfo.setUserPhone(userPhone);
        buildInfo.setUserCard(userInfo.getUserCard());
        buildInfo.setBuildingStatus(1);
        buildInfo.setUpdateTime(new Date());
        //根据房屋id修改房屋表中的信息
        int editBuildNum = iBuildingService.editBuildInfo(buildInfo);
        //往用户表中添加信息
        int addUserNum = iUserService.addUserInfo(userInfo);
        if(editBuildNum >0 && addUserNum >0){
            map.put("code",true);
            map.put("msg","添加成功！");
        }else {
            map.put("code",false);
            map.put("msg","添加失败！");
        }
        return map;
    }

    /**
     * @Description 获取当前登录的用户信息 用来进行维修信息的赋值
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @GetMapping(value = "/getUserInfo")
    @ResponseBody
    public ServerResponse getUserInfo(HttpServletRequest request){
        User userInfo = getUser(request);
        if (userInfo == null){
            return ServerResponse.sendError("数据出错！");
        }
        return ServerResponse.sendSuccess(userInfo);
    }
}
