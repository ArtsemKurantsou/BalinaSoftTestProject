package com.kurantsou.balinasofttestproject.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kurantsou.balinasofttestproject.R;
import com.kurantsou.balinasofttestproject.fragment.adapter.SignInPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        FragmentActivity activity = getActivity();

        SignInPagerAdapter adapter = new SignInPagerAdapter(activity.getSupportFragmentManager(), activity);

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}
