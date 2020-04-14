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
public class Repair implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 报修人
     */
    @TableField("user_name")
    private String userName;

    /**
     * 报修人电话
     */
    @TableField("user_phone")
    private String userPhone;

    /**
     * 维修内容
     */
    @TableField("repair_locations")
    private String repairLocations;

    /**
     * 0未维修1已维修
     */
    @TableField("repair_state")
    private Integer repairState;

    /**
     * 处理结果
     */
    @TableField("process_result")
    private String processResult;

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
