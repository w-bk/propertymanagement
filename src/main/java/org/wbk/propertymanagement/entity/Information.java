package org.wbk.propertymanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *    消息实体类
 * </p>
 *
 * @author 王宝凯
 * @since 2020-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Information implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 消息标题
     */
    @TableField("information_title")
    private String informationTitle;

    /**
     * 消息内容
     */
    @TableField("information_content")
    private String informationContent;

    /**
     * 用户是否查看:0未查看  1已查看
     */
    @TableField("information_status")
    private Integer informationStatus;

    /**
     * 发送时间
     */
    @TableField("sent_time")
    private Date sentTime;

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
