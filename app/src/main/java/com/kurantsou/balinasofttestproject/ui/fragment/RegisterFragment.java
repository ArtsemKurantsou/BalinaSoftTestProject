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

import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener{

    private EditText etLogin;
    private EditText etPassword;
    private EditText etConfirmPassword;



    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        etLogin = (EditText) view.findViewById(R.id.etLogin);
        etPassword = (EditText) view.findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) view.findViewById(R.id.etConfirmPassword);
        view.findViewById(R.id.btnRegister).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister:
                register();
                break;
        }
    }

    private void register(){
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin(etLogin.getText().toString());
        userCredentials.setPassword(etPassword.getText().toString());
        Api.getApi()
                .register(userCredentials)
                .subscribeOn(Schedulers.io())
                .subscribe(loginAnswer -> Toast.makeText(getActivity(), "Register success!", Toast.LENGTH_LONG).show(),
                        throwable -> Log.e("Register", "register: ", throwable));
    }
}
