package com.hspedu.hspliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.hspliving.commodity.entity.SkuInfoEntity;
import com.hspedu.hspliving.commodity.vo.SearchResult;

import java.util.Map;

/**
 * sku 信息
 *
 * @author ming
 * @email ming@gmail.com
 * @date 2024-05-13 16:51:29
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void saveSkuInfo(SkuInfoEntity skuInfoEntity);

    //进行分页查询-携带相应的检索条件
    PageUtils queryPageByCondition(Map<String, Object> params);


    //返回购头用户检索的结果
    SearchResult querySearchByCondition(Map<String, Object> params);


}

