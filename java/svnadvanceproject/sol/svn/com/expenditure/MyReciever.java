package svnadvanceproject.sol.svn.com.expenditure;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

/**
 * Created by Naresh Nerkar on 25-09-2018.
 */


public class MyReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //  Toast.makeText(context, "Alarm running", Toast.LENGTH_SHORT).show();

      /*Intent service1 = new Intent(context, MyAlarmService.class);
        context.startService(service1);*/
        Log.i("App", "called receiver method");
        try {
            //  Utils.generateNotification(context);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
