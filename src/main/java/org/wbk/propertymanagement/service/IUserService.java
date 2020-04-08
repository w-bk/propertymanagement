package org.wbk.propertymanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wbk.propertymanagement.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wbk.propertymanagement.response.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王宝凯
 * @since 2020-01-12
 */
public interface IUserService extends IService<User> {
    /**
     * @Description 验证登录
     * @Author 王宝凯
     * @Date 2020/1/14
     **/
    ServerResponse selectUserLogin(User user, HttpServletRequest request);

    /**
     * @Description 根据用户id来查询用户的基本信息
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    User userInforMation(Integer userId);

    /**
     * @Description 根据用户的id更新用户头像
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    int avatarUpdate(User user);

    /**
     * @Description 根据用户的id修改个人信息
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    int updateUserInfo(User userInfo);

    /**
     * @Description 根据用户的id修改用户密码
     * @Author 王宝凯
     * @Date 2020/3/29
     **/
    int updatePassword(User user);

    /**
     * @Description 业主列表界面
     * @Author 王宝凯
     * @Date 2020/3/31
     **/
    IPage<User> ownerList(Integer page, Integer limit, String ownerCard);

    /**
     * @Description 租户列表界面
     * @Author 王宝凯
     * @Date 2020/3/31
     **/
    IPage<User> tenantList(Integer page, Integer limit, String tenantCard);

    /**
     * @Description 管理员列表界面
     * @Author 王宝凯
     * @Date 2020/3/31
     **/
    IPage<User> adminList(Integer page, Integer limit, String adminCard);

    /**
     * @Description 根据用户传过来的id 删除信息
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    int deleteUser(int id);

    /**
     * @Description 添加用户信息
     * @Author 王宝凯
     * @Date 2020/4/3
     **/
    int addUserInfo(User userInfo);

    /**
     * @Description 根据用户手机号查找用户信息 判断手机号是否已存在
     * @Author 王宝凯
     * @Date 2020/4/3
     **/
    User selectUserPhone(String userPhone);
}
