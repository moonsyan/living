package com.hspedu.hspliving.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.hspedu.common.valid.EnumValidate;
import com.hspedu.common.valid.SaveGroup;
import com.hspedu.common.valid.UpdateGroup;
import com.hspedu.common.valid.UpdateIsShow;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 家居品牌
 * 
 * @author ming
 * @email ming@gmail.com
 * @date 2024-05-01 08:41:17
 */
@Data
@TableName("commodity_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 * 1.@NotNuLl(message="修政要求指定id",groups={UpdateGroup.class.})
	 * 表示@NotNull在updateGroup校验组生效
	 * 2.@NULl(message="添加不能指定id",groups={SaveGroup.class.})
	 * 表示@NuLl在SaveGroup校验组生效
	 */
	@TableId
	@NotNull(message = "修改要求指定id",groups = {UpdateGroup.class, UpdateIsShow.class})
	@Null(message = "添加不能指定id",groups = {SaveGroup.class})
	private Long id;
	/**
	 * 品牌名
	 * 1.@NotBlank表示name必须包括一个非空字符
	 * 2.message="品牌名不能为空是老师指定的一个校验消息
	 * 3.如果没有指定message="品牌名不能为空"，就好返回默认的校验消息
	 */
	@NotBlank(message = "品牌名不能为空",groups = {SaveGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * logo
	 */

	@NotBlank(message = "logo不能为空",groups = {SaveGroup.class})
	@URL(message = "logo必须是一个合法的url地址",groups = {SaveGroup.class, UpdateGroup.class})
	private String logo;
	/**
	 * 说明
	 */
	private String description;
	/**
	 * 显示
	 * 1 sshow后面的s是小写
	 * 1.这里我们使用的注解是aNotNu几L,他可以接收任意类型
	 * 2.如果这里使用@NotBLank,会报错，因为@NotBlank不支特红nteger
	 * 3。同学门在开发时，需要知道注解可以用在哪些类型上，可以查看注解源码
	 * 4.老师说明，假如isshow规定是0/1，这时我们后面通过自定义约束来解决.·
	 */

	@NotNull(message = "显示状态不能为空",groups = {SaveGroup.class, UpdateGroup.class, UpdateIsShow.class})
	@EnumValidate(values = {0,1},message = "显示状态需要是0或1",groups = {SaveGroup.class, UpdateGroup.class, UpdateIsShow.class})
	//@EnumValidate(regexp = "^[0-1]$",message = "显示状态需要是0或1",groups = {SaveGroup.class, UpdateGroup.class})
	private Integer isshow;
	/**
	 * 检索首字母
	 */
	@NotBlank(message = "检索首字母不能为空",groups = {SaveGroup.class})
	@Pattern(regexp = "^[a-zA-Z]$",message = "检索首字母必须是一个字母",groups = {SaveGroup.class, UpdateGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull(message = "排序不能为空",groups = {SaveGroup.class})
	@Min(value = 0,message = "排序必须大于等于0",groups = {SaveGroup.class, UpdateGroup.class})
	private Integer sort;

}
