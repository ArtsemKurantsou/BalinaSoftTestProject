package com.kurantsou.balinasofttestproject.model;

/**
 * Created by artem on 07.08.2017.
 */

public class LoginAnswer {

    private String login;
    private String token;
    private long userId;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
