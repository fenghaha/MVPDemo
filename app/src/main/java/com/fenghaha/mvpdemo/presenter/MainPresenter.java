package com.fenghaha.mvpdemo.presenter;

import com.fenghaha.mvpdemo.view.MainView;

/**
 * Created by FengHaHa on2018/6/15 0015 21:41
 */
public class MainPresenter {
    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void exit(){
        mainView.exit();
    }
}
