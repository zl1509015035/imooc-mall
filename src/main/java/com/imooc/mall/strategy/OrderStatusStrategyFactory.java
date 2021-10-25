package com.imooc.mall.strategy;



import com.imooc.mall.common.Constant;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhul
 * @create 2021/10/25 16:04
 */
public class OrderStatusStrategyFactory implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;


    private final Map<Constant.OrderStatusEnum,OrderStatusStrategy> orderStatusStrategyMap= new ConcurrentHashMap<>();



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, OrderStatusStrategy> strategyBeans = applicationContext.getBeansOfType(OrderStatusStrategy.class);
        strategyBeans.forEach((key,strategy)->{
            OrderStatusStrategyType annotation = strategy.getClass().getAnnotation(OrderStatusStrategyType.class);
            if(!ObjectUtils.isEmpty(annotation)){
                Constant.OrderStatusEnum type = annotation.type();
                orderStatusStrategyMap.put(type,strategy);
            }
        });
    }

    public OrderStatusStrategy getOrderStatusStrategy(Integer value) throws Exception {
        Constant.OrderStatusEnum[] orderStatusEnums = Constant.OrderStatusEnum.values();
        for (Constant.OrderStatusEnum orderStatusEnum : orderStatusEnums) {
            if(orderStatusEnum.getValue().equals(value)){
                return getOrderStatusStrategy(orderStatusEnum);
            }
        }
        throw new Exception("错误");
    }

    public OrderStatusStrategy getOrderStatusStrategy(Constant.OrderStatusEnum orderStatusEnum) throws Exception {
        OrderStatusStrategy orderStatusStrategy = orderStatusStrategyMap.get(orderStatusEnum);
        if(ObjectUtils.isEmpty(orderStatusStrategy)){
            throw new Exception("找不到实现类");
        }
        return orderStatusStrategy;
    }



}
