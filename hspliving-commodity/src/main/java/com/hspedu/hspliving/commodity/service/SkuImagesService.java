package com.hspedu.hspliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.hspliving.commodity.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku 图片
 *
 * @author ming
 * @email ming@gmail.com
 * @date 2024-05-13 17:43:39
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

