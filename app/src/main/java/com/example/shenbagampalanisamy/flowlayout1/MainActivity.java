package com.example.shenbagampalanisamy.flowlayout1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    FlowLayout f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        f=new FlowLayout(this);
        setContentView(f);
        setContentView(R.layout.activity_main);
    }
}
