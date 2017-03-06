package com.wangenyong.gankio.presentation.base;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.mvp.Presenter;
import com.squareup.leakcanary.RefWatcher;
import com.wangenyong.gankio.di.component.AppComponent;

/**
 * Created by wangenyong on 2017/3/2.
 */

public abstract class AppFragment<P extends Presenter> extends BaseFragment<P> {
    protected AppApplication mAppApplication;
    protected boolean isVisible = false;
    protected boolean isViewPrepared = false;
    protected boolean isDataLoaded = false;

    @Override
    protected void ComponentInject() {
        mAppApplication = (AppApplication)mActivity.getApplication();
        setupFragmentComponent(mAppApplication.getAppComponent());
    }

    protected abstract void setupFragmentComponent(AppComponent appComponent);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible(){}
    protected abstract void lazyLoad();
    protected void onInvisible(){}

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher watcher = AppApplication.getRefWatcher(getActivity());
        if (watcher != null) {
            watcher.watch(this);
        }
        this.mAppApplication =null;
    }
}
