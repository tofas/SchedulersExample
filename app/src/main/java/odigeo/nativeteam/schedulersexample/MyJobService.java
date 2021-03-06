package odigeo.nativeteam.schedulersexample;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyJobService extends JobService {

	@Override
	public boolean onStartJob(final JobParameters jobParameters) {
		KillerMethods killerMethods = new KillerMethods();
		killerMethods.killerMethod(new OnKillerMethodFinish() {
			@Override
			public void onFinish(List<CustomPhoto> photoList) {
				Log.i("JOTO", "JobFinishedScheduler");
				sendBroadcast(photoList);
				jobFinished(jobParameters, false);
			}
		});

		return false;
	}

	@Override
	public boolean onStopJob(JobParameters jobParameters) {

		return false;
	}

	private void sendBroadcast (List<CustomPhoto> photoList){
		Intent intent = new Intent ("list"); //put the same message as in the filter you used in the activity when
		// registering the receiver
		intent.putParcelableArrayListExtra("photos", new ArrayList<>(photoList));
		LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
	}
}
