package com.fenghaha.mvpdemo.model;

import com.fenghaha.mvpdemo.presenter.MvpCallBack;
import com.fenghaha.mvpdemo.util.HttpUtil;
import com.fenghaha.mvpdemo.util.MyTextUtil;

public class LoginModel {
    public void login(String username, String password, MvpCallBack<String> callBack) {
        if (MyTextUtil.isEmpty(username) || MyTextUtil.isEmpty(password)) {
            callBack.onFailure("请正确输入账号密码!");
        } else {
            String url = "https://wx.idsbllp.cn/api/verify";
            String param = "stuNum=" + username + "&idNum=" + password;
            HttpUtil.post(url, param, new HttpUtil.HttpCallBack() {
                @Override
                public void onResponse(HttpUtil.Response response) {
                    if (response.isOk()) {
                       callBack.onSuccess("登陆成功!");
                    } else {
                        callBack.onFailure(response.getInfo());
                    }
                }

                @Override
                public void onFail(String reason) {
                    callBack.onError();
                }
            });
        }
    }
}
