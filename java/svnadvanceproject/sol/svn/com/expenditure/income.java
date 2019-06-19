package svnadvanceproject.sol.svn.com.expenditure;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class income extends AppCompatActivity {
    String[] occupation;
    String[] payment_mode;
    EditText edt_date,edt_time,edt_amount,edt_des;
    Button btn_save;
    Spinner sp_occ,sp_pay;

    SQLiteDatabase sqlitedatabase;
    transaction tran;
    DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addincome);

        edt_date= (EditText)findViewById(R.id.date);
        edt_time=(EditText)findViewById(R.id.time);
        edt_amount=(EditText)findViewById(R.id.amount);
        edt_des =(EditText)findViewById(R.id.des);
        sp_occ = (Spinner) findViewById(R.id.spinner1);
        sp_pay= (Spinner)findViewById(R.id.spinner2);
        btn_save= (Button)findViewById(R.id.btn_save);


        dbAdapter=new DBAdapter(getApplicationContext());
        Log.d("database", "onCreate: adapter initialized");
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.open();
                //INSERT

                long result=dbAdapter.insertIncome(edt_date.getText().toString(), edt_time.getText().toString(),
                        edt_amount.getText().toString(), sp_occ.getSelectedItem().toString(),edt_des.getText().toString()
                ,sp_pay.getSelectedItem().toString());

               Toast.makeText(income.this,"Record"+result+" saved successfully!!!!",Toast.LENGTH_LONG).show();

                dbAdapter.close();

            }
        });


// SPINNER CODE
        occupation=getResources().getStringArray(R.array.occupation);
        payment_mode=getResources().getStringArray(R.array.payment_mode);


        ArrayAdapter<String> arr_occ = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, occupation);
        sp_occ.setAdapter(arr_occ);
        ArrayAdapter<String> arr_pay = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, payment_mode);
        sp_pay.setAdapter(arr_pay);

        sp_occ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                int index = arg0.getSelectedItemPosition();
                           }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) { }
        });

        sp_pay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                int index = arg0.getSelectedItemPosition();
                           }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) { }
        });
        //END OF SPINNER CODE
        //=========================

        // DATE PICKER
        final Calendar mycalendar = Calendar.getInstance();
        final EditText edit_exp_date = (EditText)findViewById(R.id.date);

        edit_exp_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mycalendar.set(Calendar.YEAR,year);
                        mycalendar.set(Calendar.MONTH,month);
                        mycalendar.set(Calendar.DAY_OF_MONTH,day);

                        String myformat ="DD/MM/YY";
                        SimpleDateFormat sdf = new SimpleDateFormat(myformat, Locale.US);

                        edit_exp_date.setText(day + "-"+ (month + 1) + "-" + year);


                    }
                };
                new DatePickerDialog(income.this, date ,mycalendar.get(Calendar.YEAR),mycalendar.get(Calendar.MONTH),
                        mycalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        // END OF DATE PICKER
        //=================
        // TIME PICKER

        final Calendar mycalendartime = Calendar.getInstance();
        final EditText edit_exp_time =(EditText) findViewById(R.id.time);

        edit_exp_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        mycalendartime.get(Calendar.HOUR_OF_DAY);
                        mycalendartime.get(Calendar.MINUTE);

                        String ampm;

                        if(hour >= 12) {
                            ampm = "PM";
                        }
                        else{
                            ampm = "AM";
                        }
                        edit_exp_time.setText(String.format("%02d:%02d",hour,min)+ampm);

                    }
                };

                new TimePickerDialog(income.this,time,mycalendartime.get(Calendar.HOUR_OF_DAY),
                        mycalendartime.get(Calendar.MINUTE),true).show();

            }
        });

        //END OF TIME PICKER

    }

    private void ShowToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }



}
