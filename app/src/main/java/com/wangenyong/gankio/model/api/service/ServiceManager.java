package com.wangenyong.gankio.model.api.service;

import com.jess.arms.http.BaseServiceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by wangenyong on 2017/3/2.
 */

@Singleton
public class ServiceManager implements BaseServiceManager {
    private GankService mGankService;

    @Inject
    public ServiceManager(GankService gankService) {
        mGankService = gankService;
    }

    public GankService getGankService() {
        return mGankService;
    }

    @Override
    public void onDestory() {

    }
}
