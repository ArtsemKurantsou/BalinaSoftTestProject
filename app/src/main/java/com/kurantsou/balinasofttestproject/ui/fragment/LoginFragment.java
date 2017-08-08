package com.kurantsou.balinasofttestproject.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.kurantsou.balinasofttestproject.R;
import com.kurantsou.balinasofttestproject.api.Api;
import com.kurantsou.balinasofttestproject.model.UserCredentials;


public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText etLogin;
    private EditText etPassword;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etLogin = (EditText) view.findViewById(R.id.etLogin);
        etPassword = (EditText) view.findViewById(R.id.etPassword);

        view.findViewById(R.id.btnLogin).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                login();
                break;
        }
    }

    private void login(){
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin(etLogin.getText().toString());
        userCredentials.setPassword(etPassword.getText().toString());
        Api.getApi()
                .login(userCredentials)
                .subscribe(loginAnswer -> Toast.makeText(getActivity(), "Login success!", Toast.LENGTH_LONG).show(),
                        throwable -> {
                            Log.e("LOGIN", "login: failed", throwable);
                            //Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                        });
    }
}
