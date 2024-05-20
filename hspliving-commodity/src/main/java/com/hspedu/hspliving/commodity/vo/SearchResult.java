package com.hspedu.hspliving.commodity.vo;

import com.hspedu.hspliving.commodity.entity.SkuInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-05-14 15:36
 */

/**
 *   SearchResult :就是检索返回VO，根据业务需求来设计
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResult {

    /**
     * 查询到的所有家居商品信息
     */
    private List<SkuInfoEntity> commodity;
    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 总页码
     */
    private Integer totalPages;

    //分页导航条
    private List<Integer> pageNavs;

    private String keyword;
}
