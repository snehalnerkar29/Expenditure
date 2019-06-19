package svnadvanceproject.sol.svn.com.expenditure;

/**
 * Created by Naresh Nerkar on 25-09-2018.
 */
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class Utils {
    public static NotificationManager mManager;

    public static void generateNotification(Context context){
        //NotificationCompat.Builder nb= new NotificationCompat.Builder(context);
        NotificationCompat.Builder nb=new NotificationCompat.Builder(context);
        nb.setSmallIcon(R.drawable.em);
        nb.setContentTitle("Daily Expense Manager");
        nb.setContentText("Please fill your today's expenditure");
        nb.setTicker("Take a look");

        nb.setAutoCancel(true);

        //get the bitmap to show in notification bar
        Bitmap bitmap_image = BitmapFactory.decodeResource(context.getResources(), R.drawable.em);
        android.support.v4.app.NotificationCompat.BigPictureStyle s = new android.support.v4.app.NotificationCompat.BigPictureStyle().bigPicture(bitmap_image);
        s.setSummaryText("Please fill your today's expenditure");
        nb.setStyle(s);

        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder TSB = TaskStackBuilder.create(context);
        TSB.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        TSB.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                TSB.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        nb.setContentIntent(resultPendingIntent);
        nb.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(11221, nb.build());


    }
}
