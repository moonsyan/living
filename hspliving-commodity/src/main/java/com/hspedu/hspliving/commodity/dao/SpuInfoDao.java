package com.hspedu.hspliving.commodity.dao;

import com.hspedu.hspliving.commodity.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品 spu 信息
 * 
 * @author ming
 * @email ming@gmail.com
 * @date 2024-05-12 19:26:48
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {

    //方法：修改spu的发布状态
    void updateSpuStatus(@Param("spuId") Long spuId,@Param("statusCode") int statusCode);
}
