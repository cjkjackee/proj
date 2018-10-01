package com.test.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView TextView01;
    private Button Button01;
    private Button Button02;
    private Button Button03;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView TextView01= (TextView)findViewById(R.id.TextView01);
        Button btn_map = (Button)findViewById(R.id.Button02);
        btn_map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });
        Button btn_diagnosis = (Button)findViewById(R.id.Button01);
        btn_diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,search.class);
                startActivity(intent);
            }
        });
        Button btn_calender = (Button)findViewById(R.id.Button03);
        btn_calender.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,calender.class);
                startActivity(intent);
            }
        });
    }
}