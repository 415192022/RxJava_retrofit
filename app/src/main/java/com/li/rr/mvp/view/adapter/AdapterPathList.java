package com.li.rr.mvp.view.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.li.rr.base.application.App;
import com.li.rr.mvp.bean.FileModel;
import com.li.rr.mvp.presenter.fragment.FragmentPathPresenter;
import com.li.rr.mvp.view.fragment.FragmentPathList;
import com.li.rr.util.ApiConstants;
import com.li.rr.util.CommonUtils;
import com.li.rr.util.file.FileUtils;
import com.li.rr.widget.smoothcheckbox.SmoothCheckBox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rr.li.com.rxjavaretrofit.R;

/**
 * Created by Administrator on 2016/5/25.
 */
public class AdapterPathList extends RecyclerView.Adapter<AdapterPathList.ViewHolder> {
    private Context context = null;
    List<FileModel> list = new ArrayList<>();
    List<Boolean> cbState = null;
    private static AdapterPathList adapterPathList = null;

    /**
     * 构造器
     *
     * @param context
     */
    protected AdapterPathList(Context context) {
        this.context = context;
    }

    private AdapterPathList() {
    }

    /**
     * 单例模式
     *
     * @param context
     * @return
     */
    public static AdapterPathList getInstance(Context context) {
        if (null == adapterPathList) {
            synchronized (AdapterPathList.class) {
                if (null == adapterPathList)
                    adapterPathList = new AdapterPathList(context);
            }
        }
        return adapterPathList;
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


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_fragment_browsing_by_type_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //加载Item中的数据
        holder.tv_size.setText(list.get(position).getStrSize() + "");
        holder.ib_dir_properties.setColorFilter(Color.parseColor("#FF4081"));
        holder.tv_date.setText(FileUtils.getInstance().getFormatDate(list.get(position).getDate()) + "");
        holder.tv_path.setText(list.get(position).getName() + "");

        //每个item的菜单
        holder.ib_dir_properties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.showPopupMenu(context,v,new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_copy:

                                break;
                            case R.id.menu_cut:
                                break;
                            case R.id.menu_properties:
                                break;
                            case R.id.menu_rename:
                                break;
                            case R.id.menu_delete:
                                if ( FileUtils.getInstance().deleteQuietly(new File(list.get(position).getAllPath())))
                                  Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT) ;
                                break;
                            case R.id.menu_share:
                                break;
                            case R.id.menu_favorite:
                                break;
                        }
                        return false;
                    }
                });
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
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //        CircleImageView civ_icon;
//        TextView tv_path;
        SmoothCheckBox scb_item_fragment3_checked;
        public TextView tv_path;
        public TextView tv_date;
        public TextView tv_size;
        public ImageButton ib_dir_properties;
        MaterialRippleLayout rl_item_fragment3_root;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_path = (TextView) itemView.findViewById(R.id.tv_path);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_size = (TextView) itemView.findViewById(R.id.tv_size);
            scb_item_fragment3_checked = (SmoothCheckBox) itemView.findViewById(R.id.scb_item_fragment3_checked);
            rl_item_fragment3_root = (MaterialRippleLayout) itemView.findViewById(R.id.rl_item_fragment3_root);
            ib_dir_properties = (ImageButton) itemView.findViewById(R.id.ib_dir_properties);
        }
    }
}
