package com.example.day1text.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day1text.R;
import com.example.day1text.model.JsenBean;

import java.util.List;

/**
 * @Auther: 白俊岭
 * @Date: 2019/3/13 19:30:16
 * @Description:
 */
public class MlssAdapter extends RecyclerView.Adapter<MlssAdapter.ViewHolder> {

    Context context ;
    List<JsenBean.ResultBean.MlssBean.CommodityListBeanXX> mlss1;
    public MlssAdapter(Context context, List<JsenBean.ResultBean.MlssBean.CommodityListBeanXX> mlss1) {
         this.context=context;
         this.mlss1=mlss1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.view, viewGroup, false);
        MlssAdapter.ViewHolder viewHolder = new MlssAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(mlss1.get(i).getCommodityName());
        Glide.with(context)
                .load(mlss1.get(i).getMasterPic())
                .into(viewHolder.img);
        viewHolder.pirce.setText(mlss1.get(i).getPrice());
    }


    @Override
    public int getItemCount() {
        return mlss1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView pirce;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=   itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            pirce = itemView.findViewById(R.id.pirce);
        }
    }
}
