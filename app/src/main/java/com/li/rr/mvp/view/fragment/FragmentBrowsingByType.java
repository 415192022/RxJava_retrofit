package com.li.rr.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.balysv.materialripple.MaterialRippleLayout;
import com.li.rr.base.fragment.BaseFragmentV4;
import com.li.rr.mvp.view.activity.ActivityCategory;
import com.li.rr.widget.smoothcheckbox.SmoothCheckBox;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rr.li.com.rxjavaretrofit.R;

/**
 * Created by Administrator on 2016/5/24.
 */
public class FragmentBrowsingByType extends BaseFragmentV4 implements View.OnClickListener {
    @InjectView(R.id.scb_music)
    SmoothCheckBox scb_music;
    @InjectView(R.id.scb_video)
    SmoothCheckBox scb_video;
    @InjectView(R.id.scb_pic)
    SmoothCheckBox scb_pic;
    @InjectView(R.id.scb_xml)
    SmoothCheckBox scb_xml;
    @InjectView(R.id.scb_doc)
    SmoothCheckBox scb_doc;
    @InjectView(R.id.scb_zip)
    SmoothCheckBox scb_zip;
    @InjectView(R.id.scb_apk)
    SmoothCheckBox scb_apk;
    @InjectView(R.id.scb_fov)
    SmoothCheckBox scb_fov;
    @InjectView(R.id.scb_pdf)
    SmoothCheckBox scb_pdf;
    @InjectView(R.id.category_music)
    MaterialRippleLayout category_music;
    @InjectView(R.id.category_video)
    MaterialRippleLayout category_video;
    @InjectView(R.id.category_picture)
    MaterialRippleLayout category_picture;
    @InjectView(R.id.category_xml)
    MaterialRippleLayout category_xml;
    @InjectView(R.id.category_doc)
    MaterialRippleLayout category_doc;
    @InjectView(R.id.category_zip)
    MaterialRippleLayout category_zip;
    @InjectView(R.id.category_apk)
    MaterialRippleLayout category_apk;
    @InjectView(R.id.category_favorite)
    MaterialRippleLayout category_favorite;
    @InjectView(R.id.category_pdf)
    MaterialRippleLayout category_pdf;



    private void setListener(){
        category_music.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ActivityCategory.class);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_browsing_by_type;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        ButterKnife.inject(this,view);
        //初始化圆形图标
        setIcon(3.0f,3.0f);
        setListener();
    }

    @Override
    public void doBusiness(Context mContext) {
    }

    /**
     * 设置每个圆形图标
     * @param scalX
     * @param scalY
     */
    private void setIcon(float scalX,float scalY){
        scb_music.setImageBitmap(SmoothCheckBox.small(BitmapFactory.decodeResource(getResources(), R.drawable.ic_doc_audio_am), scalX, scalY));
        scb_video.setImageBitmap(SmoothCheckBox.small(BitmapFactory.decodeResource(getResources(), R.drawable.ic_doc_video_am), scalX, scalY));
        scb_pic.setImageBitmap(SmoothCheckBox.small(BitmapFactory.decodeResource(getResources(), R.drawable.ic_doc_image), scalX, scalY));
        scb_xml.setImageBitmap(SmoothCheckBox.small(BitmapFactory.decodeResource(getResources(), R.drawable.ic_doc_codes), scalX, scalY));
        scb_doc.setImageBitmap(SmoothCheckBox.small(BitmapFactory.decodeResource(getResources(), R.drawable.ic_doc_text_am), scalX, scalY));
        scb_zip.setImageBitmap(SmoothCheckBox.small(BitmapFactory.decodeResource(getResources(), R.drawable.ic_doc_compressed), scalX, scalY));
        scb_apk.setImageBitmap(SmoothCheckBox.small(BitmapFactory.decodeResource(getResources(), R.drawable.ic_doc_apk_white), scalX, scalY));
        scb_fov.setImageBitmap(SmoothCheckBox.small(BitmapFactory.decodeResource(getResources(), R.drawable.ic_star_white_18dp), scalX, scalY));
        scb_pdf.setImageBitmap(SmoothCheckBox.small(BitmapFactory.decodeResource(getResources(), R.drawable.ic_doc_pdf), scalX, scalY));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.category_music:
                Intent intent=new Intent(getActivity(), ActivityCategory.class);
                getActivity().startActivity(intent);
            break;
        }
    }
}



