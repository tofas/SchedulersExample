package odigeo.nativeteam.schedulersexample;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	Button buttonService;
	Button buttonAlarmManager;
	Button buttonJobScheduler;
	RecyclerView recyclerView;

	MyRecyclerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		setListeners();
	}

	@Override
	protected void onResume() {
		super.onResume();
		LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("list"));
	}

	@Override
	protected void onPause() {
		super.onPause();
		LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
	}

	private void initViews() {

		buttonService = (Button) findViewById(R.id.buttonService);
		buttonAlarmManager = (Button) findViewById(R.id.buttonAlarmManager);
		buttonJobScheduler = (Button) findViewById(R.id.buttonJobScheduler);
		recyclerView = (RecyclerView) findViewById(R.id.recycler);

		adapter = new MyRecyclerAdapter(getApplicationContext(), new ArrayList<CustomPhoto>());
		recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
		recyclerView.setAdapter(adapter);
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
				Alarm alarm = new Alarm();
				alarm.setAlarm(getApplicationContext());

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

	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			ArrayList<CustomPhoto> photos = intent.getParcelableArrayListExtra("photos");
			adapter.refresh(photos);
		}
	};
}
