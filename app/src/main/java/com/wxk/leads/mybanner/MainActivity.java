package com.wxk.leads.mybanner;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wxk.leads.mybanner.banner.BannerAdapter;
import com.wxk.leads.mybanner.banner.BannerView;
import com.wxk.leads.mybanner.banner.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private BannerView banner_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);

        banner_view = (BannerView) findViewById(R.id.banner_view);

        initData();
    }

    private void initData() {

        final List<BannerEntity> entities = new ArrayList<>();
        entities.add(new BannerEntity(R.drawable.bg_first, "一一一一一一一"));
        entities.add(new BannerEntity(R.drawable.bg_second, "二二二二二二二二二"));
        entities.add(new BannerEntity(R.drawable.bg_third, "三三三三三三三三三"));
        entities.add(new BannerEntity(R.drawable.bg_fourth, "四四四四四四四四四四"));
        entities.add(new BannerEntity(R.drawable.bg_fifth, "五五五五五五五五五五"));
        entities.add(new BannerEntity(R.drawable.bg_monkey_king, "六六六六六六六六六"));
        entities.add(new BannerEntity(R.drawable.nav_header_bg, "七七七七七七七七七七七七"));

        banner_view.setAdapter(new BannerAdapter() {
            @Override
            public ImageView getView(int position, View convertView) {
                ImageView imageView;
                if(convertView == null){
                    imageView = new ImageView(MainActivity.this);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }else {
                    imageView = (ImageView) convertView;
                    Log.e(TAG, "界面复用:"+convertView);
                }
                Glide.with(MainActivity.this).load(entities.get(position).getImg()).into(imageView);
                return imageView;
            }

            @Override
            public int getCount() {
                return entities.size();
            }

            @Override
            public String getBannerDesc(int position) {
                return entities.get(position).getDesc();
            }
        });

        banner_view.setDuration(3500);
        banner_view.setScrollerDuration(950);
        banner_view.startScroll();

        banner_view.setOnBannerItemClickListener(new BannerViewPager.BannerItemClickListener() {
            @Override
            public void onClick(int position) {

                Toast.makeText(MainActivity.this, entities.get(position).getDesc(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
