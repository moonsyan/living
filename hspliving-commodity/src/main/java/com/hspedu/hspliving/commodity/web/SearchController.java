package com.hspedu.hspliving.commodity.web;

import com.hspedu.common.utils.PageUtils;
import com.hspedu.hspliving.commodity.entity.SkuInfoEntity;
import com.hspedu.hspliving.commodity.service.SkuInfoService;
import com.hspedu.hspliving.commodity.vo.SearchResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-05-14 11:42
 */
@Controller
public class SearchController {


    @Resource
    private SkuInfoService skuInfoService;
    /**
     * 1.家居网前台（购买用户）-检索页面
     * 2,如果将来检索的时候有检索条件，还需要进行处理.·
     * 3.用户提交的检索条件，我们封装到params
     * @param params
     * @return
     */
    @RequestMapping("/list.html")
    public String searchList(@RequestParam Map<String,Object> params, Model model){
        SearchResult searchResult = skuInfoService.querySearchByCondition(params);


        model.addAttribute("result", searchResult);
        return "list";
    }


}
