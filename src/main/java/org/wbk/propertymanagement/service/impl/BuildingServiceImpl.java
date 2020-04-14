package org.wbk.propertymanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.wbk.propertymanagement.entity.Building;
import org.wbk.propertymanagement.entity.Family;
import org.wbk.propertymanagement.mapper.BuildingMapper;
import org.wbk.propertymanagement.response.ServerResponse;
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

    /**
     * @Description 查询楼房表中的业主信息  用来显示楼房号
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    @Override
    public ServerResponse selectBuildNumber() {
        List<Building> selectList = buildingMapper.selectList(new QueryWrapper<Building>()
                .eq("building_info","自住"));
        if (selectList.isEmpty()){
            return ServerResponse.sendError("找不到数据！");
        }
        return ServerResponse.sendSuccess(selectList);
    }

    /**
     * @Description 显示房屋列表信息 根据房屋状态进行搜索查询
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @Override
    public IPage<Building> buildingList(Integer page, Integer limit, Integer buildingStatus) {
        return buildingMapper.selectPage(new Page<>(page,limit),new QueryWrapper<Building>()
                .eq(buildingStatus != null,"building_status",buildingStatus)
                .orderByDesc("building_status"));
    }

    /**
     * @Description 停用房屋操作  修改房屋的使用状态
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @Override
    public ServerResponse stopBuildingInfo(Integer id) {
        if (id == null){
            return ServerResponse.sendError();
        }
        Building building = buildingMapper.selectById(id);
        building.setIdel(1);
        building.setUpdateTime(new Date());
        int update = buildingMapper.updateById(building);
        if (update != 1){
            return ServerResponse.sendError("停用失败！");
        }
        return ServerResponse.sendSuccess("停用成功！");
    }

    /**
     * @Description 启用房屋操作  修改房屋的使用状态
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @Override
    public ServerResponse enableBuildingInfo(Integer id) {
        if (id == null){
            return ServerResponse.sendError();
        }
        Building buildingInfo = buildingMapper.selectById(id);
        buildingInfo.setIdel(0);
        buildingInfo.setUpdateTime(new Date());
        int update = buildingMapper.updateById(buildingInfo);
        if (update != 1){
            return ServerResponse.sendError("启用失败！");
        }
        return ServerResponse.sendSuccess("启用成功！");
    }

    /**
     * @Description 编辑房屋操作
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @Override
    public ServerResponse editBuildingInfo(Building buildingInfo) {
        if (buildingInfo == null){
            return ServerResponse.sendError("修改的数据为空！");
        }
        buildingInfo.setUpdateTime(new Date());
        int updateNum = buildingMapper.updateById(buildingInfo);
        if (updateNum != 1){
            return ServerResponse.sendError("修改失败！");
        }
        return ServerResponse.sendSuccess("修改成功！");
    }

    /**
     * @Description 添加房屋操作
     * @Author 王宝凯
     * @Date 2020/4/14
     **/
    @Override
    public ServerResponse addBuildingInfo(Building buildingInfo) {
        if (buildingInfo == null){
            return ServerResponse.sendError("添加的数据为空！");
        }
        Building building = buildingMapper.selectOne(new QueryWrapper<Building>()
                .eq(!("").equals(buildingInfo.getBuildingNumber()) && buildingInfo.getBuildingNumber() != null,
                        "building_number", buildingInfo.getBuildingNumber()));
        if (building != null){
            return ServerResponse.sendError("添加的楼房号已存在！");
        }
        buildingInfo.setCreateTime(new Date());
        buildingInfo.setUpdateTime(new Date());
        int insertNum = buildingMapper.insert(buildingInfo);
        if (insertNum != 1){
            return ServerResponse.sendError("添加失败！");
        }
        return ServerResponse.sendSuccess("添加成功！");
    }

    @Override
    public IPage<Building> userBuildingList(Integer page, Integer limit, String userPhone) {
        return buildingMapper.selectPage(new Page<>(page,limit),new QueryWrapper<Building>()
                .eq(!("").equals(userPhone) && userPhone != null,"user_phone",userPhone));
    }
}
