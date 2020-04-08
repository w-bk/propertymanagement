package org.wbk.propertymanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王宝凯
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Building implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户的的身份证号
     */
    @TableField("user_card")
    private String userCard;

    /**
     * 用户的电话号码
     */
    @TableField("user_phone")
    private String userPhone;

    /**
     * 楼房号
     */
    @TableField("building_number")
    private String buildingNumber;

    /**
     * 房屋状态:0未入住  1已入住
     */
    @TableField("building_status")
    private Integer buildingStatus;

    /**
     * 房屋信息（自住/出租）
     */
    @TableField("building_info")
    private String buildingInfo;

    /**
     * 房屋面积
     */
    @TableField("building_size")
    private String buildingSize;

    /**
     * 出租时间
     */
    @TableField("lease_time")
    private String leaseTime;

    /**
     * 到期时间
     */
    @TableField("due_time")
    private String dueTime;

    /**
     * 房屋描述
     */
    @TableField("remarks")
    private String remarks;

    /**
     * 0启用、1停用
     */
    @TableField("idel")
    private Integer idel;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

}
