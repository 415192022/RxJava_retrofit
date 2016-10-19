package com.li.rr.mvp.view.view;

import com.li.rr.mvp.bean.FileModel;

/**
 * Created by Administrator on 2016/5/26.
 */
public interface IFragmentPathView {
    void getDirList(FileModel fileModel);
    void onStartLoadDirList();
    void onCompletLoadDirList();
    void onLoadDirError(String er);
}
