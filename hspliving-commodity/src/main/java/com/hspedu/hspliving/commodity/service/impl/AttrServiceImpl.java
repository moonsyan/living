package com.hspedu.hspliving.commodity.service.impl;

import com.hspedu.hspliving.commodity.dao.AttrAttrgroupRelationDao;
import com.hspedu.hspliving.commodity.dao.AttrgroupDao;
import com.hspedu.hspliving.commodity.entity.AttrAttrgroupRelationEntity;
import com.hspedu.hspliving.commodity.entity.AttrgroupEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.common.utils.Query;

import com.hspedu.hspliving.commodity.dao.AttrDao;
import com.hspedu.hspliving.commodity.entity.AttrEntity;
import com.hspedu.hspliving.commodity.service.AttrService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Resource
    AttrAttrgroupRelationDao attrAttrgroupRelationDao;
    @Resource
    AttrgroupDao attrgroupDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params,Long categoryId) {
        //根据实际情况，封装查询条件到QueryWrapper对象
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_type", 1);

        if(categoryId != 0){
            queryWrapper.eq("category_id", categoryId);
        }
        String  key = (String)params.get("key");
        if(StringUtils.isNotBlank(key)){
            queryWrapper.and((obj) -> {
                obj.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAttrAndRelation(AttrEntity attrEntity) {
        this.save(attrEntity);

        //保存商品属性（基本属性）和属性组的关联关系
        if(attrEntity.getAttrType() == 1 && attrEntity.getAttrGroupId() != null && attrEntity.getAttrGroupId() != 0){
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();

            attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
            attrAttrgroupRelationEntity.setAttrGroupId(attrEntity.getAttrGroupId());
            attrAttrgroupRelationEntity.setAttrSort(0);

            attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
        }

    }

    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {
        List<AttrAttrgroupRelationEntity> attrgroupRelationEntities = attrAttrgroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));

        //2.将entities的attrId收集到集合中  ->   使用前面我们讲过的jdk8  流式计算  stream api
        List<Long> attrIds = attrgroupRelationEntities.stream().map((attrAttrgroupRelationEntity) -> {
            return attrAttrgroupRelationEntity.getAttrId();
        }).collect(Collectors.toList());

        if(attrIds == null || attrIds.size() == 0){
            return null;
        }

        Collection<AttrEntity> attrEntities = this.listByIds(attrIds);

        return (List<AttrEntity>) attrEntities;
    }

    @Override
    public void deleteRelation(AttrAttrgroupRelationEntity[] attrgroupRelationEntities) {
        attrAttrgroupRelationDao.deleteBatchRelation(Arrays.asList(attrgroupRelationEntities));
    }


    /**
     *     //获取某个属性组可以关联的基本属性
     *     //1.如果某个基本属性己经和某个属性组关联了，就不能再关联
     *     //2.某个属性组可以关联的基本属性，必须是同一个分类
     * @return
     */
    @Override
    public PageUtils getAllRelationAttr(Map<String, Object> params, Long groupId) {
        //1.通过接收的属性组id attrgroupId,得到对应的categoryId
        AttrgroupEntity attrGroupEntity = attrgroupDao.selectById(groupId);
        Long categoryId = attrGroupEntity.getCategoryId();

        //   -----增加业务需求，排除已经关联的基本属性-----
        //(1)先得到当前categoryId的所有分组-commodity_attrgroup表

        List<AttrgroupEntity> attrgroupEntities = attrgroupDao.selectList(new QueryWrapper<AttrgroupEntity>().eq("category_id", categoryId));
        List<Long> attrGroupIds = attrgroupEntities.stream().map((attrgroupEntity) -> {
            return attrgroupEntity.getId();
        }).collect(Collectors.toList());

        //2)再commodity_.attr_attrgroup_relation中，去检索有哪些基本属性已经关联
        List<AttrAttrgroupRelationEntity> attrgroupRelationEntities = attrAttrgroupRelationDao.
                selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", attrGroupIds));

        List<Long>  attrIds = attrgroupRelationEntities.stream().map((attrAttrgroupRelationEntity) -> {
            return attrAttrgroupRelationEntity.getAttrId();
        }).collect(Collectors.toList());

        //2.通过得到categoryId,T获取到对应的基本属性
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId).eq("attr_type", 1);

        String key = (String) params.get("key");
        if(StringUtils.isNotBlank(key)){
            queryWrapper.eq("attr_id", key).or().like("attr_name", key);
        }
        if(attrIds != null && attrIds.size() != 0){
            queryWrapper.notIn("attr_id", attrIds);
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), queryWrapper);


        return new PageUtils(page);
    }

    @Override
    public PageUtils querySaleAttrPage(Map<String, Object> params, Long categoryId) {
        //根据实际情况，封装查询条件到QueryWrapper对象
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_type", 0);

        if(categoryId != 0){
            queryWrapper.eq("category_id", categoryId);
        }
        String  key = (String)params.get("key");
        if(StringUtils.isNotBlank(key)){
            queryWrapper.and((obj) -> {
                obj.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

}