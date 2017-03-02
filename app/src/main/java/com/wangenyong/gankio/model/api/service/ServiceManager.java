package com.wangenyong.gankio.model.api.service;

import com.jess.arms.http.BaseServiceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by wangenyong on 2017/3/2.
 */

@Singleton
public class ServiceManager implements BaseServiceManager {
    private TechService mTechService;

    @Inject
    public ServiceManager(TechService techService) {
        mTechService = techService;
    }

    public TechService getTechService() {
        return mTechService;
    }

    @Override
    public void onDestory() {

    }
}
