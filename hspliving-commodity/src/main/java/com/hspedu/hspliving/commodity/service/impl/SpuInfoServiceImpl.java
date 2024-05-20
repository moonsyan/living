package com.hspedu.hspliving.commodity.service.impl;

import com.hspedu.hspliving.commodity.dao.AttrDao;
import com.hspedu.hspliving.commodity.entity.*;
import com.hspedu.hspliving.commodity.service.*;
import com.hspedu.hspliving.commodity.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.common.utils.Query;

import com.hspedu.hspliving.commodity.dao.SpuInfoDao;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Resource
    private SpuInfoDescService spuInfoDescService;

    @Resource
    private SpuImagesService spuImagesService;

    @Resource
    private ProductAttrValueService productAttrValueService;
    @Resource
    private AttrService attrService;

    @Resource
    private SkuInfoService skuInfoService;

    @Resource
    private SkuImagesService skuImagesService;

    @Resource
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 保存SpuSaveV0对象/数据到表[会根据业务，将数据分别保存对应表]
     * <p>
     * 2.因为saveSpuInfo涉及到对多表的添加，因此需要进行事务管理，所以标识@Transactional
     *
     * @param spuSaveVO
     */
    @Transactional
    @Override
    public void saveSpuInfo(SpuSaveVO spuSaveVO) {
        //1.保存spu基本信息到表commodity_spu_info
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        //2.将spuSaveV0对象的属性值对拷到spuInfoEntity.对象[属性名需要有对应关系]
        BeanUtils.copyProperties(spuSaveVO, spuInfoEntity);

        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());

        //3.将spuInfoEntity保存到commodity_.spU_info,这里我们为了可读性和扩展性
        //单独的写一个方法。
        this.saveBaseSpuInfo(spuInfoEntity);


        List<String> decript = spuSaveVO.getDecript();
        SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
        //这里要设置给spuInfoDescEntity对象ispuId就是上面添加spuInfoEntity对应id
        spuInfoDescEntity.setSpuId(spuInfoEntity.getId());
        if (decript.size() == 0) {
            spuInfoDescEntity.setDecript("https://hspliving-10001.oss-cn-beijing.aliyuncs.com/blackUp.jpg");
        } else {//SpU有对应的介绍图片，就进行遍历，如果有多个图片U♪L,就使用，间隔
            spuInfoDescEntity.setDecript(String.join(",", decript));
        }
        spuInfoDescService.saveSpuInfoDesc(spuInfoDescEntity);

        //保存spU对应的图片集
        spuImagesService.saveSpuImages(spuInfoEntity.getId(), spuSaveVO.getImages());


        //保存spu的基本属性
        List<BaseAttrs> baseAttrs = spuSaveVO.getBaseAttrs();
        //遍历baseAttrs
        List<ProductAttrValueEntity> productAttrValueEntities = baseAttrs.stream().map(baseAttr -> {
            ProductAttrValueEntity productAttrValueEntity = new ProductAttrValueEntity();
            productAttrValueEntity.setSpuId(spuInfoEntity.getId());
            productAttrValueEntity.setQuickShow(baseAttr.getShowDesc());
            productAttrValueEntity.setAttrValue(baseAttr.getAttrValues());
            productAttrValueEntity.setAttrSort(0);

            //这里我们发现前端没有通过5so门把基本属性名携带孙W0对象，需要我们二次处理
            //通过attrId获取到属性
            AttrEntity attrEntity = attrService.getById(baseAttr.getAttrId());
            productAttrValueEntity.setAttrName(attrEntity.getAttrName());
            productAttrValueEntity.setAttrId(baseAttr.getAttrId());
            return productAttrValueEntity;
        }).collect(Collectors.toList());
        productAttrValueService.saveBatch(productAttrValueEntities);


        //保存 sku 的基本信息
        List<Skus> skus = spuSaveVO.getSkus();
        if (skus != null && skus.size() != 0) {
            skus.forEach(sku -> {
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                skuInfoEntity.setSpuId(spuInfoEntity.getId());

                String defaultImg = "https://hspliving-10001.oss-cn-beijing.aliyuncs.com/blackUp.jpg";

                //这里要设置给skuInfoEntity对象ispuId就是上面添加spuInfoEntity对应id
                List<Images> images = sku.getImages();
                for (Images image : images) {
                    if (image.getDefaultImg() == 1) {
                        defaultImg = image.getImgUrl();
                    }
                }
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
                skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
                skuInfoEntity.setPrice(sku.getPrice());
                skuInfoEntity.setSkuName(sku.getSkuName());
                skuInfoEntity.setSkuSubtitle(sku.getSkuSubtitle());
                skuInfoEntity.setSkuTitle(sku.getSkuTitle());
                skuInfoEntity.setSkuDefaultImg(defaultImg);
                //保存sku 信息到数据库
                skuInfoService.saveSkuInfo(skuInfoEntity);


                //保存skU的图片集信息到commodity_sku_images
                //一个SKU可以对应多个图片


                //1.从item(sku)取出图片集集合
                List<Images> skuImages = sku.getImages();
                //2.在收集skuImagesEntitie时，要过滤到imgUrl为空的情沉
                List<SkuImagesEntity> collect = skuImages.stream().map(image -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    skuImagesEntity.setSkuId(skuInfoEntity.getSkuId());
                    skuImagesEntity.setImgSort(0);
                    skuImagesEntity.setImgUrl(image.getImgUrl());
                    skuImagesEntity.setDefaultImg(image.getDefaultImg());
                    return skuImagesEntity;
                }).filter(img -> {
                    return StringUtils.isNotBlank(img.getImgUrl());
                }).collect(Collectors.toList());
                skuImagesService.saveBatch(collect);


                // 保存 sku 的销售属性
                //一个sKU可以对应多个销售属性

                List<Attr> attrs = sku.getAttr();

                if (attrs != null && attrs.size() != 0) {
                    //2.遍历attrs将数据封装到SkuSaleAttrValueEntity象中
                    List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attrs.stream().map(attr -> {
                        SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                        skuSaleAttrValueEntity.setAttrId(attr.getAttrId());
                        skuSaleAttrValueEntity.setAttrName(attr.getAttrName());
                        skuSaleAttrValueEntity.setAttrValue(attr.getAttrValue());
                        skuSaleAttrValueEntity.setSkuId(skuInfoEntity.getSkuId());
                        skuSaleAttrValueEntity.setAttrSort(0);
                        return skuSaleAttrValueEntity;
                    }).collect(Collectors.toList());
                    skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);
                }

            });
        }
    }

    @Override
    public void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity) {
        this.baseMapper.insert(spuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> spuInfoEntityQueryWrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        //我们定一个业务需求，如果视为id,就是等于，如果视为名称就是模糊查询
        if(StringUtils.isNotBlank(key)){
            spuInfoEntityQueryWrapper.and((wq)->{
                wq.eq("id",key).or().like("spu_name",key);
            });
        }
        String brandId = (String) params.get("brandId");
        String catalogId = (String) params.get("catalogId");
        String status = (String) params.get("status");

        if(StringUtils.isNotBlank(status)){
            spuInfoEntityQueryWrapper.eq("publish_status",status);
        }

        if(StringUtils.isNotBlank(catalogId) && !"0".equalsIgnoreCase(catalogId)){
            spuInfoEntityQueryWrapper.eq("catalog_id",catalogId);
        }
        if (StringUtils.isNotBlank(brandId) && !"0".equalsIgnoreCase(brandId)){
            spuInfoEntityQueryWrapper.eq("brand_id",brandId);
        }

        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                spuInfoEntityQueryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void up(Long spuId) {
       this.baseMapper.updateSpuStatus(spuId,1);
    }

    @Override
    public void down(Long spuId) {
        this.baseMapper.updateSpuStatus(spuId,2);
    }

}