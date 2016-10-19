package com.li.rr.mvp.view.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.li.rr.base.application.App;
import com.li.rr.mvp.bean.FileModel;
import com.li.rr.mvp.presenter.fragment.FragmentPathPresenter;
import com.li.rr.mvp.view.fragment.FragmentPathList;
import com.li.rr.util.ApiConstants;
import com.li.rr.util.file.FileUtils;
import com.li.rr.widget.smoothcheckbox.SmoothCheckBox;

import java.util.ArrayList;
import java.util.List;

import rr.li.com.rxjavaretrofit.R;

/**
 * Created by Administrator on 2016/6/3.
 */
public class AdapterFileList extends BaseAdapter {
    private Context context;
    private List<FileModel> fileModels=new ArrayList<>();
    List<FileModel> list = new ArrayList<>();
    List<Boolean> cbState = null;
    private static AdapterFileList adapterFileList = null;
    private LayoutInflater layoutInflater=null;
    private AdapterFileList(Context context){
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }
    public static AdapterFileList getInstance(Context context){
        if(null == adapterFileList){
            synchronized (AdapterFileList.class){
                if(null == adapterFileList){
                    adapterFileList=new AdapterFileList(context);
                }
            }
        }
        return adapterFileList;
    }


    /**
     * 添加一个Dir对象
     *
     * @param fileModel
     */
    public void addDatas(FileModel fileModel) {
        list.add(fileModel);
        cbState = new ArrayList<Boolean>();
        for (int i = 0; i < list.size(); i++) {
            cbState.add(false);
        }

    }

    /**
     * 清除所有数据
     */
    public void clearAllDatas() {
        list.clear();
    }

    public List<FileModel> getDatas() {
        return this.list;
    }

    public List<Boolean> getCbState() {
        return cbState;
    }

    /**
     * 判断现在是否为多选状态
     *
     * @return
     */
    public boolean isMultiple() {
        for (boolean b : getCbState()) {
            if (b)
                return true;
        }
        return false;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    ViewHolder holder=null;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_fragment_browsing_by_type_recyclerview, parent, false);
            holder=new ViewHolder();
            holder.tv_path = (TextView) convertView.findViewById(R.id.tv_path);
            holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.tv_size = (TextView) convertView.findViewById(R.id.tv_size);
            holder.scb_item_fragment3_checked = (SmoothCheckBox) convertView.findViewById(R.id.scb_item_fragment3_checked);
            holder.rl_item_fragment3_root = (MaterialRippleLayout) convertView.findViewById(R.id.rl_item_fragment3_root);
            holder.ib_dir_properties = (ImageButton) convertView.findViewById(R.id.ib_dir_properties);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tv_size.setText(list.get(position).getStrSize() + "");
        holder.ib_dir_properties.setColorFilter(Color.parseColor("#FF4081"));
        holder.tv_date.setText(FileUtils.getInstance().getFormatDate(list.get(position).getDate()) + "");
        holder.tv_path.setText(list.get(position).getName() + "");

