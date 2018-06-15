package com.fenghaha.mvpdemo.presenter;

public interface MvpCallBack<T> {
    void onSuccess(T data);

    void onFailure(String msg);

    void onError();

    void onComplete();
}
