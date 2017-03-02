package com.wangenyong.gankio.presentation.base;

import android.content.Context;

import com.jess.arms.base.BaseApplication;
import com.jess.arms.di.module.GlobeConfigModule;
import com.jess.arms.http.GlobeHttpHandler;
import com.jess.arms.utils.UiUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.wangenyong.gankio.BuildConfig;
import com.wangenyong.gankio.di.component.AppComponent;
import com.wangenyong.gankio.di.component.DaggerAppComponent;
import com.wangenyong.gankio.di.module.CacheModule;
import com.wangenyong.gankio.di.module.ServiceModule;
import com.wangenyong.gankio.model.api.Api;

import me.jessyan.rxerrorhandler.handler.listener.ResponseErroListener;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by wangenyong on 2017/3/2.
 */

public class AppApplication extends BaseApplication {
    private AppComponent mAppComponent;
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(getAppModule())
                .clientModule(getClientModule())
                .imageModule(getImageModule())
                .globeConfigModule(getGlobeConfigModule())
                .serviceModule(new ServiceModule())
                .cacheModule(new CacheModule())
                .build();

        if (BuildConfig.LOG_DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        installLeakCanary();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppComponent != null) {
            this.mAppComponent = null;
        }
        if (mRefWatcher != null) {
            this.mRefWatcher = null;
        }
    }

    /**
     * 安装leakCanary检测内存泄露
     */
    protected void installLeakCanary() {
        this.mRefWatcher = BuildConfig.USE_CANARY ? LeakCanary.install(this) : RefWatcher.DISABLED;
    }

    /**
     * 获得leakCanary观察器
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
        AppApplication application = (AppApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    protected GlobeConfigModule getGlobeConfigModule() {
        return GlobeConfigModule
                .buidler()
                .baseurl(Api.APP_DOMAIN)
                .globeHttpHandler(new GlobeHttpHandler() {
                    @Override
                    public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
                        return response;
                    }

                    @Override
                    public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
                        return request;
                    }
                })
                .responseErroListener(new ResponseErroListener() {
                    @Override
                    public void handleResponseError(Context context, Exception e) {
                        Timber.tag(TAG).w("------------>" + e.getMessage());
                        UiUtils.SnackbarText("net error");
                    }
                }).build();

    }

}
