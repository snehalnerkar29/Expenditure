package svnadvanceproject.sol.svn.com.expenditure;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.HOUR_OF_DAY;

public class expense  extends AppCompatActivity {

    String[] thing;
    String[] pay_mode;
    EditText edt_date,edt_time,edt_amount,edt_des;
    Button btn_save;
    Spinner sp_thing,sp_pay;

    SQLiteDatabase sqlitedatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addexpense);

        final DBAdapter dbAdapter = new DBAdapter(this);

        edt_date= (EditText) findViewById(R.id.date);
        edt_time= (EditText)findViewById(R.id.time);
        edt_amount= (EditText)findViewById(R.id.amount);
        edt_des =(EditText)findViewById(R.id.des);
        sp_thing = (Spinner) findViewById(R.id.spinner1);
        sp_pay=(Spinner) findViewById(R.id.spinner2);
        btn_save= (Button) findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.open();
                //INSERT

                long result =dbAdapter.insertExpense(edt_date.getText().toString(), edt_time.getText().toString(),
                        edt_amount.getText().toString(), sp_thing.getSelectedItem().toString(),edt_des.getText().toString()
                        ,sp_pay.getSelectedItem().toString());


                Toast.makeText(expense.this, "Record "+result+" Saved Succesfully!!!", Toast.LENGTH_SHORT).show();

                dbAdapter.close();
                //CLOSE
            }
        });


// SPINNER CODE
        thing=getResources().getStringArray(R.array.thing);
        pay_mode=getResources().getStringArray(R.array.payment_mode);

        Spinner s_thing = (Spinner)findViewById(R.id.spinner3);
        Spinner s_pay_m= (Spinner)findViewById(R.id.spinner4);


        ArrayAdapter<String> arr_thing = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, thing);
        s_thing.setAdapter(arr_thing);
        ArrayAdapter<String> arr_pay = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pay_mode);
        s_pay_m.setAdapter(arr_pay);

        s_thing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                int index = arg0.getSelectedItemPosition();
                            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) { }
        });
        s_pay_m.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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

        //DATEPICKER CODE

        final Calendar mycalendar = Calendar.getInstance();
        final EditText edit_exp_date = (EditText) findViewById(R.id.date);

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
                        edit_exp_date.setText(day+ "-"+ (month + 1) + "-" + year);


                    }
                };
                new DatePickerDialog(expense.this, date ,mycalendar.get(Calendar.YEAR),mycalendar.get(Calendar.MONTH),
                        mycalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
// END OF DATEPICKER CODE
//=================
  // TIME PICKER

        final Calendar mycalendartime = Calendar.getInstance();
        final EditText edit_exp_time = (EditText) findViewById(R.id.time);

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

                new TimePickerDialog(expense.this,time,mycalendartime.get(Calendar.HOUR_OF_DAY),
                        mycalendartime.get(Calendar.MINUTE),true).show();

            }
        });

        //END OF TIME PICKER
    }


}

