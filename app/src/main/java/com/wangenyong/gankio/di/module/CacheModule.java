package com.wangenyong.gankio.di.module;

import com.wangenyong.gankio.model.api.cache.CommonCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache.internal.RxCache;

/**
 * Created by wangenyong on 2017/3/2.
 */

@Module
public class CacheModule {
    @Singleton
    @Provides
    CommonCache provideCommonService(RxCache rxCache) {
        return rxCache.using(CommonCache.class);
    }
}
