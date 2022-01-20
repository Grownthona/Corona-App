package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class Choose_Option extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView mail,num,whichday,whichtime;
    CalendarView date;
    Button btn;
    String apptime,appdate,appbirth,appgender,fullname,enail,phonenum;
    AutoCompleteTextView spinner;

    String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choose__option);

        date = findViewById(R.id.calendarView);
        btn = findViewById(R.id.gotest);
        spinner = findViewById(R.id.autoC);
        whichday = findViewById(R.id.showdte);

        enail = getIntent().getStringExtra("mail");
        phonenum = getIntent().getStringExtra("phn");
        fullname = getIntent().getStringExtra("fname");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(whichday.getText().equals("")){
                    Toast.makeText(Choose_Option.this, "Please Choose an Appoinment Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                String text = spinner.getText().toString();
                Intent in = new Intent(Choose_Option.this,Corona_Test4.class);
                in.putExtra("mail",enail);
                in.putExtra("phn",phonenum);
                in.putExtra("fname",fullname);
                in.putExtra("date",whichday.getText().toString());
                in.putExtra("time",text);
                startActivity(in);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}