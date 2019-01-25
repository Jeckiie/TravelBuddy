package com.travelbuddy.jerko.travelbuddy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    private SectionsFragment mSectionsFragment;
    private ListFragment mListFragment;
    private MainFragment mMainFragment;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                if (mSectionsFragment == null) {
                    mSectionsFragment = new SectionsFragment();
                }
                return mSectionsFragment;
            case 1:
                if (mListFragment == null) {
                    mListFragment = new ListFragment();
                }
                return mListFragment;
            case 2:
                if (mMainFragment == null) {
                    mMainFragment = new MainFragment();
                }
                return mMainFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
