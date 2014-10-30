package com.example.homework.base;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.homework.R;
import com.example.homework.utils.PopDialog;

/**
 * API Interface is used for adding / automatic listening to events in APIs
 * 
 * @author Andrei
 * 
 */
public class BaseActivity extends Activity implements BaseApiListener {

	private Handler mainHandler = new Handler();
	private List<BaseApiInterface> apiInterfaces = new ArrayList<BaseApiInterface>();

	/**
	 * call this in onCreate for adding apiInterfaces
	 * 
	 * @param apiInterface
	 */
	protected void addApiInterface(BaseApiInterface apiInterface) {
		apiInterfaces.add(apiInterface);
	}

	/**
	 * Sets apiListeners for this activity
	 * 
	 * @param listener
	 */
	private void setApiInterfacesListener(BaseApiListener listener) {
		int size = apiInterfaces.size();
		for (int i = 0; i < size; i++) {
			apiInterfaces.get(i).setApiListener(listener);
		}
	}

	/**
	 *used to provide an action for the view based on controller's response
	 * 
	 */
	@Override
	public void onResponse(BaseModel model) {
		// TODO Auto-generated method stub

	}

	
	/**
	 * Implementation for the application states(life cycles)
	 */
	@Override
	protected void onPause() {
		super.onPause();
		setApiInterfacesListener(null);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setApiInterfacesListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}
	
	
	// *displays a dialog box with a given message */
	public void showErrorDialog(final String text) {
		if (!TextUtils.isEmpty(text)) {
			if (Looper.myLooper() == Looper.getMainLooper()) {
				PopDialog.showDialog(this, "Error", text, null);
			} else {
				mainHandler.post(new Runnable() {
					@Override
					public void run() {
						PopDialog.showDialog(BaseActivity.this, "Error", text,
								null);
					}
				});
			}
		}
	}

	/** show an alert dialog for internet connection failure */
	protected void showConnectionError() {
		showErrorDialog(getString(R.string.noInternet));
	}

	
	/** check for internet connection */
	public boolean checkInternet() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			return false;
		} else
			return true;
	}
}