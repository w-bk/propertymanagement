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
public class Family implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 楼房号
     */
    @TableField("building_number")
    private String buildingNumber;

    /**
     * 户主的身份证号
     */
    @TableField("owner_card")
    private String ownerCard;

    /**
     * 家人姓名
     */
    @TableField("family_name")
    private String familyName;

    /**
     * 家人性别
     */
    @TableField("family_sex")
    private String familySex;

    /**
     * 家人的电话号码
     */
    @TableField("family_phone")
    private String familyPhone;

    /**
     * 家人的身份证号
     */
    @TableField("family_card")
    private String familyCard;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

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
