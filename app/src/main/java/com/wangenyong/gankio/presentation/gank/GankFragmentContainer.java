package com.wangenyong.gankio.presentation.gank;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangenyong.gankio.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragmentContainer extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.tablayout_gank_container) TabLayout mTabLayout;
    @BindView(R.id.viewPager_gank_container) ViewPager mViewPager;

    private GankPagerAdapter mGankPagerAdapter;

    public GankFragmentContainer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGankPagerAdapter = new GankPagerAdapter(getChildFragmentManager());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gank_container, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mGankPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public class GankPagerAdapter extends FragmentPagerAdapter {
        private final String[] titles = {"all", "Android", "iOS", "前端", "瞎推荐"};

        public GankPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = GankFragment.newInstance(titles[i]);
            return fragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }

}
