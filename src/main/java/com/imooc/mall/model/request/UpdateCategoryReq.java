package com.imooc.mall.model.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 描述：     AddCategoryReq
 */
@Data
public class UpdateCategoryReq {

    @NotNull(message = "id不能为null")
    private Integer id;

    @Size(min = 2,max = 5)
    private String name;
    @Max(3)
    private Integer type;

    private Integer parentId;

    private Integer orderNum;


}
