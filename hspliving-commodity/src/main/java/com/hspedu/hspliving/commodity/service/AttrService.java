package com.hspedu.hspliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.hspliving.commodity.entity.AttrAttrgroupRelationEntity;
import com.hspedu.hspliving.commodity.entity.AttrEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品属性表
 *
 * @author ming
 * @email ming@gmail.com
 * @date 2024-05-10 16:17:59
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryBaseAttrPage(Map<String, Object> params,Long categoryId);
    PageUtils querySaleAttrPage(Map<String, Object> params,Long categoryId);

    /**
     * 增加接口，完成保存商品属性（基本属性）的同时，要需要保持该基本属性和属性组的关联关系
     */
    void  saveAttrAndRelation(AttrEntity attrEntity);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrAttrgroupRelationEntity[] attrgroupRelationEntities);

    //获取某个属性组可以关联的基本属性
    //1.如果某个基本属性己经和某个属性组关联了，就不能再关联
    //2.某个属性组可以关联的基本属性，必须是同一个分类
    PageUtils getAllRelationAttr(Map<String, Object> params,Long groupId);

}

