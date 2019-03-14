package com.example.day1text.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Auther: 白俊岭
 * @Date: 2019/3/13 18:50:15
 * @Description:
 */
public class HttpUtils<T> {

       private final OkHttpClient okHttpClient;

       private HttpUtils(){
              okHttpClient = new OkHttpClient.Builder()
                      .addNetworkInterceptor(new LogginIntecepter())
                      .readTimeout(5, TimeUnit.SECONDS)
                      .writeTimeout(5, TimeUnit.SECONDS)
                      .connectTimeout(5, TimeUnit.SECONDS)
                      .build();
       }

       public  static  HttpUtils getInstance(){
              return  HttpUtilsInstances.httpUtils;
       }
       private  static  class  HttpUtilsInstances{
              private  static HttpUtils httpUtils=new HttpUtils();
       }
       private  class  LogginIntecepter implements Interceptor{

              @Override
              public Response intercept(Chain chain) throws IOException {
                     Request request = chain.request();
                     RequestBody body = request.body();
                     Log.e("lala",""+body);
                     Headers headers = request.headers();
                     Response proceed = chain.proceed(request);
                     Headers headers1 = proceed.headers();
                     return  proceed;
              }
       }
        Handler handler =new Handler(){
               @Override
               public void handleMessage(Message msg) {
                      super.handleMessage(msg);
                    T t=(T)  msg.obj;
                    mCallBack.onResponse(t);
               }
        };
        private  CallBackData mCallBack;
       public  void  getData (String url, final Class<T> tClass, CallBackData callBackData){
                this .mCallBack=callBackData;
              Request build = new Request.Builder()
                      .url(url)
                      .get()
                      .build();
              Call call = okHttpClient.newCall(build);
              call.enqueue(new Callback() {
                     @Override
                     public void onFailure(Call call, IOException e) {

                     }

                     @Override
                     public void onResponse(Call call, Response response) throws IOException {
                            String string = response.body().string();
                            Gson gson = new Gson();
                            T t = gson.fromJson(string, tClass);
                            Message message = handler.obtainMessage();
                            message.obj=t;
                            handler.sendMessage(message);
                     }
              });
       }
       public  void  getpost (String url, final Class<T> tClass, CallBackData callBackData){
              this .mCallBack=callBackData;
              FormBody build1 = new FormBody.Builder().build();
              Request build = new Request.Builder()
                      .url(url)
                      .post(build1)
                      .build();
              Call call = okHttpClient.newCall(build);
              call.enqueue(new Callback() {
                     @Override
                     public void onFailure(Call call, IOException e) {

                     }

                     @Override
                     public void onResponse(Call call, Response response) throws IOException {
                            String string = response.body().string();
                            Gson gson = new Gson();
                            T t = gson.fromJson(string, tClass);
                            Message message = handler.obtainMessage();
                            message.obj=t;
                            handler.sendMessage(message);
                     }
              });
       }

       public interface  CallBackData<D>{
              public  void onResponse(D d);
              public  void  onFail(String err);
       }
}
