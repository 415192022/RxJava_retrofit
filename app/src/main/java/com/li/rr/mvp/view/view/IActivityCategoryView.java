package com.li.rr.mvp.view.view;

import com.li.rr.mvp.bean.FileModel;

/**
 * Created by Administrator on 2016/6/2.
 */
public interface IActivityCategoryView {
    void getMusicList(FileModel fileModel);
    void onStartLoadMusicList();
    void onCompletLoadMusicList();
    void onLoadMusicError(String er);
}
