package com.hspedu.hspliving.commodity.web;

import com.hspedu.hspliving.commodity.entity.CategoryEntity;
import com.hspedu.hspliving.commodity.service.CategoryService;
import com.hspedu.hspliving.commodity.vo.Catalog2Vo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-05-14 9:32
 */
@Controller
public class IndexController {

    @Resource
    private CategoryService categoryService;
    // 响应用户请求首贡

    @GetMapping(value = {"/", "/index"})
    private String indexPage(Model model){
        //默认找到就是  "classpath\templates"   +   "index"  +  ".html"
        List<CategoryEntity> level1Categories = categoryService.getLevel1Categories();
        model.addAttribute("categories", level1Categories);

        return "index";
    }
    @GetMapping(value = "/index/catalog.json")
    @ResponseBody
    private  Map<String,List<Catalog2Vo>> getCatalogJson(){
        Map<String, List<Catalog2Vo>> catalogJson = categoryService.getCatalogJson();


        return catalogJson;
    }


}
