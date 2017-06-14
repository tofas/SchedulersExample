package odigeo.nativeteam.schedulersexample;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import java.util.List;

public class MyJobService extends JobService {

	@Override
	public boolean onStartJob(final JobParameters jobParameters) {
		KillerMethods killerMethods = new KillerMethods();
		killerMethods.killerMethod(new OnKillerMethodFinish() {
			@Override
			public void onFinish(List<CustomPhoto> photoList) {
				Log.i("JOTO", "JobFinishedScheduler");
				jobFinished(jobParameters, false);
			}
		});

		return false;
	}

	@Override
	public boolean onStopJob(JobParameters jobParameters) {

		return false;
	}
}
