package org.wbk.propertymanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wbk.propertymanagement.entity.Family;
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
public interface IFamilyService extends IService<Family> {

    /**
     * @Description 更新业主对应家人表的楼房号
     * @Author 王宝凯
     * @Date 2020/4/3
     **/
    int editFamilyInfo(String oldOwnerCard,String ownerCard, String buildingNumber);

    /**
     * @Description 根据业主的身份证号和楼房号 查询是否存在楼房信息
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    List<Family> selectFamilyList(String userCard, String buildingNumber);

    /**
     * @Description 根据业主的身份证号和楼房号 删除信息
     * @Author 王宝凯
     * @Date 2020/4/1
     **/
    int deletFamily(String userCard,String buildingNumber);

    /**
     * @Description  查找业主对应的家人信息
     * @Author 王宝凯
     * @Date 2020/4/8
     **/
    IPage<Family> selectFamilyInfo(Integer page, Integer limit, String userCard);

    /**
     * @Description  查看家人信息
     * @Author 王宝凯
     * @Date 2020/4/8
     **/
    IPage<Family> familyList(Integer page, Integer limit, String ownerCard);

    /**
     * @Description 删除家人信息
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    ServerResponse deleteFamily(Integer id);

    /**
     * @Description 根据家人表的id查询家人信息
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    ServerResponse selectByIdFamily(Integer id);

    /**
     * @Description 根据家人表的id修改家人信息
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    ServerResponse reviseFamilyInfo(Family familyInfo);

    /**
     * @Description 添加家人信息
     * @Author 王宝凯
     * @Date 2020/4/9
     **/
    ServerResponse addFamilyList(Family familyList);

}
