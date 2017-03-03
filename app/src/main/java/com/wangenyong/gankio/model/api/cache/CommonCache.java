package com.wangenyong.gankio.model.api.cache;

import com.wangenyong.gankio.model.entity.Gank;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.rx_cache.DynamicKeyGroup;
import io.rx_cache.EvictProvider;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * Created by wangenyong on 2017/3/2.
 */

public interface CommonCache {

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<Gank>>> getGanks(Observable<List<Gank>> oGanks, DynamicKeyGroup idLastQueried, EvictProvider evictProvider);
}
