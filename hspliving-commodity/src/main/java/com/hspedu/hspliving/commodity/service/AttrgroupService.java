package com.hspedu.hspliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.hspliving.commodity.entity.AttrgroupEntity;
import com.hspedu.hspliving.commodity.vo.AttrGroupWithAttrsVO;

import java.util.List;
import java.util.Map;

/**
 * 家居商品属性分组
 *
 * @author ming
 * @email ming@gmail.com
 * @date 2024-05-04 10:19:15
 */
public interface AttrgroupService extends IService<AttrgroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //增加根据分类（第3级分类）+查询条件+分页的API接口
    PageUtils queryPage(Map<String, Object> params,Long categoryId);

    //根据分类categoryId返回该分类关联的属性组和这些属性组关联的基本属性
    List<AttrGroupWithAttrsVO> getAttrGroupWithAttrsByCategoryId(Long categoryId);
}

