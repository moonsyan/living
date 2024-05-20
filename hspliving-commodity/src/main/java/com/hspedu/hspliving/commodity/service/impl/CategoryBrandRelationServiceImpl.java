package com.hspedu.hspliving.commodity.service.impl;

import com.hspedu.hspliving.commodity.dao.BrandDao;
import com.hspedu.hspliving.commodity.dao.CategoryDao;
import com.hspedu.hspliving.commodity.entity.BrandEntity;
import com.hspedu.hspliving.commodity.entity.CategoryEntity;
import com.hspedu.hspliving.commodity.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.common.utils.Query;

import com.hspedu.hspliving.commodity.dao.CategoryBrandRelationDao;
import com.hspedu.hspliving.commodity.entity.CategoryBrandRelationEntity;
import com.hspedu.hspliving.commodity.service.CategoryBrandRelationService;

import javax.annotation.Resource;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {
    @Resource
    private BrandDao brandDao;
    @Resource
    private CategoryDao categoryDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveAll(CategoryBrandRelationEntity categoryBrandRelation) {

        // 保存分类和品牌的关系
        // 1. 查询品牌id
        Long brandId = categoryBrandRelation.getBrandId();
        // 2. 查询分类id
        Long catelogId = categoryBrandRelation.getCategoryId();

        BrandEntity brandEntity = brandDao.selectById(brandId);

        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);


        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCategoryName(categoryEntity.getName());

        this.save(categoryBrandRelation);
    }
@Resource
private CategoryBrandRelationDao categoryBrandRelationDao;
    @Resource
    private BrandService brandService;
    @Override
    public List<BrandEntity> getBrandsByCategoryId(Long categoryId) {
        List<CategoryBrandRelationEntity> categoryBrandRelationEntities = categoryBrandRelationDao.selectList(new QueryWrapper<CategoryBrandRelationEntity>().eq("category_id", categoryId));



        List<BrandEntity> collect = categoryBrandRelationEntities.stream().map(categoryBrandRelationEntity -> {
            Long brandId = categoryBrandRelationEntity.getBrandId();
            BrandEntity brandEntity = brandService.getById(brandId);
            return brandEntity;
        }).collect(Collectors.toList());

        return collect;

//        List<Long> collect = categoryBrandRelationEntities.stream().map(categoryBrandRelationEntity -> {
//            return categoryBrandRelationEntity.getBrandId();
//        }).collect(Collectors.toList());
//
//        List<BrandEntity> brandEntities = brandDao.selectList(new QueryWrapper<BrandEntity>().in("id", collect));

//        return brandEntities;
    }

}