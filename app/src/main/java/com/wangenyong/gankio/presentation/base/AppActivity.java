package com.wangenyong.gankio.presentation.base;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.mvp.Presenter;
import com.wangenyong.gankio.di.component.AppComponent;

/**
 * Created by wangenyong on 2017/3/2.
 */

public abstract class AppActivity<P extends Presenter> extends BaseActivity<P> {
    protected  AppApplication mAppApplication;

    @Override
    protected void ComponentInject() {
        mAppApplication = (AppApplication) getApplication();
        setupActivityComponent(mAppApplication.getAppComponent());
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mAppApplication = null;
    }
}
