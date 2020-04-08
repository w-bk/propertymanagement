package org.wbk.propertymanagement.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.wbk.propertymanagement.entity.Notice;
import org.wbk.propertymanagement.entity.User;
import org.wbk.propertymanagement.service.INoticeService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王宝凯
 * @since 2020-01-12
 */
@Controller   //返回html文件
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private INoticeService iNoticeService;

    /**
     * @Description 查询全部公告和公告的全部信息
     * @Author 王宝凯
     * @Date 2020/3/1
     **/
    @GetMapping(value = "/noticeInfo")
    public String noticeList(Model model){
        List<Notice> noticeList = iNoticeService.noticeList();
       /* System.out.println("noticeList测试1111111");*/

        if (noticeList.isEmpty()){
            model.addAttribute("noticeList",null);
           /* System.out.println("noticeList测试2222222");*/
            return "notice/blanknotice";
        }else {
            model.addAttribute("noticeList",noticeList);
            /*System.out.println("noticeList测试成功33333333");*/
        }
        return "notice/notice";
    }

    /**
     * @Description 公告列表分页及搜索
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    @GetMapping(value = "/noticePageList")
    @ResponseBody //将java对象转为json格式的数据
    public Map noticePageList(Integer page,Integer limit,String noticeTitle){
        /*System.out.println("noticeTitle="+noticeTitle);*/
        IPage<Notice> noticePageList = iNoticeService.noticePageList(page,limit,noticeTitle);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);//分页返回的数据规范，正确的成功状态码应为："code": 0
        map.put("msg","");
        map.put("count",noticePageList.getTotal());//总条数
        map.put("data",noticePageList.getRecords());//返回的数据
        /*System.out.println(noticePageList.getRecords());*/
        return map;
    }

    /**
     * @Description 公告信息的删除
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    @GetMapping(value = "/deleteNotice")
    @ResponseBody
    public Map deleteNotice(@RequestParam int id){
        Map<String,Object> resp = new HashMap<>();
        int deleteNumber = iNoticeService.deleteNotice(id);
        if (deleteNumber > 0) {
            resp.put("code", true);
            resp.put("msg", "删除成功！");
        }else {
            resp.put("code",false);
            resp.put("msg","删除失败！");
        }
        return resp;

    }

    /**
     * @Description 实现编辑页面的弹窗和数据的显示
     * @Author 王宝凯
     * @Date 2020/3/14
     **/
    @GetMapping(value = "/selectByIdNotice")
    @ResponseBody
    public Map selectByIdNotice(@RequestParam int id, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        Notice noticeInfo = iNoticeService.selectByIdNotice(id);
        User user = (User) request.getSession().getAttribute("userInformation");
        String noticePublisher = user.getUserName();
        /*System.out.println(noticeInfo);
        System.out.println(noticePublisher);*/
        if (noticeInfo != null){
            map.put("code",true);
            map.put("data",noticeInfo);
            map.put("noticePublisher",noticePublisher);
        }else {
            map.put("code",false);
            map.put("msg","找不到数据！");
        }
        return map;
    }

    /**
     * @Description 实现编辑页面的修改
     * @Author 王宝凯
     * @Date 2020/3/14
     **/
    @PostMapping(value = "/updateNotice")
    @ResponseBody
    public Map updateNotice(@RequestBody Notice updateNotice){
        Map<String,Object> resp = new HashMap<>();
        /*Timestamp timeStamp = new Timestamp(new Date().getTime());*/
        Date time = new Date();
        updateNotice.setModificationTime(time);
        updateNotice.setUpdateTime(time);
        int updateNumber = iNoticeService.updateNotice(updateNotice);
        if(updateNumber > 0){
            resp.put("code",true);
            resp.put("msg","更新成功！");
        }else {
            resp.put("code",false);
            resp.put("msg","更新失败！");
        }
        /*System.out.println(resp);*/
        return resp;
    }

    /**
     * @Description 实现添加页面
     * @Author 王宝凯
     * @Date 2020/3/14
     **/
    @PostMapping(value = "/insertNotice")
    @ResponseBody
    public Map insertNotice(@RequestBody Notice insertNotice, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        Date time = new Date();
        User user = (User) request.getSession().getAttribute("userInformation");
        String noticePublisher = user.getUserName();
        insertNotice.setCreationTime(time);
        insertNotice.setNoticePublisher(noticePublisher);
        insertNotice.setCreateTime(time);
        insertNotice.setUpdateTime(time);
        int insertNumber = iNoticeService.insertNotice(insertNotice);
        if (insertNumber > 0){
            map.put("code",true);
            map.put("msg","添加成功！");
        }else {
            map.put("code",false);
            map.put("msg","添加失败！");
        }
        /*System.out.println(map);*/
        return map;
    }

}
