package svnadvanceproject.sol.svn.com.expenditure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBAdapter  {

    static int version=1;

    static final String KEY_ROWID = "ID";
    static final String KEY_DATE = "DATE";
    static final String KEY_TIME = "TIME";
    static final String KEY_AMOUNT = "AMOUNT";
    static final String KEY_OCCUPATION = "OCCUPATION";
    static final String KEY_DESCRIPTION = "DESCRIPTION";
    static final String KEY_PAYMENTMODE = "PAYMENTMODE";
    static final String KEY_THING ="THING";
    static final String DATABASE_NAME = "MyDB1";
    static final String INCOME="income";
    static final String EXPENSE="expense";

  //  static  String DATABASE_INCOME = "Create Table "+INCOME+" (DATE text not null,"+ " TIME text not null,"+ "AMOUNT text not null,"
  //          +"OCCUPATION text,"+"PAYMENTMODE text);";


  //  static  String DATABASE_EXPENSE = "Create Table "+EXPENSE+" (DATE text not null,"+
  //          " TIME text not null,"+ "AMOUNT text not null,"
  //          +"THING text,"+"PAYMENTMODE text);";


    Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        //DBHelper = new DatabaseHelper(context);
        db=SQLiteDatabase.openOrCreateDatabase(String.valueOf(R.raw.MyDB),null);
  //      db.execSQL(DATABASE_INCOME);
  //      db.execSQL(DATABASE_EXPENSE);
        Log.d("database","in DBAdapter construcror");
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, String.valueOf(R.raw.MyDB), null,1 );
            Log.d("database","in constructor table created");
        }


        @Override
        public void onCreate(SQLiteDatabase db){
           // db.execSQL(DATABASE_INCOME);
           // db.execSQL(DATABASE_EXPENSE);
            Log.d("database","table created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+R.raw.MyDB);
        }
    }

    //---opens the database--
    public DBAdapter open() throws SQLException {

        //db = DBHelper.getWritableDatabase();
       // db = DBHelper.getReadableDatabase();
        return this;
    }

    //---closes the database--
    public void close() {
        DBHelper.close();
    }

    //---insert a income into the database--
    public long insertIncome(String DATE, String TIME, String AMOUNT, String OCCUPATION, String DESCRIPTION, String PAYMENTMODE)
    {
        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_DATE, DATE);
        initialValues.put(KEY_TIME, TIME);
        initialValues.put(KEY_AMOUNT, AMOUNT);
        initialValues.put(KEY_OCCUPATION, OCCUPATION);
        initialValues.put(KEY_DESCRIPTION, DESCRIPTION);
        initialValues.put(KEY_PAYMENTMODE, PAYMENTMODE);

        long l= db.insert(INCOME,null,initialValues);
        return l;
    }

    //---insert a income into the database--
    public long insertExpense(String DATE, String TIME, String AMOUNT, String THING, String DESCRIPTION, String PAYMENTMODE)
    {
        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_DATE, DATE);
        initialValues.put(KEY_TIME, TIME);
        initialValues.put(KEY_AMOUNT, AMOUNT);
        initialValues.put(KEY_THING, THING);
        initialValues.put(KEY_DESCRIPTION, DESCRIPTION);
        initialValues.put(KEY_PAYMENTMODE, PAYMENTMODE);

        long m= db.insert(EXPENSE,null,initialValues);
        return m;
    }

    Cursor getAllIncome(){

        SQLiteDatabase db=DBHelper.getReadableDatabase();

        Cursor resultset = db.query(DATABASE_INCOME,new String[]{KEY_DATE,KEY_TIME,KEY_AMOUNT,KEY_OCCUPATION,
                KEY_DESCRIPTION,KEY_PAYMENTMODE},null,null,
                null,null,null);

        return resultset;
    }

    Cursor getAllExpense(){
        SQLiteDatabase db=DBHelper.getReadableDatabase();

        Cursor resultset=db.query(DATABASE_EXPENSE,new String[]{KEY_DATE,KEY_TIME,KEY_AMOUNT,KEY_THING,
                        KEY_DESCRIPTION,KEY_PAYMENTMODE},null,null,
                null,null,null);

        return resultset;
    }

    //---deletes a particular income--
    public boolean deleteIncome(long rowId) {
        SQLiteDatabase db=DBHelper.getWritableDatabase();
        boolean b=db.delete(DATABASE_INCOME,KEY_ROWID+" = "+rowId,null)>0;
        db.close();
        return b;
    }

    public boolean updateIncome(long rowId, String date, String time, String amount, String occupation, String description, String paymentmode)
    {
        SQLiteDatabase db=DBHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_DATE,date);
        contentValues.put(KEY_TIME,time);
        contentValues.put(KEY_AMOUNT,amount);
        contentValues.put(KEY_OCCUPATION,occupation);
        contentValues.put(KEY_DESCRIPTION,description);
        contentValues.put(KEY_PAYMENTMODE,paymentmode);

        final int update = db.update(DATABASE_INCOME, contentValues, KEY_ROWID + " = " + rowId, null);
        return update > 0;    }


    public boolean updateExpense(long rowId, String date, String time, String amount, String thing, String description, String paymentmode)
    {
        SQLiteDatabase db=DBHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_DATE,date);
        contentValues.put(KEY_TIME,time);
        contentValues.put(KEY_AMOUNT,amount);
        contentValues.put(KEY_THING,thing);
        contentValues.put(KEY_DESCRIPTION,description);
        contentValues.put(KEY_PAYMENTMODE,paymentmode);

        final int update = db.update(DATABASE_EXPENSE, contentValues, KEY_ROWID + " = " + rowId, null);
        return update > 0;    }

}
