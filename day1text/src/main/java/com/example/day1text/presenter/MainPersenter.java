package com.example.day1text.presenter;

import com.example.day1text.model.Constant;
import com.example.day1text.model.HttpUtils;
import com.example.day1text.model.JsenBean;
import com.example.day1text.view.activity.MainActivity;
import com.example.day1text.view.interfaces.IMainView;

/**
 * @Auther: 白俊岭
 * @Date: 2019/3/13 16:44:46
 * @Description:
 */
public class MainPersenter extends  BasePersenter<IMainView<JsenBean>> {

   private final HttpUtils instance;

   public  MainPersenter(MainActivity mainActivity){
      instance = HttpUtils.getInstance();
   }
   public  void  getData(){
instance .getData(Constant.BASE_URL, JsenBean.class, new HttpUtils.CallBackData<JsenBean>() {


   @Override
   public void onResponse(JsenBean jsenBean) {
      getView().onseccess(jsenBean);
   }

   @Override
   public void onFail(String err) {

   }


});
   }

}
