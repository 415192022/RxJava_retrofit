package com.li.rr.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.li.rr.mvp.bean.FileModel;

import java.util.ArrayList;
import java.util.List;

import rr.li.com.rxjavaretrofit.R;

/**
 * Created by Administrator on 2016/6/2.
 */
public class AdapterCategory extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  List<FileModel> fileModels=new ArrayList<>();
    private Context context;
    public AdapterCategory(Context context){
        this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.item_fragment_browsing_by_type_recyclerview , parent ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return fileModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
