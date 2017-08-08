package com.kurantsou.balinasofttestproject.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kurantsou.balinasofttestproject.R;
import com.kurantsou.balinasofttestproject.ui.fragment.GaleryFragment;
import com.kurantsou.balinasofttestproject.ui.fragment.SignInFragment;

public class MainActivity extends FragmentActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new GaleryFragment())
                .commit();

    }
}
