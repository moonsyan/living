package com.hspedu.hspliving.commodity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-05-14 10:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalog2Vo {
    /**
     * 一级分类的 id
     */
    private String catalog1Id;

    /**
     * 二级分类的信息
     */
    private String id;
    private String name;

    /**
     * 三级子分类
     */
    private List<Category3Vo> catalog3List;
    /**
     * 三级分类 vo
     */

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Category3Vo {
        /**
         * 父分类、二级分类 id
         */
        private String catalog2Id;
        private String id;
        private String name;
    }
}
