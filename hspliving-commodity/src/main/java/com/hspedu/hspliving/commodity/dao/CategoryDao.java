package com.hspedu.hspliving.commodity.dao;

import com.hspedu.hspliving.commodity.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类表
 * 
 * @author ming
 * @email ming@gmail.com
 * @date 2024-04-28 11:29:56
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
