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

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "RegisterFragment";

    private EditText etLogin;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnRegister;

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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        etLogin = view.findViewById(R.id.etLogin);
        etPassword = view.findViewById(R.id.etPassword);
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword);
        btnRegister = view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                register();
                break;
        }
    }

    private void register() {
        btnRegister.setEnabled(false);
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin(etLogin.getText().toString());
        userCredentials.setPassword(etPassword.getText().toString());
        Api.getApi()
                .register(userCredentials)
                .subscribeOn(Schedulers.io())
                .subscribe(loginAnswer -> {
                            //Toast.makeText(getActivity().getApplicationContext(), "Register success!", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "register: success, username: " + userCredentials.getLogin() + ", token: " + loginAnswer.getToken());
                            onSignInListener.onSignIn(userCredentials, loginAnswer.getToken());
                        },
                        throwable -> {
                            Log.e("Register", "register: ", throwable);
                            btnRegister.setEnabled(true);
                        });
    }
}
