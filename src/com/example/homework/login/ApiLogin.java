package com.example.homework.login;

import com.example.homework.base.BaseApiInterface;
import com.example.homework.base.ModelFailureResponse;
import com.example.homework.base.ModelSuccessResponse;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class ApiLogin extends BaseApiInterface {

	public void login(String username, String password) {
		ParseUser.logInInBackground(username, password, new LogInCallback() {

			@Override
			public void done(ParseUser user, ParseException e) {
				if (e == null) {
					onLogin("");
				} else {
					String mess = e.getMessage();
					onLogin(mess);
				}

			}
		});
	}

	public void onLogin(String error) {
		if (apiListener != null) {
			if (error.equals("")) {
				apiListener.onResponse(new ModelSuccessResponse());

			} else {
				ModelFailureResponse mfr = new ModelFailureResponse();
				mfr.setDescription(error);
				apiListener.onResponse(mfr);
			}
		}
	}
}
