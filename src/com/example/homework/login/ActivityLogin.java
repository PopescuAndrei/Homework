package com.example.homework.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.homework.R;
import com.example.homework.base.BaseActivity;
import com.example.homework.base.BaseModel;
import com.example.homework.base.ModelFailureResponse;
import com.example.homework.base.ModelSuccessResponse;
import com.example.homework.home.ActivityHome;

public class ActivityLogin extends BaseActivity {

	EditText etUsername, etPassword;
	Button btnLogin;
	ApiLogin api = new ApiLogin();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		btnLogin = (Button) findViewById(R.id.btn_login);

		api.setApiListener(this);
		
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (checkInternet() == false) {
					showConnectionError();
				} else {
					btnLogin.setEnabled(false);
					onLoginPressed();
				}

			}
		});
	}

	public void onLoginPressed() {
		btnLogin.setEnabled(false);
		String username = getUsername();
		String password = getPassword();
		boolean cancel = false;

		if (TextUtils.isEmpty(username)) {
			showErrorDialog("Please insert a username");
			cancel = true;
		} else if (password.length() < 8) {
			showErrorDialog("Password must be at least 8 characters long");
			cancel = true;
		}

		if (cancel) {
			btnLogin.setEnabled(true);
		} else {
			api.login(username, password);
		}
	}

	private String getUsername() {
		return etUsername.getText().toString();
	}

	private String getPassword() {
		return etPassword.getText().toString();
	}

	public void onResponse(BaseModel model) {
		if (model instanceof ModelSuccessResponse) {
			btnLogin.setEnabled(false);
			finish();
			startActivity(new Intent(ActivityLogin.this, ActivityHome.class));
		} else if (model instanceof ModelFailureResponse) {
			showErrorDialog("Username or password incorrect");
			btnLogin.setEnabled(true);
			etUsername.setText("");
			etPassword.setText("");
		}
	}
}
