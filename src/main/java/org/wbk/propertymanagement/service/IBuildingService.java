package org.wbk.propertymanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wbk.propertymanagement.entity.Building;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wbk.propertymanagement.response.ServerResponse;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王宝凯
 * @since 2020-03-31
 */
public interface IBuildingService extends IService<Building> {

    /**
     * @Description 根据用户修改前的手机号更新房屋表中的手机号
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    int updateBuildPhone(Building buildInfo, String userPhone);

    /**
     * @Description 删除用户操作 根据房屋id修改房屋的状态
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    int updateBuildSta(Building buildInfo);

    /**
     * @Description 查询房屋信息
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    List<Building> selectBuild();

    /**
     * @Description 根据用户身份证号和用户的手机号  查询楼房表中的楼房信息
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    Building selectBuildInfo(String userCard,String userPhone);

    /**
     * @Description 根据前端传过来的楼层表的id查询楼层号
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    Building selectByIdBuildInfo(String buildingId);

    /**
     * @Description 根据房屋id修改房屋表中的信息
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    int editBuildInfo(Building buildInfo);

    /**
     * @Description 根据用户的身份证号查找房屋信息
     * @Author 王宝凯
     * @Date 2020/4/5
     **/
    IPage<Building> buildInfo(Integer page, Integer limit, String userCard);

    /**
     * @Description 查询楼房表中的业主信息  用来显示楼房号
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    ServerResponse selectBuildNumber();
}
