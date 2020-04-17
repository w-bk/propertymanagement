package org.wbk.propertymanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.wbk.propertymanagement.entity.PaymentInfo;
import org.wbk.propertymanagement.entity.Repair;
import org.wbk.propertymanagement.mapper.RepairMapper;
import org.wbk.propertymanagement.response.ServerResponse;
import org.wbk.propertymanagement.service.IRepairService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王宝凯
 * @since 2020-03-31
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements IRepairService {
    @Autowired
    private RepairMapper repairMapper;

    /**
     * @Description 维修管理列表
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @Override
    public IPage<Repair> repairList(Integer page, Integer limit, Integer repairState) {
        return repairMapper.selectPage(new Page<>(page,limit),new QueryWrapper<Repair>()
                .eq(repairState != null,"repair_state",repairState)
                .orderByAsc("repair_state","create_time"));
    }

    /**
     * @Description 根据前端传过来的实体类 获得对象 给页面赋值
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @Override
    public ServerResponse repairInfoSelectById(Repair repairInfo) {
        Repair selectRepairInfo = repairMapper.selectById(repairInfo);
        if (selectRepairInfo == null){
            return ServerResponse.sendError("找不到数据！");
        }
        return ServerResponse.sendSuccess(selectRepairInfo);
    }

    /**
     * @Description 修改维修状态
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @Override
    public ServerResponse editRepairInfo(Repair repairInfo) {
        if (repairInfo == null){
            return ServerResponse.sendError("数据不能为空");
        }
        if (repairInfo.getRepairState() == 0){
            return ServerResponse.sendError("缴费状态未进行修改！");
        }
        repairInfo.setUpdateTime(new Date());
        int updateNum = repairMapper.updateById(repairInfo);
        if (updateNum != 1){
            return ServerResponse.sendError("修改失败！");
        }
        return ServerResponse.sendSuccess("修改成功！");
    }

    /**
     * @Description 用户查看报修的信息
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @Override
    public IPage<Repair> userRepairList(Integer page, Integer limit, Integer userRepairState, String userPhone) {
        return repairMapper.selectPage(new Page<>(page,limit),new QueryWrapper<Repair>()
                .eq(userRepairState != null,"repair_state",userRepairState)
                .eq(!("").equals(userPhone) && userPhone != null,"user_phone",userPhone)
                .orderByAsc("repair_state","create_time"));
    }


    /**
     * @Description 用户添加维修信息
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @Override
    public ServerResponse addRepairInfo(Repair repair) {
        if (repair == null){
            return ServerResponse.sendError("提交的数据为空！");
        }
        repair.setCreateTime(new Date());
        repair.setUpdateTime(new Date());
        int insertNum = repairMapper.insert(repair);
        if (insertNum != 1){
            return ServerResponse.sendError("添加失败！");
        }
        return ServerResponse.sendSuccess("添加成功！");
    }

    /**
     * @Description 用户修改维修内容
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @Override
    public ServerResponse userEditRepairInfo(Repair repairInfo) {
        if (repairInfo == null){
            return ServerResponse.sendError("数据不能为空");
        }
        repairInfo.setUpdateTime(new Date());
        int updateNum = repairMapper.updateById(repairInfo);
        if (updateNum != 1){
            return ServerResponse.sendError("修改失败！");
        }
        return ServerResponse.sendSuccess("修改成功！");
    }

    /**
     * @Description 用户删除未维修的信息
     * @Author 王宝凯
     * @Date 2020/4/15
     **/
    @Override
    public ServerResponse deleteRepair(Integer id) {
        if (id == null){
            return ServerResponse.sendError();
        }
        int deleteByIdNum = repairMapper.deleteById(id);
        if (deleteByIdNum != 1){
            return ServerResponse.sendError("删除失败！");
        }
        return ServerResponse.sendSuccess("删除成功！");
    }
}
