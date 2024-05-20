package com.hspedu.hspliving.commodity.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.hspedu.hspliving.commodity.entity.AttrEntity;
import com.hspedu.hspliving.commodity.service.AttrService;
import com.hspedu.hspliving.commodity.vo.AttrGroupWithAttrsVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.common.utils.Query;

import com.hspedu.hspliving.commodity.dao.AttrgroupDao;
import com.hspedu.hspliving.commodity.entity.AttrgroupEntity;
import com.hspedu.hspliving.commodity.service.AttrgroupService;

import javax.annotation.Resource;


@Service("attrgroupService")
public class AttrgroupServiceImpl extends ServiceImpl<AttrgroupDao, AttrgroupEntity> implements AttrgroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrgroupEntity> page = this.page(
                new Query<AttrgroupEntity>().getPage(params),
                new QueryWrapper<AttrgroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long categoryId) {
        //增加根据分类（第3级分类）+查询条件+分页的API接口
        String key = (String) params.get("key");

        //根据实际情况，封装查询条件到QueryWrapper对象
        QueryWrapper<AttrgroupEntity> queryWrapper = new QueryWrapper<>();

        // 判断key是否携带的有查询条件
        if (StringUtils.isNotBlank(key)) {
//            queryWrapper.eq("id", key).or().like("name", key);

            queryWrapper.and((obj) -> {
                obj.eq("id", key).or().like("name", key);
            });
        }

        //我们在处理是否需要封装categoryId检索条件
        //这里先设置一个业务规则：categoryId==0表示在查询属性组的时候，不加入categoryId
        if (categoryId != 0) {
            //根据categoryId查询
            queryWrapper.eq("category_id", categoryId);
        }

        IPage<AttrgroupEntity> page = this.page(
                new Query<AttrgroupEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);


    }

    @Resource
    private AttrService attrService;
    @Override
    public List<AttrGroupWithAttrsVO> getAttrGroupWithAttrsByCategoryId(Long categoryId) {
        //1.根据categoryId得到该分类关联的所有属性组
        List<AttrgroupEntity> attrgroupEntities = this.list(new QueryWrapper<AttrgroupEntity>().eq("category_id", categoryId));

        //2.根据前面查询到的attrgroupEntities,对其进行遍历，将各个属性组关联的
        //基本属性查询到，并封装到AttrGroupWithAttrsVo集会中，返回->依然使用流式计算
        List<AttrGroupWithAttrsVO> collect = attrgroupEntities.stream().map((attrgroupEntity) -> {
            //(1)先创建一个AttrGroupWithAttrsVo时象
            AttrGroupWithAttrsVO attrGroupWithAttrsVO = new AttrGroupWithAttrsVO();

            ///(2)attrgroupEntity 对象属性拷贝到attrGroupW/ithAttrsVo
            BeanUtils.copyProperties(attrgroupEntity, attrGroupWithAttrsVO);

            //(3)通过属性的id,获取到该属性组关联的所有基本属性
            List<AttrEntity> relationAttr = attrService.getRelationAttr(attrgroupEntity.getId());

            attrGroupWithAttrsVO.setAttrs(relationAttr);

            return attrGroupWithAttrsVO;

        }).collect(Collectors.toList());

        return collect;
    }

}