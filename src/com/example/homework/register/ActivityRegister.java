package com.example.homework.register;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homework.R;
import com.example.homework.base.BaseActivity;
import com.example.homework.base.BaseModel;
import com.example.homework.base.ModelFailureResponse;
import com.example.homework.base.ModelSuccessResponse;

public class ActivityRegister extends BaseActivity {

	Button registerBtn;
	EditText etUsername, etEmail, etPassword;
	ApiRegister api = new ApiRegister();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		etEmail = (EditText) findViewById(R.id.et_email);
		registerBtn = (Button) findViewById(R.id.btn_register);

		api.setApiListener(this);

		registerBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (checkInternet() == false) {
					showConnectionError();
				} else {
					onRegisterPressed();
				}

			}
		});
	}

	public boolean checkInternet() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			return false;
		} else
			return true;
	}

	public void onRegisterPressed() {
		registerBtn.setEnabled(false);
		String username = getUsername();
		String password = getPassword();
		String email = getEmail();
		boolean cancel = false;

		if (TextUtils.isEmpty(username)) {
			showErrorDialog("Please insert a username!");
			cancel = true;
		} else if (TextUtils.isEmpty(email)) {
			showErrorDialog("Please insert a valid email");
			cancel = true;
		} else if (password.length() < 8) {
			showErrorDialog("The password must be at least 8 characters long");
			cancel = true;
		}

		if (cancel) {
			registerBtn.setEnabled(true);
		} else {
			api.register(username, email, password);
		}
	}

	private String getUsername() {
		return etUsername.getText().toString();
	}

	private String getPassword() {
		return etPassword.getText().toString();
	}

	private String getEmail() {
		return etEmail.getText().toString();
	}

	public void onResponse(BaseModel model) {
		if (model instanceof ModelSuccessResponse) {
			registerBtn.setEnabled(false);
			Toast.makeText(getApplicationContext(),
					"Account Successfully Created", Toast.LENGTH_LONG).show();
			finish();
		} else if (model instanceof ModelFailureResponse) {
			showErrorDialog("User Already Exists!");
			registerBtn.setEnabled(true);
			etUsername.setText("");
			etEmail.setText("");
			etPassword.setText("");
		}
	}

}