        //每个item的菜单
        holder.ib_dir_properties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CommonUtils.showPopupMenu(context,v,);
            }
        });
        //复选按钮监听
        holder.scb_item_fragment3_checked.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if (isChecked) {
                    holder.scb_item_fragment3_checked.clearText();
                    holder.scb_item_fragment3_checked.clearImageBitmap();
                    getCbState().set(position, isChecked);
                } else {
                    if(list.get(position).isDirectory()){
                        holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_grid_folder_new));
                        getCbState().set(position, isChecked);
                    }else{
                        holder.scb_item_fragment3_checked.setText(list.get(position).getSuffix());
                        getCbState().set(position, isChecked);
                    }

                }
            }
        });



        //如果复选状态为被选中状态
        if (getCbState().get(position)) {
            //设置按钮为被选中状态
            holder.scb_item_fragment3_checked.setChecked(true);
            holder.scb_item_fragment3_checked.clearImageBitmap();
            holder.scb_item_fragment3_checked.clearText();
        } else {
            //如果不是复选状态
            //设置按钮为没有被选中状态
            holder.scb_item_fragment3_checked.setChecked(false);
            //如果是目录
            if (list.get(position).isDirectory()) {
                //设置背景图片为文件夹图片
                holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_grid_folder_new));
                //清除文字
                holder.scb_item_fragment3_checked.clearText();
            } else {
                //如果是文件
//                if (ApiConstants.Suffix.SUFFIX_JPG.equals(list.get(position).getSuffix())) {
//                    holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_doc_image));
//                    holder.scb_item_fragment3_checked.clearText();
//                } else if (ApiConstants.Suffix.SUFFIX_OOG.equals(list.get(position).getSuffix())) {
//                    holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_doc_audio_am));
//                    holder.scb_item_fragment3_checked.clearText();
//                } else if (ApiConstants.Suffix.SUFFIX_PDF.equals(list.get(position).getSuffix())) {
//                    holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_doc_pdf));
//                    holder.scb_item_fragment3_checked.clearText();
//                } else if (ApiConstants.Suffix.SUFFIX_PNG.equals(list.get(position).getSuffix())) {
//                    holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_doc_image));
//                    holder.scb_item_fragment3_checked.clearText();
//                } else if (ApiConstants.Suffix.SUFFIX_TXT.equals(list.get(position).getSuffix())) {
//                    holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_doc_text_am));
//                    holder.scb_item_fragment3_checked.clearText();
//                } else if (ApiConstants.Suffix.SUFFIX_XML.equals(list.get(position).getSuffix())) {
//                    holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_doc_codes));
//                    holder.scb_item_fragment3_checked.clearText();
//                }
//                else {
                //设置复选按钮为文件后缀
                holder.scb_item_fragment3_checked.setText(list.get(position).getSuffix());
                //为防止叠加效果，清除Bitmap
                holder.scb_item_fragment3_checked.clearImageBitmap();
//                }

            }
        }


        //真格Item的长按事件
        holder.rl_item_fragment3_root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(getCbState().get(position)){
                    getCbState().set(position,true);
                    holder.scb_item_fragment3_checked.setChecked(false,true);
                }else {
                    getCbState().set(position,true);
                    holder.scb_item_fragment3_checked.setChecked(true,true);
                }
                return true;
            }
        });
        //整个Item的点击事件
        holder.rl_item_fragment3_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.scb_item_fragment3_checked.clearText();
                holder.scb_item_fragment3_checked.clearImageBitmap();
                if (isMultiple()) {
                    //在判断复选框是否是被选状态
                    if (getCbState().get(position)) {
                        //如果是被选状态
                        if (list.get(position).isDirectory()) {
                            holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_grid_folder_new));
                            getCbState().set(position, false);
                            holder.scb_item_fragment3_checked.setChecked(false, true);
                        } else {
                            holder.scb_item_fragment3_checked.setText(list.get(position).getSuffix());
                            getCbState().set(position, false);
                            holder.scb_item_fragment3_checked.setChecked(false, true);
                        }

                    } else {
                        //如果不是被选状态
                        if (list.get(position).isDirectory()) {
                            holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_grid_folder_new));
                            getCbState().set(position, true);
                            holder.scb_item_fragment3_checked.setChecked(true, true);
                        } else {
                            holder.scb_item_fragment3_checked.clearText();
                            getCbState().set(position, true);
                            holder.scb_item_fragment3_checked.setChecked(true, true);
                        }

                    }
                } else {
                    if (list.get(position).isDirectory()) {
                        FragmentPathPresenter
                                .getInstance(App.getApplicationInstance()
                                        .getFragmentObject(FragmentPathList.class))
                                .getDirsList(list.get(position)
                                        .getAllPath());
                    } else {
                        Toast.makeText(context, "文件:" + list.get(position).getName(), Toast.LENGTH_SHORT).show();
                        if (ApiConstants.Suffix.SUFFIX_JPG.equals(list.get(position).getSuffix())) {

                        } else if (ApiConstants.Suffix.SUFFIX_OOG.equals(list.get(position).getSuffix())) {

                        } else if (ApiConstants.Suffix.SUFFIX_PDF.equals(list.get(position).getSuffix())) {

                        } else if (ApiConstants.Suffix.SUFFIX_PNG.equals(list.get(position).getSuffix())) {
                        } else if (ApiConstants.Suffix.SUFFIX_TXT.equals(list.get(position).getSuffix())) {
                        } else if (ApiConstants.Suffix.SUFFIX_XML.equals(list.get(position).getSuffix())) {


                        } else if (ApiConstants.Suffix.SUFFIX_OOG.equals(list.get(position).getSuffix())) {

                        }
                    }
                }


            }
        });

        return convertView;
    }

    public class ViewHolder{
        SmoothCheckBox scb_item_fragment3_checked;
        public TextView tv_path;
        public TextView tv_date;
        public TextView tv_size;
        public ImageButton ib_dir_properties;
        MaterialRippleLayout rl_item_fragment3_root;
    }
}
