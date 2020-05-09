package org.wbk.propertymanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.wbk.propertymanagement.entity.Building;
import org.wbk.propertymanagement.entity.Family;
import org.wbk.propertymanagement.mapper.BuildingMapper;
import org.wbk.propertymanagement.mapper.FamilyMapper;
import org.wbk.propertymanagement.response.ServerResponse;
import org.wbk.propertymanagement.service.IFamilyService;
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
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper, Family> implements IFamilyService {
    @Autowired
    private FamilyMapper familyMapper;
    @Autowired
    private BuildingMapper buildingMapper;

    /**
     * @Description 更新业主对应家人表的楼房号
     * @Author 王宝凯
     * @Date 2020/4/3
     **/
    @Override
    public int editFamilyInfo(String oldOwnerCard,String ownerCard, String buildingNumber) {
        Family familyInfo = new Family();
        //条件
        QueryWrapper<Family> editQueryWrapper = new QueryWrapper<>();
        editQueryWrapper.eq(!("").equals(oldOwnerCard) && oldOwnerCard != null,"owner_card",oldOwnerCard);
        familyInfo.setOwnerCard(ownerCard);
        familyInfo.setBuildingNumber(buildingNumber);
        familyInfo.setUpdateTime(new Date());
        return familyMapper.update(familyInfo,editQueryWrapper);
    }

    /**
     * @Description 根据业主的身份证号和楼房号 查询是否存在楼房信息
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    @Override
    public List<Family> selectFamilyList(String userCard, String buildingNumber) {
        return familyMapper.selectList(new QueryWrapper<Family>()
                .eq(!("").equals(userCard) && userCard !=null,"owner_card",userCard)
                .eq(!("").equals(buildingNumber) && buildingNumber !=null,"building_number",buildingNumber));
    }

    /**
     * @Description 根据业主的身份证号和楼房号 删除信息
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    @Override
    public int deletFamily(String userCard,String buildingNumber) {
        QueryWrapper<Family> delFamQueWer = new QueryWrapper<>();
        delFamQueWer.eq(!"".equals(userCard) && userCard != null,"owner_card",userCard)
        .eq(!"".equals(buildingNumber) && buildingNumber != null,"building_number",buildingNumber);
        return familyMapper.delete(delFamQueWer);
    }

    /**
     * @Description  查找业主对应的家人信息
     * @Author 王宝凯
     * @Date 2020/4/8
     **/
    @Override
    public IPage<Family> selectFamilyInfo(Integer page, Integer limit, String userCard) {
        return familyMapper.selectPage(new Page<>(page,limit),new QueryWrapper<Family>()
                .eq(!("").equals(userCard) && userCard != null,"owner_card",userCard));
    }

    /**
     * @Description  查看家人信息
     * @Author 王宝凯
     * @Date 2020/4/8
     **/
    @Override
    public IPage<Family> familyList(Integer page, Integer limit, String ownerCard) {
        return familyMapper.selectPage(new Page<>(page,limit),new QueryWrapper<Family>()
                .eq(!("").equals(ownerCard) && ownerCard != null,"owner_card",ownerCard)
                .orderByDesc("owner_card"));
    }

    /**
     * @Description 删除家人信息
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    @Override
    public ServerResponse deleteFamily(Integer id) {
        int delNum = familyMapper.deleteById(id);
        if (delNum != 1){
            return ServerResponse.sendError("删除失败！");
        }
        return ServerResponse.sendSuccess("删除成功！");
    }

    /**
     * @Description 根据家人表的id查询家人信息
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    @Override
    public ServerResponse selectByIdFamily(Integer id) {
        Family familyInfo = familyMapper.selectById(id);
        if (familyInfo == null){
            return ServerResponse.sendError("查询失败！");
        }
        return ServerResponse.sendSuccess(familyInfo);
    }

    /**
     * @Description 根据家人表的id修改家人信息
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    @Override
    public ServerResponse reviseFamilyInfo(Family familyInfo) {
        if (familyInfo == null){
            return ServerResponse.sendError("家人信息为空！");
        }
        int reviseNum = familyMapper.updateById(familyInfo);
        if (reviseNum != 1){
            return ServerResponse.sendError("修改失败！");
        }
        return ServerResponse.sendSuccess("修改成功！");
    }

    /**
     * @Description 添加家人信息
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    @Override
    public ServerResponse addFamilyList(Family familyList) {
        if (familyList == null){
            return ServerResponse.sendError();
        }
        Building building = buildingMapper.selectById(familyList.getBuildingNumber());
        if (building == null){
            return ServerResponse.sendError();
        }
        familyList.setBuildingNumber(building.getBuildingNumber());
        familyList.setUpdateTime(new Date());
        familyList.setCreateTime(new Date());
        int insert = familyMapper.insert(familyList);
        if (insert != 1){
            return ServerResponse.sendError("添加失败！");
        }
        return ServerResponse.sendSuccess("添加成功！");
    }
}
