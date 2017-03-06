package com.wangenyong.gankio.presentation.gank;

import android.app.Application;

import com.jess.arms.base.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.wangenyong.gankio.model.entity.Gank;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.drakeet.multitype.MultiTypeAdapter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */


/**
 * Created by wangenyong on 2017/3/3.
 */

@ActivityScope
public class GankPresenter extends BasePresenter<GankContract.Model, GankContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    private MultiTypeAdapter mAdapter;
    private List<Object> mGanks = new ArrayList<>();

    private final int mCount = 10;
    private int mPage = 1;

    @Inject
    public GankPresenter(GankContract.Model model, GankContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;

        mAdapter = new MultiTypeAdapter();
        mAdapter.register(Gank.class, new GankItemViewProvider());
        mRootView.setAdapter(mAdapter);
    }

    public void requestGanks(final String type, final boolean pullToRefresh) {
        //请求外部存储权限用于适配android6.0的权限管理机制
        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                //request permission success, do something.
            }
        }, mRootView.getRxPermissions(), mRootView, mErrorHandler);

        Subscription subscription = mModel.getGanks(type, mCount, mPage, pullToRefresh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<List<Gank>>(mErrorHandler) {
                    @Override
                    public void onNext(List<Gank> ganks) {
                        for (Gank gank: ganks) {
                            mGanks.add(gank);
                        }
                        mAdapter.setItems(mGanks);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

}