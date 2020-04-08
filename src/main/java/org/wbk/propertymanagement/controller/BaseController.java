package org.wbk.propertymanagement.controller;

import org.wbk.propertymanagement.entity.Building;
import org.wbk.propertymanagement.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 在session中获取信息
 * @Author 王宝凯
 * @Date 2020/4/5
 */
public class BaseController {

    /**
     * @Description: 在session中获取当前用户id
     * @Author 王宝凯
     * @Date 2020/4/5
     */
    public Integer getUserId(HttpServletRequest request){
        User user = getUser(request);
        return user==null?null:user.getId();
    }

    /**
     * @Description: 在session中获取当前用户信息
     * @Author 王宝凯
     * @Date 2020/4/5
     */
    public User getUser(HttpServletRequest request){
        Object object = request.getSession().getAttribute("userInformation");
        if(object == null){
            return null;
        }
        User user = (User)object;
        return user;
    }

    public User editUser(HttpServletRequest request){
        Object object = request.getSession().getAttribute("editUserInfo");
        if(object == null){
            return null;
        }
        User user = (User)object;
        return user;
    }

    /**
     * @Description: 在session中获取当前楼层信息
     * @Author 王宝凯
     * @Date 2020/4/5
     */
    public Building getBuilding(HttpServletRequest request){
        Object object = request.getSession().getAttribute("buildInfomation");
        if(object == null){
            return null;
        }
        Building building = (Building)object;
        return building;
    }


}
