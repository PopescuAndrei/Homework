package com.example.homework.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.homework.R;
import com.example.homework.base.BaseActivity;

public class ActivitySettings extends BaseActivity {

	Button changePass;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		changePass = (Button) findViewById(R.id.changePasswordEt);
		changePass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(ActivitySettings.this,
						ActivityChangePassword.class));

			}
		});
	}
}
