package com.imooc.mall.common;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 描述：    常量值
 */
@Component
public class Constant {
    public static final String IMOOC_MALL_USER = "imooc_mall_user";
    public static final String SALT = "1qaz@WSX";

    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir){
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price desc","price asc");

    }

    public interface SaleStatus{
        int NOT_SALE = 0 ;//商品下架状态
        int SALE = 1;//商品上架状态
    }

    public interface Cart{
        int UN_CHECKED = 0 ;//购物车未选中状态
        int CHECKED = 1;//购物车选中状态
    }
}
