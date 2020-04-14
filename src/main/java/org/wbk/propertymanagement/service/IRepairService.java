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
}
