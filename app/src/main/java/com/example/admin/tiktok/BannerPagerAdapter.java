package com.example.admin.tiktok;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/1/18.
 */

public class BannerPagerAdapter extends PagerAdapter {
    private Context mContext;
    /**
     * 图像列表
     */
    private List<Integer> pictureList = new ArrayList<>();


    public BannerPagerAdapter(Context context, List<Integer> pictureList) {
        this.mContext = context;
        this.pictureList = pictureList;
    }

    @Override
    public int getCount() {
        return pictureList.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_item, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_banner_item);
        imageView.setImageResource(pictureList.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
