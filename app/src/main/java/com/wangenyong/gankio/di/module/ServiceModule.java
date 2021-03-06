package com.wangenyong.gankio.di.module;

import com.wangenyong.gankio.model.api.service.GankService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by wangenyong on 2017/3/2.
 */

@Module
public class ServiceModule {

    @Singleton
    @Provides
    GankService provideGankService(Retrofit retrofit) {
        return retrofit.create(GankService.class);
    }
}
