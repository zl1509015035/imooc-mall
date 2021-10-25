package com.imooc.mall.strategy;

import com.imooc.mall.common.Constant;

import java.lang.annotation.*;

/**
 * @author zhul
 * @create 2021/10/25 16:11
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OrderStatusStrategyType {
    /**
     * 订单状态
     */
    Constant.OrderStatusEnum type();

}
