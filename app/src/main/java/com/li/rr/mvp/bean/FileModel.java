package com.li.rr.mvp.bean;

/**
 * Created by Administrator on 2016/5/25.
 */
public class FileModel {
    private long date, size;
    private boolean isDirectory;
    private String permisson;
    private String name;
    private String allPath;
    private String strSize;
    private String suffix;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getStrSize() {
        return strSize;
    }

    public void setStrSize(String strSize) {
        this.strSize = strSize;
    }


    public String getAllPath() {
        return allPath;
    }


    public void setAllPath(String allPath) {
        this.allPath = allPath;
    }

    public long getDate() {
        return date;
    }

    public long getSize() {
        return size;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public String getPermisson() {
        return permisson;
    }

    public String getName() {
        return name;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public void setPermisson(String permisson) {
        this.permisson = permisson;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FileModel{" +
                "date=" + date +
                ", size=" + size +
                ", isDirectory=" + isDirectory +
                ", permisson='" + permisson + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
