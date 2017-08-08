package com.kurantsou.balinasofttestproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.kurantsou.balinasofttestproject.R;
import com.kurantsou.balinasofttestproject.model.UserCredentials;
import com.kurantsou.balinasofttestproject.ui.fragment.SignInFragment;
import com.kurantsou.balinasofttestproject.util.SharedPreferencesUtil;

public class LoginActivity extends FragmentActivity implements SignInFragment.OnSignInListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new SignInFragment())
                .commit();
    }

    public void onSignIn(UserCredentials credentials, String token){
        SharedPreferencesUtil.saveCredentials(this, credentials);
        SharedPreferencesUtil.saveToken(this, token);
        startActivity(new Intent(this, MainActivity.class));
    }

}
