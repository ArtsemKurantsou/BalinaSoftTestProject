package com.kurantsou.balinasofttestproject.ui.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kurantsou.balinasofttestproject.R;
import com.kurantsou.balinasofttestproject.api.Api;
import com.kurantsou.balinasofttestproject.databinding.FragmentLoginBinding;
import com.kurantsou.balinasofttestproject.model.UserCredentials;

import java.net.HttpURLConnection;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;


public class LoginFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "LoginFragment";

    FragmentLoginBinding mBinding;

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

        mBinding = DataBindingUtil.bind(view);

        mBinding.btnLogin.setOnClickListener(this);

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
        mBinding.btnLogin.setEnabled(false);
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin(mBinding.etLogin.getText().toString());
        userCredentials.setPassword(mBinding.etPassword.getText().toString());
        Api.getApi()
                .login(userCredentials)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginAnswer -> {
                            Log.d(TAG, "login: success, username: " + userCredentials.getLogin() + ", token:" + loginAnswer.getToken());
                            onSignInListener.onSignIn(userCredentials, loginAnswer.getToken());
                        },
                        throwable -> {
                            Log.e(TAG, "login: failed", throwable);
                            mBinding.btnLogin.setEnabled(true);
                            String message = getErrorMessage(throwable);
                            mBinding.txtError.setVisibility(View.VISIBLE);
                            mBinding.txtError.setText(message);
                        },
                        () -> {
                            mBinding.btnLogin.setEnabled(true);
                        });
    }

    private String getErrorMessage(Throwable throwable) {
        String error = getString(R.string.some_error);
        if (throwable instanceof HttpException) {
            HttpException exeption = (HttpException) throwable;

            switch (exeption.code()) {
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    error = getString(R.string.wrong_login_or_password);
                    break;
                default:
                    error = getString(R.string.connection_error);
                    break;
            }
        }
        return error;
    }
}
