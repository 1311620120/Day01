package com.example.day1text.view.interfaces;

/**
 * @Auther: 白俊岭
 * @Date: 2019/3/13 16:35:40
 * @Description:
 */
public interface  IMainView<T> {

     void  onseccess(T t);
     void  Fail(String Err);
}
