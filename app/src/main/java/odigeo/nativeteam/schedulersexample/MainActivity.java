package odigeo.nativeteam.schedulersexample;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

	Button buttonService;
	Button buttonAlarmManager;
	Button buttonJobScheduler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		setListeners();
	}

	private void initViews() {

		buttonService = (Button) findViewById(R.id.buttonService);
		buttonAlarmManager = (Button) findViewById(R.id.buttonAlarmManager);
		buttonJobScheduler = (Button) findViewById(R.id.buttonJobScheduler);
	}

	private void setListeners() {

		buttonService.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intentService = new Intent(getApplicationContext(), MyService.class);
				startService(intentService);
			}
		});

		buttonAlarmManager.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {


			}
		});

		buttonJobScheduler.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ComponentName componentName = new ComponentName(getApplicationContext(), MyJobService.class);
				JobInfo jobInfo = new JobInfo.Builder(1, componentName)
						.setRequiresCharging(true)
						.build();
				JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
				scheduler.schedule(jobInfo);
			}
		});
	}
}
