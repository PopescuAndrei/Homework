package com.example.homework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.homework.base.BaseActivity;
import com.example.homework.login.ActivityLogin;
import com.example.homework.register.ActivityRegister;
/**
 * First Activity
 * initialization of buttons and actions
 * @author Andrei
 *
 */
public class MainActivity extends BaseActivity {

	Button btnLogin, btnRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnLogin = (Button) findViewById(R.id.btnLoginLink);
		btnRegister = (Button) findViewById(R.id.btnRegisterLink);

		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, ActivityLogin.class));
				finish();
			}
		});

		btnRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,
						ActivityRegister.class));
				finish();

			}
		});
	}
}
