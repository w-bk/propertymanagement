package org.wbk.propertymanagement.entity;

import java.math.BigDecimal;
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
public class Cost implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 楼房号
     */
    @TableField("building_number")
    private String buildingNumber;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户手机号
     */
    @TableField("user_phone")
    private String userPhone;

    /**
     * 水费
     */
    @TableField("water_cost")
    private BigDecimal waterCost;

    /**
     * 水费缴费标志:0未交  1已交
     */
    @TableField("water_status")
    private Integer waterStatus;

    /**
     * 电费
     */
    @TableField("electric_cost")
    private BigDecimal electricCost;

    /**
     * 电费缴费标志:0未交  1已交
     */
    @TableField("electric_status")
    private Integer electricStatus;

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
