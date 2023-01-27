package com.moore.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ：imoore
 * @date ：created in 2023/1/27 18:00
 * @description：基础实体对象
 * @version: v
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    @TableField("id")
    private Integer id;

    @ApiModelProperty("状态1在用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("状态切换时间")
    @TableField(value = "status_changed_time")
    private LocalDateTime statusChangedTime;

    @ApiModelProperty("备注")
    @TableField("notes")
    private String notes;

    @ApiModelProperty("创建Id")
    @TableField(value = "created_id")
    private Integer createdId;

    @ApiModelProperty("创建时间")
    @TableField(value = "created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty("修改Id")
    @TableField(value = "modified_id")
    private Integer modifiedId;

    @ApiModelProperty("修改时间")
    @TableField(value = "modified_time")
    private LocalDateTime modifiedTime;


}
