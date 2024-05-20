package com.hspedu.hspliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.hspliving.commodity.entity.BrandEntity;
import com.hspedu.hspliving.commodity.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联表
 *
 * @author ming
 * @email ming@gmail.com
 * @date 2024-05-04 18:47:03
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void  saveAll(CategoryBrandRelationEntity categoryBrandRelation);

    //根据categoryId返回该分类关联的品牌信息-集合
    List<BrandEntity> getBrandsByCategoryId(Long categoryId);
}

