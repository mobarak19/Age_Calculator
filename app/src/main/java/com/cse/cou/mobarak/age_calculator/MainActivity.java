package com.cse.cou.mobarak.age_calculator;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Calendar myCalendar1,myCalendar2;
    EditText strting_time,finishing_time;
    Button submit;
   static int syear,smonth,sday,dyear,dmonth,dday,ryear,rday,rmonth;
    TextView show_age;
    String str_starting,str_finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        strting_time= (EditText) findViewById(R.id.starting_time);
        submit= (Button) findViewById(R.id.submit);
        finishing_time= (EditText) findViewById(R.id.finishing_time);
        show_age= (TextView) findViewById(R.id.show_age);




        //initializing the calender objects

        myCalendar1 = Calendar.getInstance();
        myCalendar2 = Calendar.getInstance();

        final   DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, monthOfYear);
                myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                strting_time.setText(sdf.format(myCalendar1.getTime()));
            }

        };
        final   DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                finishing_time.setText(sdf.format(myCalendar2.getTime()));            }

        };
        strting_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date1, myCalendar1
                        .get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                        myCalendar1.get(Calendar.DAY_OF_MONTH)).show();


            }
        });

        finishing_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date2, myCalendar2
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                str_starting=strting_time.getText().toString();
                str_finish=finishing_time.getText().toString();


                if(str_starting.isEmpty() || str_finish.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Invalid input",Toast.LENGTH_LONG).show();
                }
                else {


                    String[] item = str_starting.replace("/", ", ").split(", ");
                    syear = Integer.parseInt(item[2]);
                    smonth = Integer.parseInt(item[1]);
                    sday = Integer.parseInt(item[0]);
                    String[] item2 = str_finish.replace("/", ", ").split(", ");
                    dyear = Integer.parseInt(item2[2]);
                    dmonth = Integer.parseInt(item2[1]);
                    dday = Integer.parseInt(item2[0]);



                    if (dday >= sday) {

                        rday = dday - sday;
                        if (dmonth >= smonth) {
                            rmonth = dmonth - smonth;
                            ryear = dyear - syear;

                        } else {
                            dyear = dyear - 1;
                            dmonth = dmonth + 12;
                            rmonth = dmonth - smonth;
                            ryear = dyear - syear;

                        }
                    } else {
                        dmonth = dmonth - 1;
                        dday = dday + 30;
                        rday = dday - sday;
                        if (dmonth >= smonth) {
                            rmonth = dmonth - smonth;
                            ryear = dyear - syear;

                        } else {
                            dyear = dyear - 1;
                            dmonth = dmonth + 12;
                            rmonth = dmonth - smonth;
                            ryear = dyear - syear;

                        }

                    }

                    if(ryear<0){
                        Toast.makeText(getApplicationContext(),"Invalid input",Toast.LENGTH_LONG).show();

                    }else{
                        show_age.setText(ryear + " Years " + rmonth + " months " + rday + " days");






                    }




                }


            }
        });

    }

}
