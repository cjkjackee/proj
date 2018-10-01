package com.test.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;


public class calender extends AppCompatActivity {
    CalendarView calendarView;
    TextView date_textview;
    String date;
    TextView condition_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        date_textview = (TextView) findViewById(R.id.date_textview);

        condition_textview = (TextView) findViewById(R.id.condition_textview);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "/" + (month + 1) + "/" + dayOfMonth;
                date_textview.setText(date);

                if (date_textview.getText().toString().equals("2018/9/30")) {
                    condition_textview.setText("Temperatue:30 degree");
                } else {
                    condition_textview.setText("No record!");
                }
            }
        });

        condition_button();
    }

    private void condition_button(){
        Button modify_btn= (Button) findViewById(R.id.modify_btn);
        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(calender.this,condittions.class));
            }
        });
    }
}
