package com.hspedu.hspliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.hspliving.commodity.entity.CategoryEntity;
import com.hspedu.hspliving.commodity.vo.Catalog2Vo;

import java.util.List;
import java.util.Map;

/**
 * 商品分类表
 *
 * @author ming
 * @email ming@gmail.com
 * @date 2024-04-28 11:29:56
 */
public interface CategoryService extends IService<CategoryEntity> {
    //返回所有分类及其子分类（带有层级关系，即树形）
    List<CategoryEntity> listTree();

    PageUtils queryPage(Map<String, Object> params);

    //增加接口，返回cascadedCategoryId.数组，数据的形式[1,21,301]
    Long[] getCascadedCategoryId(Long categoryId);

    //获取 1 级分类的信息
    List<CategoryEntity>  getLevel1Categories();

    //获取二级和三级分类的信息
    Map<String,List<Catalog2Vo>> getCatalogJson();


}

