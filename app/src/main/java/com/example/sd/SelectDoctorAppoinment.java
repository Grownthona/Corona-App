package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SelectDoctorAppoinment extends AppCompatActivity {

    String dname ,dspec,dtime,docdes,docpic;
    ImageView img;
    TextView name,speciaality,whichday;
    Button btn;
    CalendarView date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_doctor_appoinment);

        dname = getIntent().getStringExtra("name");
        dspec = getIntent().getStringExtra("sprcial");
        dtime = getIntent().getStringExtra("time");
        docdes = getIntent().getStringExtra("about");
        docpic = getIntent().getStringExtra("url");
        date = findViewById(R.id.calendarView2);
        whichday = findViewById(R.id.textView136);

        img = findViewById(R.id.imageView42);
        name = findViewById(R.id.textView111);
        speciaality = findViewById(R.id.textView117);

        Picasso.get().load(docpic).into(img);
        name.setText(dname);
        speciaality.setText(dspec);

        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String  curDate = String.valueOf(dayOfMonth);
                String  Year = String.valueOf(year);
                String  Month = String.valueOf(month);

                String pickdate = curDate+"/"+Month+"/"+Year;
                whichday.setText(pickdate);
            }
        });

        btn = findViewById(R.id.button13);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SelectDoctorAppoinment.this,BookDoctorAppoinment.class);
                in.putExtra("name",dname);
                in.putExtra("sprcial",dspec);
                in.putExtra("url",docpic);
                in.putExtra("about",docdes);
                in.putExtra("time",dtime);
                in.putExtra("date",whichday.getText().toString());
                startActivity(in);
            }
        });
    }
}