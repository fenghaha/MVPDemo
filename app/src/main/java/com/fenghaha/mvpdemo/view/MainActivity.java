package com.fenghaha.mvpdemo.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.fenghaha.mvpdemo.R;
import com.fenghaha.mvpdemo.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainPresenter presenter = new MainPresenter(this);
        Button button = findViewById(R.id.btn_exit);
        button.setOnClickListener(v -> presenter.exit());
    }
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void exit() {
        finish();
    }
}
