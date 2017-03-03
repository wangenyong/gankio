package com.wangenyong.gankio.presentation.main;

import android.view.LayoutInflater;
import android.view.View;

import com.roughike.bottombar.BottomBar;
import com.wangenyong.gankio.R;
import com.wangenyong.gankio.di.component.AppComponent;
import com.wangenyong.gankio.presentation.base.AppActivity;

import butterknife.BindView;

public class MainActivity extends AppActivity {
    @BindView(R.id.bottomBar) BottomBar mBottomBar;

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
}
