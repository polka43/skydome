package org.androidtown.tab;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;
    private String dbidStr;


    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setDbid(String dbid) {
        dbidStr = dbid;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public String getDbid() {
        return  this.dbidStr;
    }

    public ListViewItem(Drawable iconDrawable, String titleStr, String descStr, String dbidStr)
    {

        super();
        this.iconDrawable = iconDrawable;
        this.titleStr = titleStr;
        this.descStr = descStr;
        this.dbidStr = dbidStr;

    }
    public ListViewItem(){}
}
/*
public class ListViewBtnItem {
    private Drawable iconDrawable;
    private String textStr;

    public void setIcon(Drawable icon) {  iconDrawable = icon;  }

    public void setText(String text) {  textStr = text;   }

    public Drawable getIcon() {   return this.iconDrawable;  }

    public String getText() {    return this.textStr;  }
}*/
