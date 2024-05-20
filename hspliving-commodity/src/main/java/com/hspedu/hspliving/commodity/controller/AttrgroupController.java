package com.hspedu.hspliving.commodity.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.hspedu.hspliving.commodity.dao.AttrgroupDao;
import com.hspedu.hspliving.commodity.entity.AttrAttrgroupRelationEntity;
import com.hspedu.hspliving.commodity.entity.AttrEntity;
import com.hspedu.hspliving.commodity.service.AttrAttrgroupRelationService;
import com.hspedu.hspliving.commodity.service.AttrService;
import com.hspedu.hspliving.commodity.service.CategoryService;
import com.hspedu.hspliving.commodity.vo.AttrGroupWithAttrsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hspedu.hspliving.commodity.entity.AttrgroupEntity;
import com.hspedu.hspliving.commodity.service.AttrgroupService;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.common.utils.R;

import javax.annotation.Resource;


/**
 * 家居商品属性分组
 *
 * @author ming
 * @email ming@gmail.com
 * @date 2024-05-04 10:19:15
 */
@RestController
@RequestMapping("commodity/attrgroup")
public class AttrgroupController {
    @Autowired
    private AttrgroupService attrgroupService;

    @Resource
    private CategoryService categoryService;
    @Resource
    private AttrService attrService;

    @Resource
    private AttrAttrgroupRelationService attrAttrgroupRelationService;


    @RequestMapping("{catalogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("catalogId") Long catalogId){
        List<AttrGroupWithAttrsVO> attrGroupWithAttrsByCategoryId = attrgroupService.getAttrGroupWithAttrsByCategoryId(catalogId);
        return R.ok().put("data", attrGroupWithAttrsByCategoryId);
    }

    @RequestMapping("/attr/relation")
    public R attrRelation(@RequestBody List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntities){

        attrAttrgroupRelationService.saveBatch(attrAttrgroupRelationEntities);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")

    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrgroupService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/list/{categoryId}")
    public R list(@RequestParam Map<String, Object> params,@PathVariable("categoryId") Long categoryId){
        PageUtils page = attrgroupService.queryPage(params,categoryId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Long id){
		AttrgroupEntity attrgroup = attrgroupService.getById(id);
        Long[] cascadedCategoryId = categoryService.getCascadedCategoryId(attrgroup.getCategoryId());
        attrgroup.setCascadedCategoryId(cascadedCategoryId);
        return R.ok().put("attrgroup", attrgroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody AttrgroupEntity attrgroup){
		attrgroupService.save(attrgroup);

        return R.ok();
    }

    @RequestMapping("/{attrGroupId}//attr/relation")
    public R attrRelation(@PathVariable("attrGroupId") Long attrGroupId) {
        List<AttrEntity> entities = attrService.getRelationAttr(attrGroupId);

        return R.ok().put("data", entities);
    }



    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody AttrgroupEntity attrgroup){
		attrgroupService.updateById(attrgroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		attrgroupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 响应批量删除属性组和属性的关联关系
     */
    @RequestMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrAttrgroupRelationEntity[] attrgroupRelationEntities){
        System.out.println("attrgroupRelationEntities = "+ attrgroupRelationEntities);
        attrService.deleteRelation(attrgroupRelationEntities);
        return R.ok();
    }

    @RequestMapping("/{attrGroupId}/attr/allowrelation")
    public R attrAllowRelation(@PathVariable("attrGroupId") Long attrGroupId,@RequestParam Map<String,Object> params) {
        PageUtils page = attrService.getAllRelationAttr(params, attrGroupId);
        return R.ok().put("page", page);
    }

}
