package com.hspedu.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-05-04 9:11
 */

/**
 * 1.创建自定义的注解狂numValidate参@NotNuLL源码来开发
 *
 * 2. @Constraint(
 *         validatedBy = { EnumConstraintValidator.class }
 *   )    可以指定该自定义注解与 EnumConstraintValidator.class  校验器关联
 *
 *  3.    String message() default "{ ? }";  可以指定校验时返回的信息
 *
 *  4. Class<?>[] groups() default {}; 指定该自定义注解在哪个校验组生效
 *
 *
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {EnumConstraintValidator.class}
)
public @interface EnumValidate {
    String message() default "{com.hspedu.common.valid.EnumValidate.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //增加values 属性
    int[] values() default {};

    //增加对正则表达式的支持
    String regexp() default "";

}
