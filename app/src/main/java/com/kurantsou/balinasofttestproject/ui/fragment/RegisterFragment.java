package com.kurantsou.balinasofttestproject.ui.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kurantsou.balinasofttestproject.BR;
import com.kurantsou.balinasofttestproject.R;
import com.kurantsou.balinasofttestproject.api.Api;
import com.kurantsou.balinasofttestproject.databinding.FragmentRegisterBinding;
import com.kurantsou.balinasofttestproject.model.UserCredentials;
import com.kurantsou.balinasofttestproject.model.uimodel.RegisterModel;

import java.net.HttpURLConnection;
import java.util.Map;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "RegisterFragment";

    FragmentRegisterBinding mBinding;

    RegisterModel model;

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

        mBinding = DataBindingUtil.bind(view);

        model = new RegisterModel(getActivity());
        mBinding.setModel(model);

        model.addOnPropertyChangedCallback(propertyChangedCallback);

        mBinding.btnRegister.setOnClickListener(this);

        return view;
    }

    private Observable.OnPropertyChangedCallback propertyChangedCallback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            if (propertyId == BR.loginError)
                    mBinding.loginWrapper.setError(model.getLoginError());
            else if (propertyId == BR.passwordError)
                mBinding.passwordWrapper.setError(model.getPasswordError());
            else if (propertyId == BR.confirmPasswordError)
                mBinding.confirmPasswordWrapper.setError(model.getConfirmPasswordError());
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                register();
                break;
        }
    }

    private void register() {
        mBinding.btnRegister.setEnabled(false);
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin(model.getLogin());
        userCredentials.setPassword(model.getPassword());
        Api.getApi()
                .register(userCredentials)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginAnswer -> {
                            Toast.makeText(getActivity().getApplicationContext(), "Register success!", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "register: success, username: " + userCredentials.getLogin() + ", token: " + loginAnswer.getToken());
                            onSignInListener.onSignIn(userCredentials, loginAnswer.getToken());
                        },
                        throwable -> {
                            Log.e("Register", "register: ", throwable);
                            if (throwable instanceof HttpException && ((HttpException) throwable).code() == HttpURLConnection.HTTP_BAD_REQUEST){
                                model.setLoginError(getString(R.string.login_used));
                            }
                        },
                        () -> {
                            mBinding.btnRegister.setEnabled(true);
                        });
    }
}
