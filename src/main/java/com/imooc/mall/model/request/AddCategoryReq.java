package com.imooc.mall.model.request;

import lombok.Data;

/**
 * 描述：     AddCategoryReq
 */
@Data
public class AddCategoryReq {

    private String name;

    private Integer type;

    private Integer parentId;

    private Integer orderNum;


}
