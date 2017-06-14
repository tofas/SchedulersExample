package odigeo.nativeteam.schedulersexample;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * Created by daniel.morales on 12/6/17.
 */

public class MyService extends IntentService {

	public MyService() {

		super("myService");
	}

	public MyService(String name) {

		super(name);
	}

	@Override
	protected void onHandleIntent(@Nullable Intent intent) {

		KillerMethods killerMethods = new KillerMethods();
		killerMethods.killerMethod(new OnKillerMethodFinish() {
			@Override
			public void onFinish(List<CustomPhoto> photoList) {

				Log.i("JOTO", "JobFinishedService");
			}
		});
	}
}
