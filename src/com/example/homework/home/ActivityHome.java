package com.example.homework.home;

import java.util.Random;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.homework.R;
import com.example.homework.base.BaseActivity;
import com.example.homework.login.ActivityLogin;
import com.parse.ParseUser;
//just initializations and setting click Listeners with actions
public class ActivityHome extends BaseActivity {

	Button btnBackground, btnSettings, btnLogout;
	LinearLayout mainLayout;
	int[] colors = { Color.YELLOW, Color.BLUE, Color.CYAN, Color.RED,
			Color.MAGENTA, Color.GREEN, Color.LTGRAY };
	Random rand;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
		btnBackground = (Button) findViewById(R.id.btn_changeBg);
		btnSettings = (Button) findViewById(R.id.btn_accountSettings);
		btnLogout = (Button) findViewById(R.id.btn_logout);
		rand = new Random();

		btnBackground.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mainLayout.setBackgroundColor(colors[rand
						.nextInt(colors.length)]);

			}
		});

		btnSettings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(ActivityHome.this,
						ActivitySettings.class));

			}
		});

		btnLogout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ParseUser.logOut();
				finish();
				startActivity(new Intent(ActivityHome.this, ActivityLogin.class));
			}

		});
	}
}
