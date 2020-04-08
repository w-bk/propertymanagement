package org.wbk.propertymanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.wbk.propertymanagement.entity.User;
import org.wbk.propertymanagement.mapper.UserMapper;
import org.wbk.propertymanagement.response.ServerResponse;
import org.wbk.propertymanagement.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王宝凯
 * @since 2020-01-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Description 验证登录 调用mybatisplus提供的方法，将传过来的参数作为条件，查询一条记录
     * @Author 王宝凯
     * @Date 2020/1/14
     **/
    @Override
    public ServerResponse selectUserLogin(User user, HttpServletRequest request) {
        if(!StringUtils.isNotEmpty(user.getUserPhone())){
            return ServerResponse.sendError("手机号不能为空");
        }
        if(!StringUtils.isNotEmpty(user.getUserPassword())){
            return ServerResponse.sendError("密码不能为空");
        }
        if(user.getStatus() == null){
            return ServerResponse.sendError("登录类型不能为空");
        }
        User userInfo = userMapper.selectOne(new QueryWrapper<User>()
                .eq("user_phone",user.getUserPhone())
                .eq("user_password",user.getUserPassword())
        .eq("status",user.getStatus()));
        if(userInfo == null){
            return ServerResponse.sendError("手机号或密码错误");
        }
        request.getSession().setAttribute("userInformation",userInfo);
        return ServerResponse.sendSuccess(userInfo);
    }

    /**
     * @Description 根据用户id来查询用户的基本信息
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    @Override
    public User userInforMation(Integer userId) {
        return userMapper.selectById(userId);
    }

    /**
     * @Description 根据用户的id更新用户头像
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    @Override
    public int avatarUpdate(User user) {
        return userMapper.updateById(user);
    }

    /**
     * @Description 根据用户的id修改个人信息
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    @Override
    public int updateUserInfo(User userInfo) {
        return userMapper.updateById(userInfo);
    }

    /**
     * @Description 根据用户的id修改用户密码
     * @Author 王宝凯
     * @Date 2020/3/29
     **/
    @Override
    public int updatePassword(User user) {
        return userMapper.updateById(user);
    }

    /**
     * @Description 业主列表界面
     * @Author 王宝凯
     * @Date 2020/3/31
     **/
    @Override
    public IPage<User> ownerList(Integer page, Integer limit, String ownerCard) {
        QueryWrapper<User> ownerQueryWrapper = new QueryWrapper<>();
        ownerQueryWrapper.eq(!("").equals(ownerCard) && ownerCard != null,"user_card",ownerCard).eq("status",1).orderByDesc("create_time");
        return userMapper.selectPage(new Page<>(page,limit),ownerQueryWrapper);
    }

    /**
     * @Description 租户列表界面
     * @Author 王宝凯
     * @Date 2020/3/31
     **/
    @Override
    public IPage<User> tenantList(Integer page, Integer limit, String tenantCard) {
        QueryWrapper<User> ownerQueryWrapper = new QueryWrapper<>();
        ownerQueryWrapper.eq(!("").equals(tenantCard) && tenantCard != null,"user_card",tenantCard).eq("status",2).orderByDesc("create_time");
        return userMapper.selectPage(new Page<>(page,limit),ownerQueryWrapper);
    }

    /**
     * @Description 管理员列表界面
     * @Author 王宝凯
     * @Date 2020/3/31
     **/
    @Override
    public IPage<User> adminList(Integer page, Integer limit, String adminCard) {
        QueryWrapper<User> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq(!("").equals(adminCard) && adminCard != null,"user_card",adminCard).eq("status",0).orderByDesc("create_time");
        return userMapper.selectPage(new Page<>(page,limit),adminQueryWrapper);
    }

    /**
     * @Description 根据用户传过来的id 删除信息
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    @Override
    public int deleteUser(int id) {
        return userMapper.deleteById(id);
    }

    /**
     * @Description 添加用户信息
     * @Author 王宝凯
     * @Date 2020/4/3
     **/
    @Override
    public int addUserInfo(User userInfo) {
        return userMapper.insert(userInfo);
    }

    /**
     * @Description 根据用户手机号查找用户信息 判断手机号是否已存在
     * @Author 王宝凯
     * @Date 2020/4/3
     **/
    @Override
    public User selectUserPhone(String userPhone) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq(!("").equals(userPhone) && userPhone != null,"user_phone",userPhone);
        return userMapper.selectOne(userQueryWrapper);
    }


}
