package com.wangenyong.gankio.presentation.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.ncapdevi.fragnav.FragNavController;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.wangenyong.gankio.R;
import com.wangenyong.gankio.di.component.AppComponent;
import com.wangenyong.gankio.presentation.base.AppActivity;
import com.wangenyong.gankio.presentation.gank.GankFragmentContainer;
import com.wangenyong.gankio.presentation.girls.GirlsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends AppActivity {
    @BindView(R.id.bottomBar) BottomBar mBottomBar;
    private FragNavController mFragNavController;

    private final int TAB_GANK = FragNavController.TAB1;
    private final int TAB_TODAY = FragNavController.TAB2;
    private final int TAB_GIRLS = FragNavController.TAB3;
    private final int TAB_MY = FragNavController.TAB4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Fragment> fragments = new ArrayList<>(4);
        fragments.add(new GankFragmentContainer());
        fragments.add(new GirlsFragment());
        fragments.add(new GirlsFragment());
        fragments.add(new GirlsFragment());

        mFragNavController = new FragNavController(savedInstanceState, getSupportFragmentManager(), R.id.contentContainer, fragments, TAB_TODAY);

        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_gank) {
                    mFragNavController.switchTab(TAB_GANK);
                } else if (tabId == R.id.tab_today) {
                    mFragNavController.switchTab(TAB_TODAY);
                } else if (tabId == R.id.tab_girls) {
                    mFragNavController.switchTab(TAB_GIRLS);
                } else if (tabId == R.id.tab_my) {
                    mFragNavController.switchTab(TAB_MY);
                }
            }
        });
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_main, null, false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mFragNavController != null) {
            mFragNavController.onSaveInstanceState(outState);
        }
    }
}
