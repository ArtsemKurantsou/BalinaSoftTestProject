package com.kurantsou.balinasofttestproject.ui.fragment.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kurantsou.balinasofttestproject.R;
import com.kurantsou.balinasofttestproject.ui.fragment.LoginFragment;
import com.kurantsou.balinasofttestproject.ui.fragment.RegisterFragment;

/**
 * Created by artem on 07.08.2017.
 */

public class SignInPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    public SignInPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        titles = context.getResources().getStringArray(R.array.sign_in_titles);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new LoginFragment();
                break;
            case 1:
                fragment = new RegisterFragment();
                break;
        }

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
}
