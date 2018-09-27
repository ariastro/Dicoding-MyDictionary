package com.example.astronout.mydictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_kata)
    TextView tv_kata;

    @BindView(R.id.tv_translate)
    TextView tv_translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Intent intentThatStartThisActivity = getIntent();
        if (intentThatStartThisActivity.hasExtra("extra_kata")) {
            String kata = getIntent().getExtras().getString("extra_kata");
            String translate = getIntent().getExtras().getString("extra_translate");

            tv_kata.setText(kata);
            tv_translate.setText(translate);
        }
    }
}
