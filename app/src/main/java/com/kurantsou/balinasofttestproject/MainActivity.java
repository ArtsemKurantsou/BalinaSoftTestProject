package com.kurantsou.balinasofttestproject;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.kurantsou.balinasofttestproject.fragment.SignInFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new SignInFragment())
                .commit();

    }
}
