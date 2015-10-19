package com.mentobile.homzz;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by user on 10/18/2015.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private final int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("TabsPageAdapter ", ":::::Adapter " + position);
        switch (position) {
            case 0:
                return new TrendFragment();
            case 1:
                return new SearchFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
