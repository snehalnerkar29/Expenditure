package svnadvanceproject.sol.svn.com.expenditure;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class transaction  extends AppCompatActivity implements View.OnClickListener {

    DBAdapter dbAdapter;
    income income;
    expense expense;
    SQLiteDatabase database;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alltransaction);

        dbAdapter=new DBAdapter(getApplicationContext());

        b1 = (Button) findViewById(R.id.btn1);
        b1.setOnClickListener(this);
        b2 = (Button) findViewById(R.id.btn2);
        b2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == b1) // print all I transactions
        {
            dbAdapter.getAllIncome();

        }
        if (v == b1) // print all E transactions
        {
            dbAdapter.getAllExpense();
        }
    }

    void showToast(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }

    void showDialog(String title,String msg){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.create();
        dialog.setCancelable(true);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.show();
    }

}
