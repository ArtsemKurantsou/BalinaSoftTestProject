package com.kurantsou.balinasofttestproject.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kurantsou.balinasofttestproject.R;
import com.kurantsou.balinasofttestproject.api.Api;
import com.kurantsou.balinasofttestproject.model.UserCredentials;

import rx.schedulers.Schedulers;


public class LoginFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "LoginFragment";

    private EditText etLogin;
    private EditText etPassword;
    private Button btnLogin;

    SignInFragment.OnSignInListener onSignInListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onSignInListener = (SignInFragment.OnSignInListener) context;
        } catch (Exception e) {
            throw new IllegalArgumentException("Activity needs to implements SignInFragment.OnSignInListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etLogin = view.findViewById(R.id.etLogin);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                login();
                break;
        }
    }

    private void login() {
        btnLogin.setEnabled(false);
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin(etLogin.getText().toString());
        userCredentials.setPassword(etPassword.getText().toString());
        Api.getApi()
                .login(userCredentials)
                .subscribe(loginAnswer -> {
                            //Toast.makeText(getActivity().getApplicationContext(), "Login success!", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "login: success, username: " + userCredentials.getLogin() + ", token:" + loginAnswer.getToken());
                            onSignInListener.onSignIn(userCredentials, loginAnswer.getToken());
                        },
                        throwable -> {
                            Log.e(TAG, "login: failed", throwable);
                            btnLogin.setEnabled(true);
                            //Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                        });
    }
}
