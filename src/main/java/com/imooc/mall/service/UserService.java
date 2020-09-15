package com.imooc.mall.service;

import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.model.pojo.User;

import java.security.NoSuchAlgorithmException;

/**
 * 描述：     UserService
 */
public interface UserService {

    User getUser();

    void register(String userName,String password) throws ImoocMallException, NoSuchAlgorithmException;

    User login(String userName, String password) throws ImoocMallException, NoSuchAlgorithmException;

    void updateInformation(User user) throws ImoocMallException;

    boolean checkAdminRole(User user);
}
