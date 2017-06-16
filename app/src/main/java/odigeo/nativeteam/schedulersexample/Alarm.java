package odigeo.nativeteam.schedulersexample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class Alarm extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, Intent intent) {
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
		wl.acquire();

		KillerMethods killerMethods = new KillerMethods();
		killerMethods.killerMethod(new OnKillerMethodFinish() {
			@Override
			public void onFinish(List<CustomPhoto> photoList) {
				Log.i("JOTO", "JobFinishedAlarm");
				sendBroadcast(context, photoList);
			}
		});

		wl.release();
	}

	public void setAlarm(Context context)
	{
		AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent i = new Intent(context, Alarm.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 6000, pi);
	}

	public void cancelAlarm(Context context)
	{
		Intent intent = new Intent(context, Alarm.class);
		PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
	}

	private void sendBroadcast (Context context, List<CustomPhoto> photoList){
		Intent intent = new Intent ("list"); //put the same message as in the filter you used in the activity when
		// registering the receiver
		intent.putParcelableArrayListExtra("photos", new ArrayList<>(photoList));
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
	}
}
