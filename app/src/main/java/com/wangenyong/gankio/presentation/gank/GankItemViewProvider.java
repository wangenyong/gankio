package com.wangenyong.gankio.presentation.gank;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangenyong.gankio.R;
import com.wangenyong.gankio.model.entity.Gank;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by wangenyong on 2017/3/3.
 */

public class GankItemViewProvider extends ItemViewProvider<Gank, GankItemViewProvider.GankHolder> {

    static class GankHolder extends RecyclerView.ViewHolder {
        @NonNull final TextView desc;

        GankHolder(@NonNull View itemView) {
            super(itemView);
            this.desc = (TextView) itemView.findViewById(R.id.textView_item_gank_desc);
        }
    }

    @NonNull @Override
    protected GankHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_gank, parent, false);
        return new GankHolder(root);
    }


    @Override
    protected void onBindViewHolder(@NonNull GankHolder holder, @NonNull Gank gank) {
        holder.desc.setText(gank.getDesc());
    }
}
