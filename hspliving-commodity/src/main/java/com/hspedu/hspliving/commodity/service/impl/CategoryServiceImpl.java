package com.hspedu.hspliving.commodity.service.impl;

import com.hspedu.hspliving.commodity.vo.Catalog2Vo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.common.utils.Query;

import com.hspedu.hspliving.commodity.dao.CategoryDao;
import com.hspedu.hspliving.commodity.entity.CategoryEntity;
import com.hspedu.hspliving.commodity.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    //核心方法
    //这里我们会使用到的Java8的流式计算(stream api)+递归操作（有一定难度）


    @Override
    public List<CategoryEntity> listTree() {


        /**
         * CategoryServiceImpl  所继承的  ServiceImpl
         * public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {
         *     protected Log log = LogFactory.getLog(this.getClass());
         *     @Autowired
         *     protected M baseMapper;
         *
         *     public ServiceImpl() {
         *     }
         */
        List<CategoryEntity> entities = baseMapper.selectList(null);

        // 组装层级树形结构。  使用到java8的stream api+  递归操作
        //1. 对 categoryEntities 进行过滤，返回一级分类
        //2.进行mαp映轂操作，给每个分类设置对应的子分类  ,这个过程会使用到递归
        //3. 进行sort排序
        //4.将处理好的数据收集，返回到集合中

        List<CategoryEntity> collect = entities.stream().filter(categoryEntity -> {
            return categoryEntity.getParentId().longValue() == 0;
        }).map(category -> {
            // 设置子分类
            category.setChildrenCategories(getChildrensCategories(entities, category));
            return category;

        }).sorted((categoryEntity1, categoryEntity2) -> {
            //进行排序sorted操作，按照sort的升序排列

            return (categoryEntity1.getSort() == null ? 0 : categoryEntity1.getSort()) -
                    (categoryEntity2.getSort() == null ? 0 : categoryEntity2.getSort());
        }).collect(Collectors.toList());


        return collect;
    }

    //递归查询所有类型的子分类
    //该方法的任务就是把下0ot下的所有子分类的层级关系组织好（有多少级，就处理多少级），并返回

    /**
     * @param categoryEntities 所有的分类信息
     * @param root
     * @return
     */
    private List<CategoryEntity> getChildrensCategories(List<CategoryEntity> categoryEntities, CategoryEntity root) {
        List<CategoryEntity> chirdren = categoryEntities.stream().filter(categoryEntity -> {
            ////这里有问题，categoryEntity.getParentId()root.getId()
            ////返回的是Long包装类型 ==  表示是对象比较
            ////return categoryEntity.getParentId()=root.getId();

            //  所以在  -128 ~ 127之间 只用 == 是可以的，而超过127之后，每次都是new 了一个新的对象，所以会出问题
            //public static Long valueOf(long l) {
            //        final int offset = 128;
            //        if (l >= -128 && l <= 127) { // will cache
            //            return LongCache.cache[(int)l + offset];
            //        }
            //        return new Long(l);
            //    }
            // return categoryEntity.getParentId() == root.getId();
            return categoryEntity.getParentId().longValue() == root.getId().longValue();
        }).map(categoryEntity -> {
            // 找到子分类并设置
            categoryEntity.setChildrenCategories(getChildrensCategories(categoryEntities, categoryEntity));
            return categoryEntity;
        }).sorted((categoryEntity1, categoryEntity2) -> {
            return (categoryEntity1.getSort() == null ? 0 : categoryEntity1.getSort()) -
                    (categoryEntity2.getSort() == null ? 0 : categoryEntity2.getSort());
        }).collect(Collectors.toList());
        return chirdren;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 1.该方法返回cascadedCategoryId,数据形式星[1,21,301]
     *
     * @param categoryId
     * @return
     */
    @Override
    public Long[] getCascadedCategoryId(Long categoryId) {
        List<Long> cassadedCategoryId = new ArrayList<>();
        getParentCategoryId(categoryId, cassadedCategoryId);

        Collections.reverse(cassadedCategoryId);
        Long[] array = cassadedCategoryId.toArray(new Long[cassadedCategoryId.size()]);
        return array;
    }

    @Override
    public List<CategoryEntity> getLevel1Categories() {
        QueryWrapper<CategoryEntity> categoryEntityQueryWrapper = new QueryWrapper<>();
        categoryEntityQueryWrapper.eq("parent_id", 0l);
        List<CategoryEntity> categoryEntities = this.baseMapper.selectList(categoryEntityQueryWrapper);

        return categoryEntities;
    }

    // 根据父级的分类id,返回对应的分类数据
    private List<CategoryEntity> getCataByParentId(List<CategoryEntity> categoryEntities, Long parentId) {
        return categoryEntities.stream().filter(categoryEntity -> {
            return categoryEntity.getParentId().equals(parentId);
        }).collect(Collectors.toList());
    }

    /**
     * 返回二级分类（包含三级分类）的数据-按照规定的格式    Map<String,List<Catalog2Vo>>
     * 这里我们会使用到流式计算的集合->map
     * 有一定雅度有层级关系工
     * 老师分析：我们需要一个铺助方法，parentId.获现对应的下一级的信息
     *
     * @param level1CategoryId
     * @return
     */
    @Override
    public Map<String, List<Catalog2Vo>> getCatalogJson() {

        List<CategoryEntity> categoryEntities = this.baseMapper.selectList(null);
        //-从一级分类开始-》二级分类-》三级分类->

        List<CategoryEntity> level1Categories = getCataByParentId(categoryEntities, 0l);

        //-遍历一级分类--->最终得到--》Map<String,List<Catalog2Vo>>

        Map<String, List<Catalog2Vo>> map = level1Categories.stream().collect(Collectors.toMap(
                k ->
                        k.getId().toString(),
                v -> {
                    List<Catalog2Vo> catalog2Vos = new ArrayList<>();

                    //这里就需要业务处理List<Catalog2Vo>
                    List<CategoryEntity> level2Categories = getCataByParentId(categoryEntities, v.getId());

                    if (level2Categories != null && level2Categories.size() != 0) {
                        catalog2Vos = level2Categories.stream().map(l2 -> {

                            Catalog2Vo catalog2Vo = new Catalog2Vo(v.getId().toString()
                                    , l2.getId().toString()
                                    , l2.getName().toString(),
                                    null);

                            //三级分类的信息
                            List<CategoryEntity> level3Categories = getCataByParentId(categoryEntities, l2.getId());
                            if (level3Categories != null && level3Categories.size() != 0) {

                                List<Catalog2Vo.Category3Vo> collect = level3Categories.stream().map(l3 -> {
                                    Catalog2Vo.Category3Vo category3Vo = new Catalog2Vo.Category3Vo();
                                    category3Vo.setCatalog2Id(l2.getId().toString());
                                    category3Vo.setId(l3.getId().toString());
                                    category3Vo.setName(l3.getName().toString());
                                    return category3Vo;
                                }).collect(Collectors.toList());

                                catalog2Vo.setCatalog3List(collect);
                            }

                            return catalog2Vo;
                        }).collect(Collectors.toList());

                    }
                    return catalog2Vos;

                }));

        return map;
    }

    //编写方法，更加categoryId递归的查找层级关系
    //比如我们接收到categoryId301->parentId->.···直到parentId
    public List<Long> getParentCategoryId(Long categoryId, List<Long> categories) {
        categories.add(categoryId);
        CategoryEntity categoryEntity = this.getById(categoryId);
        if (!categoryEntity.getParentId().equals(0l)) {
            getParentCategoryId(categoryEntity.getParentId(), categories);
        }
        return categories;
    }

}