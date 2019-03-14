package com.example.day1text.presenter;

/**
 * @Auther: 白俊岭
 * @Date: 2019/3/13 16:44:30
 * @Description:
 */
public class BasePersenter<V> {
 private  V view;

        public void setView(V view){
          this.view=view;
        }
        public  V getView(){
            return  view;
        }

        public  void  detachView(){
            this.view=null;
        }
}
