package com.hspedu.hspliving.commodity.service.impl;

import com.hspedu.hspliving.commodity.dao.SpuInfoDao;
import com.hspedu.hspliving.commodity.entity.SpuInfoEntity;
import com.hspedu.hspliving.commodity.vo.SearchResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.common.utils.Query;

import com.hspedu.hspliving.commodity.dao.SkuInfoDao;
import com.hspedu.hspliving.commodity.entity.SkuInfoEntity;
import com.hspedu.hspliving.commodity.service.SkuInfoService;

import javax.annotation.Resource;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuInfo(SkuInfoEntity skuInfoEntity) {
            this.baseMapper.insert(skuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {

        //brandId  catalogId  key  price.min  price.max
        QueryWrapper<SkuInfoEntity> skuInfoEntityQueryWrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        String catalogId = (String) params.get("catalogId");
        String brandId = (String) params.get("brandId");
        String min = (String) params.get("min");
        String max = (String) params.get("max");


        if(StringUtils.isNotBlank(key)){
            skuInfoEntityQueryWrapper.and((wq)->{
                wq.eq("sku_id",key).or().like("sku_name",key);
            });
        }
        if(StringUtils.isNotBlank(catalogId) && !"0".equalsIgnoreCase(catalogId)){
            skuInfoEntityQueryWrapper.eq("catalog_id",catalogId);
        }
        if(StringUtils.isNotBlank(brandId) && !"0".equalsIgnoreCase(brandId)){
            skuInfoEntityQueryWrapper.eq("brand_id",brandId);
        }
        if(StringUtils.isNotBlank(min) && !"0".equalsIgnoreCase(min)){
            skuInfoEntityQueryWrapper.ge("price",min);
        }
        if(StringUtils.isNotBlank(max) && !"0".equalsIgnoreCase(max)){
            skuInfoEntityQueryWrapper.le("price",max);
        }

        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                skuInfoEntityQueryWrapper
        );

        return new PageUtils(page);
    }

    @Resource
    private SpuInfoDao spuInfoDao;



    @Override
    public SearchResult querySearchByCondition(Map<String, Object> params) {

        String keyword = (String) params.get("keyword");
        String catalog3Id = (String) params.get("catalog3Id");
        String brandId = (String) params.get("brandId");

        // 检索到所有已经上架的spu 的id
        List<SpuInfoEntity> publishStatus = spuInfoDao.selectList(new QueryWrapper<SpuInfoEntity>().eq("publish_status", 1));

        //收集所有已经上架的spu 的id
        List<Long> collect = publishStatus.stream().map(spuInfoEntity -> {
            return spuInfoEntity.getId();
        }).collect(Collectors.toList());

        //http://localhost:9090/list.html?keyword=海信&catalog3Id=301&brandId=1

         //构建QueryWrapper,根据检索条件，返回结果
        QueryWrapper<SkuInfoEntity> skuInfoEntityQueryWrapper = new QueryWrapper<>();




        if(collect != null && collect.size() != 0){
            skuInfoEntityQueryWrapper.in("spu_id",collect);
        }else {
            //如果没有上架的商品，后面再进行处理。·
            //因为前台要解析的.
            SearchResult searchResult = new SearchResult();
            searchResult.setCommodity(new ArrayList<>());
            searchResult.setPageNum(1);
            searchResult.setTotal(0L);
            searchResult.setTotalPages(0);
            searchResult.setPageNavs(new ArrayList<>());
            searchResult.setKeyword(keyword == null ? "" : keyword);
            return searchResult;
        }




        if(StringUtils.isNotBlank(keyword)){
            skuInfoEntityQueryWrapper.and((wq)->{
                wq.eq("sku_id",keyword).or().like("sku_name",keyword);
            });
        }

        if(StringUtils.isNotBlank(catalog3Id) && !"0".equalsIgnoreCase(catalog3Id)){

            skuInfoEntityQueryWrapper.eq("catalog_id",catalog3Id);
        }
        if (StringUtils.isNotBlank(brandId) && !"0".equalsIgnoreCase(brandId)){
            skuInfoEntityQueryWrapper.eq("brand_id",brandId);
        }


        params.put("limit", "2");

        IPage<SkuInfoEntity> iPage = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                skuInfoEntityQueryWrapper
        );

        PageUtils page = new PageUtils(iPage);


        SearchResult searchResult = new SearchResult();

        searchResult.setCommodity((List<SkuInfoEntity>) page.getList());
        searchResult.setTotalPages(page.getTotalPage());
        searchResult.setPageNum((Integer)page.getCurrPage());
        searchResult.setTotal((long)page.getTotalCount());
        searchResult.setKeyword(keyword == null ? "" : keyword);

        ArrayList<Integer> pageNavs = new ArrayList<>();

        for (int i = 1; i <= page.getTotalPage(); i++) {
            pageNavs.add(i);
        }
        searchResult.setPageNavs(pageNavs);

        return searchResult;
    }

}