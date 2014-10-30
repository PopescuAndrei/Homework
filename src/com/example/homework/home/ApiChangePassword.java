package com.example.homework.home;

import android.util.Log;

import com.example.homework.DBFields;
import com.example.homework.base.BaseApiInterface;
import com.example.homework.base.ModelFailureResponse;
import com.example.homework.base.ModelSuccessResponse;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ApiChangePassword extends BaseApiInterface {

	final void p_changePassword(final String oldPassword,
			final String newPassword) {

		ParseQuery<ParseUser> query = ParseUser.getQuery();
		ParseUser user = ParseUser.getCurrentUser();

		query.getInBackground(user.getObjectId(), new GetCallback<ParseUser>() {
			public void done(ParseUser object, ParseException e) {
				object.setPassword(newPassword);
				object.saveInBackground(new SaveCallback() {

					@Override
					public void done(ParseException e) {
						if (e == null) {
							DBFields.pass = newPassword;
							Log.i("ajunge in done", newPassword + " "
									+ DBFields.pass);
							apiListener.onResponse(new ModelSuccessResponse());
						} else {
							if (apiListener != null) {
								apiListener
										.onResponse(new ModelFailureResponse());
							}
						}

					}
				});
			}
		});
	}
}