package svnadvanceproject.sol.svn.com.expenditure;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ImageButton image_income,image_expense,image_transaction,image_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image_income = (ImageButton) findViewById(R.id.image_income);
        image_expense = (ImageButton)findViewById(R.id.image_expense);
        image_transaction =(ImageButton) findViewById(R.id.image_transaction);
        image_share = (ImageButton)findViewById(R.id.image_setting);

        image_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,income.class);
                startActivity(i);


            }
        });
        image_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,expense.class);
                startActivity(i);

            }
        });
        image_transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,transaction.class);
                startActivity(i);

            }
        });
        image_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,setting.class);
                startActivity(i);

            }
        });

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM,Calendar.PM);

        PendingIntent pendingIntent;
        Intent myIntent = new Intent(MainActivity.this, MyReciever.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
    }
}
