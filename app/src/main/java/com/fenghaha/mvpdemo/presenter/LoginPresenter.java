package com.fenghaha.mvpdemo.presenter;

import com.fenghaha.mvpdemo.model.LoginModel;
import com.fenghaha.mvpdemo.view.LoginView;

/**
 * Created by FengHaHa on2018/6/15 0015 20:08
 */
public class LoginPresenter implements MvpCallBack<String> {
    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    public void login(String username, String password) {
        loginView.showLoading();
        loginModel.login(username, password, this);
    }

    @Override
    public void onSuccess(String data) {
        loginView.hideLoading();
        loginView.showToast("登陆成功!");
        loginView.completeLogin();
    }

    @Override
    public void onFailure(String msg) {
        loginView.hideLoading();
        loginView.showToast(msg);
    }

    @Override
    public void onError() {
        loginView.hideLoading();
        loginView.showToast("登录失败!");
    }

    @Override
    public void onComplete() {
        loginView.hideLoading();
    }
}
