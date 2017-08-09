package com.kurantsou.balinasofttestproject.model.uimodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.kurantsou.balinasofttestproject.BR;
import com.kurantsou.balinasofttestproject.R;

/**
 * Created by artem on 09.08.2017.
 */

public class RegisterModel extends BaseObservable {

    private static final int LOGIN_MIN_LENGTH = 4;
    private static final int LOGIN_MAX_LENGTH = 32;

    private static final int PASSWORD_MIN_LENGTH = 8;
    private static final int PASSWORD_MAX_LENGTH = 32;

    private String login;
    private String password;
    private String confirmPassword;

    private boolean isCorrect = false;
    private boolean isLoginCorrect = false;
    private boolean isPasswordCorrect = false;
    private boolean isConfirmPasswordCorrect = false;

    private String loginError;
    private String passwordError;
    private String confirmPasswordError;


    private Context context;

    public RegisterModel(Context context) {
        this.context = context;
    }

    @Bindable
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        notifyPropertyChanged(BR.login);
        checkLogin();
    }

    private void checkLogin() {
        isLoginCorrect = true;
        if (login == null || (login.length() < LOGIN_MIN_LENGTH || login.length() > LOGIN_MAX_LENGTH)) {
            setLoginError(context.getString(R.string.login_length_error));
            isLoginCorrect = false;
        }
        else
            setLoginError(null);
        setCorrect();
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
        checkPassword();
    }

    private void checkPassword() {
        isPasswordCorrect = true;
        if (password == null || (password.length() < PASSWORD_MIN_LENGTH || password.length() > PASSWORD_MAX_LENGTH)) {
            setPasswordError(context.getString(R.string.password_length_error));
            isPasswordCorrect = false;
        }
        else
            setPasswordError(null);
        setCorrect();
    }

    @Bindable
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        notifyPropertyChanged(BR.confirmPassword);
        checkConfirmPassword();
    }

    private void checkConfirmPassword() {
        isConfirmPasswordCorrect = true;
        if (confirmPassword == null || !confirmPassword.equals(password)) {
            setConfirmPasswordError(context.getString(R.string.confirm_password_error));
            isConfirmPasswordCorrect = false;
        }
        else
            setConfirmPasswordError(null);
        setCorrect();
    }

    @Bindable
    public boolean isCorrect() {
        return isCorrect;
    }

    private void setCorrect() {
        setCorrect(isLoginCorrect && isPasswordCorrect && isConfirmPasswordCorrect);
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
        notifyPropertyChanged(BR.correct);
    }

    @Bindable
    public String getLoginError() {
        return loginError;
    }

    public void setLoginError(String loginError) {
        this.loginError = loginError;
        notifyPropertyChanged(BR.loginError);
    }

    @Bindable
    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
        notifyPropertyChanged(BR.passwordError);
    }

    @Bindable
    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
        notifyPropertyChanged(BR.confirmPasswordError);
    }

}
