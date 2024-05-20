package com.hspedu.hspliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.hspliving.commodity.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu 基本属性值
 *
 * @author ming
 * @email ming@gmail.com
 * @date 2024-05-13 16:20:43
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //保存基本属性 - 支持批量添加
    void saveProductAttr(List<ProductAttrValueEntity> productAttrValueEntities);

}

