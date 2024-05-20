package com.hspedu.hspliving.commodity.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.hspedu.hspliving.commodity.entity.AttrAttrgroupRelationEntity;
import com.hspedu.hspliving.commodity.entity.AttrgroupEntity;
import com.hspedu.hspliving.commodity.service.AttrAttrgroupRelationService;
import com.hspedu.hspliving.commodity.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hspedu.hspliving.commodity.entity.AttrEntity;
import com.hspedu.hspliving.commodity.service.AttrService;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.common.utils.R;

import javax.annotation.Resource;


/**
 * 商品属性表
 *
 * @author ming
 * @email ming@gmail.com
 * @date 2024-05-10 16:17:59
 */
@RestController
@RequestMapping("commodity/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @Resource
    private CategoryService categoryService;
    @Resource
    private AttrAttrgroupRelationService attrAttrgroupRelationService;
    /**
     * 列表
     */
    @RequestMapping("/list")

    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    @RequestMapping("/base/list/{categoryId}")

    public R baseAttrlist(@RequestParam Map<String, Object> params,@PathVariable("categoryId") Long categoryId) {
      //  PageUtils page = attrService.queryPage(params);
        PageUtils page = attrService.queryBaseAttrPage(params, categoryId);
        return R.ok().put("page", page);
    }

    @RequestMapping("/sale/list/{categoryId}")

    public R saleAttrlist(@RequestParam Map<String, Object> params,@PathVariable("categoryId") Long categoryId) {
        //  PageUtils page = attrService.queryPage(params);
        PageUtils page = attrService.querySaleAttrPage(params, categoryId);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")

    public R info(@PathVariable("attrId") Long attrId) {
        AttrEntity attr = attrService.getById(attrId);

        Long[] cascadedCategoryId = categoryService.getCascadedCategoryId(attr.getCategoryId());
        attr.setCascadedCategoryId(cascadedCategoryId);

        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationService.selectByAttrId(attrId);
        attr.setAttrGroupId(attrAttrgroupRelationEntity.getAttrGroupId());



        return R.ok().put("attr", attr);
    }




    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody AttrEntity attr) {
        //attrService.save(attr);

        //这个方法不适应，我们增加相应方法
        attrService.saveAttrAndRelation(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody AttrEntity attr) {
        attrService.updateById(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
