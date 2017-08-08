package com.kurantsou.balinasofttestproject.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.kurantsou.balinasofttestproject.R;
import com.kurantsou.balinasofttestproject.ui.fragment.SignInFragment;

public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new SignInFragment())
                .commit();
    }
}
