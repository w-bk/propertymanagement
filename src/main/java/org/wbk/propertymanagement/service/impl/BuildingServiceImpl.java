package org.wbk.propertymanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.wbk.propertymanagement.entity.Building;
import org.wbk.propertymanagement.mapper.BuildingMapper;
import org.wbk.propertymanagement.service.IBuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王宝凯
 * @since 2020-03-31
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements IBuildingService {
    @Autowired
    private BuildingMapper buildingMapper;

    /**
     * @Description 根据用户修改前的手机号  更新房屋表中的手机号
     * @Author 王宝凯
     * @Date 2020/3/13
     **/
    @Override
    public int updateBuildPhone(Building buildInfo, String userPhone) {
        QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!("").equals(userPhone) && userPhone != null,"user_phone",userPhone);
        return buildingMapper.update(buildInfo,queryWrapper);
    }

    /**
     * @Description 删除用户操作  根据房屋id修改房屋的状态
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    @Override
    public int updateBuildSta(Building buildInfo) {
        //将房屋的状态进行修改
        buildInfo.setUserCard("");
        buildInfo.setUserPhone("");
        buildInfo.setBuildingStatus(0);
        buildInfo.setBuildingInfo("");
        buildInfo.setLeaseTime("");
        buildInfo.setDueTime("");
        buildInfo.setCreateTime(new Date());
        return buildingMapper.updateById(buildInfo);
    }

    /**
     * @Description 查询房屋信息
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    @Override
    public List<Building> selectBuild() {
        return buildingMapper.selectList(new QueryWrapper<Building>().eq("idel",0));
    }

    /**
     * @Description 根据用户身份证号和用户的手机号  查询楼房表中的楼房信息
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    @Override
    public Building selectBuildInfo(String userCard,String userPhone) {
        QueryWrapper<Building> idQueryWrapper = new QueryWrapper<>();
        idQueryWrapper.eq(!("").equals(userCard) && userCard != null,"user_card",userCard)
                .eq(!("").equals(userPhone) && userPhone != null,"user_phone",userPhone)
                .eq("idel",0);
        return buildingMapper.selectOne(idQueryWrapper);
    }

    /**
     * @Description 根据前端传过来的楼层表的id查询楼层号
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    @Override
    public Building selectByIdBuildInfo(String buildingId) {
        return buildingMapper.selectById(buildingId);
    }

    /**
     * @Description 根据房屋id修改房屋表中的信息
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    @Override
    public int editBuildInfo(Building buildInfo) {
        return buildingMapper.updateById(buildInfo);
    }

    /**
     * @Description 根据用户的身份证号查找房屋信息
     * @Author 王宝凯
     * @Date 2020/4/5
     **/
    @Override
    public IPage<Building> buildInfo(Integer page, Integer limit, String userCard) {
        QueryWrapper<Building> buildQueWer = new QueryWrapper<>();
        buildQueWer.eq(!("").equals(userCard) && userCard != null,"user_card",userCard)
                .orderByDesc("update_time")
                .eq("idel",0);
        return buildingMapper.selectPage(new Page<>(page,limit),buildQueWer);
    }
}
