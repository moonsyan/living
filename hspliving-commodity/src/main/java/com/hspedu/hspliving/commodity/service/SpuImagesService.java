package com.hspedu.hspliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.hspliving.commodity.entity.SpuImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * spu 图片集
 *
 * @author ming
 * @email ming@gmail.com
 * @date 2024-05-13 15:53:37
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //批星保存spU对应的图片集
    void saveSpuImages(Long id, List<String> images);
}

