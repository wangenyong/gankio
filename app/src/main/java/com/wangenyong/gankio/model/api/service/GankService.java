package com.wangenyong.gankio.model.api.service;

import com.wangenyong.gankio.model.entity.BaseJson;
import com.wangenyong.gankio.model.entity.Gank;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by wangenyong on 2017/3/2.
 */

public interface GankService {
    @GET("data/{type}/{count}/{page}")
    Observable<BaseJson<List<Gank>>> getGanks(@Path("type") String type, @Path("count") int count, @Path("page") int page);
}
