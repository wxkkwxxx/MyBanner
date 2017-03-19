package com.wxk.leads.mybanner.banner;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/3/17 0017.
 */

public abstract class BannerAdapter {

    //提供视图
    public abstract ImageView getView(int position, View convertView);

    public abstract int getCount();

    public String getBannerDesc(int position){
        return "";
    }
}
