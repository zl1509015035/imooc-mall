package com.imooc.mall.strategy;

import com.imooc.mall.common.Constant;
import org.springframework.stereotype.Service;

/**
 * @author zhul
 * @create 2021/10/25 16:12
 */
@Service
@OrderStatusStrategyType(type = Constant.OrderStatusEnum.NOT_PAID)
public class OrderStatusStrategyCreate implements OrderStatusStrategy{
    @Override
    public void execute() {
        System.out.println("hello OrderStatusStrategyCreate");
    }
}
