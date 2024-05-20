package com.hspedu.hspliving.commodity.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hspedu.hspliving.commodity.entity.BrandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hspedu.hspliving.commodity.entity.CategoryBrandRelationEntity;
import com.hspedu.hspliving.commodity.service.CategoryBrandRelationService;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.common.utils.R;



/**
 * 品牌分类关联表
 *
 * @author ming
 * @email ming@gmail.com
 * @date 2024-05-04 18:47:03
 */
@RestController
@RequestMapping("commodity/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;


    @RequestMapping("/brands/list")
    public R relationBrandsList(@RequestParam(value = "catId",required = true) Long categoryId){
        List<BrandEntity> brandsByCategoryId = categoryBrandRelationService.getBrandsByCategoryId(categoryId);
        return R.ok().put("data", brandsByCategoryId);
    }

    /**
     * 列表
     * 列表-根据brandId返回品牌和分类关联的记录
     */
    @RequestMapping("/brand/list")
    public R listByBrandId(@RequestParam("brandId") Long brandId){
       // PageUtils page = categoryBrandRelationService.queryPage(params);

        List<CategoryBrandRelationEntity> brandRelationEntityList =
                categoryBrandRelationService.list(
                        new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId));


        return R.ok().put("data", brandRelationEntityList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Long id){
		CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
		//categoryBrandRelationService.save(categoryBrandRelation);
        System.out.println("categoryBrandRelation = " + categoryBrandRelation);
		categoryBrandRelationService.saveAll(categoryBrandRelation);

        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
		categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
