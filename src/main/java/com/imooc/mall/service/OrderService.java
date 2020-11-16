package com.imooc.mall.service;

import com.imooc.mall.model.request.CreateOrderReq;
import com.imooc.mall.model.vo.CartVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：     OrderService
 */
@Service
public interface OrderService {

    String create(CreateOrderReq createOrderReq);
}
