package odigeo.nativeteam.schedulersexample;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import java.util.List;

public class MyJobService extends JobService {

	JobParameters jobParameters;
	int remainingCalls = 0;

	OnKillerMethodFinish onKillerMethodFinish = new OnKillerMethodFinish() {
		@Override
		public void onFinish(List<CustomPhoto> photoList) {

			++remainingCalls;
			if(remainingCalls == 5) {
				Log.i("JOTO", "JobFinishedScheduler");
				remainingCalls = 0;
				jobFinished(jobParameters, false);
			}
		}
	};

	@Override
	public boolean onStartJob(JobParameters jobParameters) {
		this.jobParameters = jobParameters;
		KillerMethods killerMethods = new KillerMethods();
		killerMethods.killerMethod(onKillerMethodFinish);

		return false;
	}

	@Override
	public boolean onStopJob(JobParameters jobParameters) {

		return false;
	}
}
