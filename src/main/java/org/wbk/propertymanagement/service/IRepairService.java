package org.wbk.propertymanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wbk.propertymanagement.entity.Repair;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wbk.propertymanagement.response.ServerResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王宝凯
 * @since 2020-03-31
 */
public interface IRepairService extends IService<Repair> {

    /**
     * @Description 维修管理列表
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    IPage<Repair> repairList(Integer page, Integer limit, Integer repairState);

    /**
     * @Description 根据前端传过来的实体类 获得对象 给页面赋值
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    ServerResponse repairInfoSelectById(Repair repairInfo);

    /**
     * @Description 修改维修状态
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    ServerResponse editRepairInfo(Repair repairInfo);

    /**
     * @Description 用户查看报修的信息
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    IPage<Repair> userRepairList(Integer page, Integer limit, Integer userRepairState, String userPhone);

    /**
     * @Description 用户添加维修信息
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    ServerResponse addRepairInfo(Repair repair);

    /**
     * @Description 用户修改维修内容
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    ServerResponse userEditRepairInfo(Repair repairInfo);

    /**
     * @Description 用户删除未维修的信息
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    ServerResponse deleteRepair(Integer id);


}
