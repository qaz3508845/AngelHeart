package com.example;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import java.lang.reflect.Field;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link viewPagerMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewPagerMainFragment extends Fragment {

    /**
     * PagerSlidingTabStrip的实例
     */
    private PagerSlidingTabStrip tabs;

    /**
     * 获取当前屏幕的密度
     */
    private DisplayMetrics dm;

    private personalInformationFragment personalInformationFragment;
    private voiceFragment voiceFragment;
    private emergencyContactkFragment emergencyContactkFragment;
    private mapViewFragment mapViewFragment;
    /*
     *
     */


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public viewPagerMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment viewPagerMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static viewPagerMainFragment newInstance(String param1, String param2) {
        viewPagerMainFragment fragment = new viewPagerMainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager_main, null);
        setOverflowShowingAlways();
        dm = getResources().getDisplayMetrics();
        ViewPager pager = (ViewPager)view. findViewById(R.id.pager);
        pager.setOffscreenPageLimit(0);//设置ViewPager的缓存界面数,默认缓存为2
        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        pager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        tabs.setViewPager(pager);
        setTabsValue();
        return view;
    }

    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        tabs.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, dm));
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, dm));
        // 设置Tab Indicator的颜色
        tabs.setIndicatorColor(Color.parseColor("#d83737"));//#d83737   #d83737(绿)
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        tabs.setTextColor(Color.parseColor("#ffffff"));
        // 取消点击Tab时的背景色
        tabs.setTabBackground(0);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private final String[] titles = { "個人資料", "語音辨識", "緊急聯絡人" ,"地圖定位"};

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (personalInformationFragment == null) {
                        personalInformationFragment = new personalInformationFragment();
                    }
                    return personalInformationFragment;
                case 1:
                    if (voiceFragment == null) {
                        voiceFragment = new voiceFragment();
                    }
                    return voiceFragment;
                case 2:
                    if (emergencyContactkFragment == null) {
                        emergencyContactkFragment = new emergencyContactkFragment();
                    }
                    return emergencyContactkFragment;
                case 3:
                    if (mapViewFragment == null) {
                        mapViewFragment = new mapViewFragment();
                    }
                    return mapViewFragment;
                default:
                    return null;
            }
        }

    }

    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(getParentFragment().getActivity());
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
