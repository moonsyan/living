package com.hspedu.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-05-04 9:16
 */

/**
 * 1.EnumConstraintValiator 是真正的校验器，即校验的逻辑是写在这里的
 * 2.EnumConstraintValiator 必须实现 ConstraintValidator 接口
 * 3.<EnumValidate,Integer> 表示该校验器是针对  @EnumValidate 传入的Integer类型进行数据的校验
 */
public class EnumConstraintValidator implements ConstraintValidator<EnumValidate,Integer> {
    private Set<Integer> set = new HashSet<>();
    private String regexp = "";
    @Override
    public void initialize(EnumValidate constraintAnnotation) {
        //这里我们测试一下，看看是否能够得到注解传入你values
        int[] values = constraintAnnotation.values();
        for (int value : values) {
           set.add(value);
        }

        regexp = constraintAnnotation.regexp();

    }

    //如果返回trUe表示验证成功-通过
    //返回false,表示验证失败-没有通过
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        //System.out.println("接收到的表单提交的value " + integer);
        return set.contains(value);


//        return value.toString().matches(regexp);
    }
}
