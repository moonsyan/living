package com.hspedu.hspliving.commodity.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.hspedu.common.valid.SaveGroup;
import com.hspedu.common.valid.UpdateGroup;
import com.hspedu.common.valid.UpdateIsShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hspedu.hspliving.commodity.entity.BrandEntity;
import com.hspedu.hspliving.commodity.service.BrandService;
import com.hspedu.common.utils.PageUtils;
import com.hspedu.common.utils.R;



/**
 * 家居品牌
 *
 * @author ming
 * @email ming@gmail.com
 * @date 2024-05-01 08:41:17
 */
@RestController
@RequestMapping("commodity/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("commodity:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        System.out.println("params = " + params);
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("commodity:brand:info")
    public R info(@PathVariable("id") Long id){
		BrandEntity brand = brandService.getById(id);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     * I.@Validated注解的作用就是启用BrandEntity字段校验
     * 2.注解如果没有写@Validated这个校验规则不生效
     * 3.BindingResult result:springboot会将校验的错误放入到result
     * 4.程序员可以通过BindingResult result将校验得到错误取出，然后进行相应处理
     * 5.因为我]要将数据校验异常/错误交给全局异常处理器HsplivingExceptionControllerAdvice
     * ,所以这里saVe方法就注销相关的校验代码.
     *
     *
     *  @Validated({SaveGroup.class}) : 表示调用save时，进行参数校验，使用的是SaveGroup的校验规则
     */
    @RequestMapping("/save")
   // @RequiresPermissions("commodity:brand:save")
    public R save(@Validated({SaveGroup.class}) @RequestBody BrandEntity brand  /*, BindingResult bindingResult*/){
//        Map<Object,Object> map = new HashMap<>();
//        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//        if(bindingResult.hasErrors()){
//            fieldErrors.forEach(fieldError -> {
//                map.put(fieldError.getField(),fieldError.getDefaultMessage());
//            });
//            return R.error(400, "提交的数据不合法！").put("data",map);
//        }else {
            brandService.save(brand);

            return R.ok();
//        }
    }

    /**
     * 修改
     * @Validated(UpdateGroup.class) 表示进行修改/调用update.方法时，会进行参数校验，使用的是UpdateGroup 组的校验规则
     */
    @RequestMapping("/update")
   // @RequiresPermissions("commodity:brand:update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand){
		brandService.updateById(brand);

        return R.ok();
    }

    @RequestMapping("/update/isShow")
    public R updateisShow(@Validated(UpdateIsShow.class) @RequestBody BrandEntity brand){
        brandService.updateById(brand);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("commodity:brand:delete")
    public R delete(@RequestBody Long[] ids){
		brandService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
