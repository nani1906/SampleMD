package com.bandari.naveen.samplemd.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bandari.naveen.samplemd.R;
import com.bandari.naveen.samplemd.adapters.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private static int tabIndex = 0;
    private Activity mActivity;
    public static Context mContext;
    private ViewPager viewPager;
    public static TabLayout tabLayout;
    public static ViewPagerAdapter adapter;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(int index, Context context, TabLayout tabLayout) {
        mContext = context;
        MainFragment fragment = new MainFragment();
        MainFragment.tabLayout = tabLayout;
        Bundle args = new Bundle();
        args.putInt("TAB_INDEX", index);
        tabIndex = index;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
//        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#00b5e0"));
//        tabLayout.setSelectedTabIndicatorHeight((int) (3 * getResources().getDisplayMetrics().density));
        setupViewPager(viewPager);
        MainFragment.tabLayout.setupWithViewPager(viewPager);
        loadTab(tabIndex);
    }

    private void setupViewPager(ViewPager viewPager) {
        MainFragment.adapter = new ViewPagerAdapter(getFragmentManager());
        MainFragment.adapter.addFragment(new ListFragment(), "List");
        MainFragment.adapter.addFragment(new TileContentFragment(), "Tiles");
        MainFragment.adapter.addFragment(new CardContentFragment(), "Cards");
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("onPageSelected","==============="+position);
                if(getActivity() != null && getActivity().getActionBar() != null) {
                    getActivity().getActionBar().setTitle("New Title" + position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("onPageScrollState","==============="+state);
            }
        });
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(MainFragment.adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



    void loadTab(int pageIndex){
        MainFragment.tabLayout.setScrollPosition(pageIndex,0f,true);
        viewPager.setCurrentItem(pageIndex);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            mActivity =(Activity) context;
        }

    }
}
