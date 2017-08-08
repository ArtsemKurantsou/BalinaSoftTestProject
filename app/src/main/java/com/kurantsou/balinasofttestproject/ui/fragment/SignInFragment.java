package com.kurantsou.balinasofttestproject.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kurantsou.balinasofttestproject.R;
import com.kurantsou.balinasofttestproject.model.UserCredentials;
import com.kurantsou.balinasofttestproject.ui.fragment.adapter.SignInPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager viewPager = view.findViewById(R.id.viewPager);

        FragmentActivity activity = getActivity();

        SignInPagerAdapter adapter = new SignInPagerAdapter(activity.getSupportFragmentManager(), activity);

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    public interface OnSignInListener {
        void onSignIn(UserCredentials credentials, String token);
    }

}
