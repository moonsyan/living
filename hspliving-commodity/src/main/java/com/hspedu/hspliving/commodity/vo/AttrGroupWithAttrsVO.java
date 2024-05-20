package com.hspedu.hspliving.commodity.vo;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-05-12 9:14
 */

import com.baomidou.mybatisplus.annotation.TableId;
import com.hspedu.hspliving.commodity.entity.AttrEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 1。如果返回的数据，是单独返回当前实体类对象不能满足需求
 * 2.通常的解决方案可以增W0类！对象
 * 3.这V0类！对象，可以根据前端的需求，进行组合，也可以增加字段，也可以删除字段
 */
@Data
public class AttrGroupWithAttrsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 组名
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 说明
     */
    private String description;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类 id
     */
    private Long categoryId;

    private List<AttrEntity> attrs;
}
