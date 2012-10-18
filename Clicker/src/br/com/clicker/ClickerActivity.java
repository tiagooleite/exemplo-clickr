package br.com.clicker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class ClickerActivity extends Activity {

	private static final long TIME_IN_MILISECONDS = 11000;
	private int totalOfClicks;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.totalOfClicks = 0;

		loadButtonActivity();

	}

	private void loadButtonActivity() {
		ImageButton clickButton = (ImageButton) findViewById(R.id.imageButton2);
		clickButton.setOnClickListener(clickListener());

	}

	private void loadProgressBar() {
		final int countDownInterval = 1000;
		new CountDownTimer(TIME_IN_MILISECONDS, countDownInterval) {
			
			ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
			
			int progressBarStatus = 0;

			@Override
			public void onTick(long millis) {
				progressBar.setMax(100);
				System.out.println("TEMPO MILLIS = >>>" + millis);
				System.out.println("PROGRESSS  = >>>" + progressBarStatus);
				
				if (progressBarStatus <= 100) {
					progressBar.setProgress(progressBarStatus += 10);
				}
			}

			@Override
			public void onFinish() {
				showDialog();
			}
		}.start();

	}
	
	private void showDialog() {
		AlertDialog alert = new AlertDialog.Builder(this).create();
		alert.setMessage("End of Game! \nYou did " + totalOfClicks + " clicks!");
		alert.setButton("Ok", endOfGameListener());
		alert.show();
	}
	
	private DialogInterface.OnClickListener endOfGameListener() {
		return new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		};
	}

	private OnClickListener clickListener() {
		return new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (totalOfClicks == 0) {
					loadProgressBar();
				}
				totalOfClicks++;

			}
		};
	}
}