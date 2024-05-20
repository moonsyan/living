package com.hspedu.hspliving.commodity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品 spu 信息介绍
 * 
 * @author ming
 * @email ming@gmail.com
 * @date 2024-05-13 15:34:37
 */
@Data
@TableName("commodity_spu_info_desc")
public class SpuInfoDescEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品 id
	 * 1.
	 * 因为commodity_spU_info_desc表的spU_id字段不是自增长的
	 * 2.
	 * 而是我们添SpuIn1 oDescEntity对象配置的id
	 */
	@TableId(type= IdType.INPUT)
	private Long spuId;
	/**
	 * 商品介绍图片
	 */
	private String decript;

}
