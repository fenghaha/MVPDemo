package com.fenghaha.mvpdemo.view;

import android.content.Context;

public interface BaseView {

    void showLoading();

    void hideLoading();

    void showToast(String msg);

    void showErr(String msg);

    Context getContext();
}
