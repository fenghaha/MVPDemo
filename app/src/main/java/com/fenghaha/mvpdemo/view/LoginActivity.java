package com.fenghaha.mvpdemo.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fenghaha.mvpdemo.R;
import com.fenghaha.mvpdemo.presenter.LoginPresenter;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {
    private EditText userName;
    private EditText password;
    private ProgressDialog mProgressDialog;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);
        setUpViews();
    }

    private void setUpViews() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        Button mBack = findViewById(R.id.bt_login_back);
        Button logIn = findViewById(R.id.bt_login);
        userName = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        mBack.setOnClickListener(this);
        logIn.setOnClickListener(this);
    }

    @Override
    public void completeLogin() {
        mProgressDialog.dismiss();
        MainActivity.actionStart(this);
        finish();
    }


    @Override
    public void showLoading() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErr(String msg) {
        showToast(msg);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login_back:
                finish();
                break;
            case R.id.bt_login:
                presenter.login(userName.getText().toString(),password.getText().toString());
        }
    }
}
