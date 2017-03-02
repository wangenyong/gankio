package com.wangenyong.gankio.model.api.cache;

import com.jess.arms.http.BaseCacheManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by wangenyong on 2017/3/2.
 */

@Singleton
public class CacheManager implements BaseCacheManager {
    private CommonCache mCommonCache;

    @Inject
    public CacheManager(CommonCache commonCache) {
        this.mCommonCache = commonCache;
    }

    public CommonCache getCommonCache() {
        return mCommonCache;
    }

    @Override
    public void onDestory() {

    }
}
