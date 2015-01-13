package com.example.homework.feeds;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.homework.R;
import com.example.homework.base.BaseFragment;
import com.example.homework.base.BaseModel;
import com.parse.ParseUser;

public class FragmentFeeds extends BaseFragment {
	TextView tvTitleFirst, tvTitleSecond, tvTitleThird, tvDescriptionFirst,
			tvDescriptionThird;
	Button btnActionSecond;
	Context mContext;

	/**
	 * Constructors
	 */
	public FragmentFeeds() {
	}

	public static FragmentFeeds newInstance() {
		return new FragmentFeeds();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onViewCreated(View v, Bundle savedInstanceState) {
		super.onViewCreated(v, savedInstanceState);
		initUI(v);
		setJSONData();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		SCREEN_TITLE = "Change Password";
		mContext = getActivity();
		return inflater.inflate(R.layout.fragment_feed, parent, false);

	}

	@Override
	public void onResponse(BaseModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initUI(View view) {
		tvTitleFirst = (TextView) view.findViewById(R.id.tvTitleFirst);
		tvTitleSecond = (TextView) view.findViewById(R.id.tvTitleSecond);
		tvTitleThird = (TextView) view.findViewById(R.id.tvTitleThird);
		btnActionSecond = (Button) view.findViewById(R.id.btnActionSecond);
		tvDescriptionFirst = (TextView) view
				.findViewById(R.id.tvDescriptionFirst);
		tvDescriptionThird = (TextView) view
				.findViewById(R.id.tvDescriptionThird);

	}

	@Override
	protected void onAfterStart() {
		// TODO Auto-generated method stub

	}

	/** Reads the JSON from the assets */
	public JSONObject parseJSONData() {
		String jsonString = null;
		JSONObject jsonObject = null;
		try {
			InputStream inputStream = getActivity().getAssets()
					.open("JSON.txt");
			int sizeOfJSONFile = inputStream.available();
			byte[] bytes = new byte[sizeOfJSONFile];
			inputStream.read(bytes);
			inputStream.close();

			jsonString = new String(bytes, "UTF-8");
			jsonObject = new JSONObject(jsonString);

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (JSONException x) {
			x.printStackTrace();
			return null;
		}
		return jsonObject;
	}

	/** launches a website */
	public void launchWebsite(String url) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

	/** calls someone */
	public void call(String phone) {
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:" + phone));
		startActivity(callIntent);
	}

	/** sets the json data according to the user's options */
	public void setJSONData() {

		ParseUser user = ParseUser.getCurrentUser();
		JSONObject mainObject = parseJSONData();
		JSONObject object;
		JSONObject x;
		tvTitleFirst.setText("No interest selected");
		tvTitleSecond.setText("No interest selected");
		tvTitleThird.setText("No interest selected");
		btnActionSecond.setText("No interest selected");
		tvDescriptionFirst.setText("No interest selected");
		tvDescriptionThird.setText("No interest selected");
		try {
			object = mainObject.getJSONObject("first");
			if (user.getBoolean("izabela") == true) {
				x = object.getJSONObject("viral");
				tvTitleSecond.setText(x.getString("label"));
				btnActionSecond.setText("Attend");
				final String link = x.getString("link");
				btnActionSecond.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						launchWebsite(link);

					}
				});

			} else if (user.getBoolean("music") == true) {
				x = object.getJSONObject("music");
				tvTitleSecond.setText("Music");
				btnActionSecond.setText(x.getString("label"));
				final String link = x.getString("link");
				btnActionSecond.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						launchWebsite(link);

					}
				});
			} else if (user.getBoolean("call") == true) {
				x = object.getJSONObject("call");
				tvTitleSecond.setText("Call");
				btnActionSecond.setText(x.getString("label"));
				final String link = x.getString("link");
				btnActionSecond.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						call(link);

					}
				});
			}
			object = mainObject.getJSONObject("second");
			if (user.getBoolean("bioshock") == true) {
				x = object.getJSONObject("bioshock");
				tvTitleFirst.setText(x.getString("label"));
				tvDescriptionFirst.setText("Get news for your games");
				final String link = x.getString("link");
				tvDescriptionFirst.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						launchWebsite(link);

					}
				});
			} else if (user.getBoolean("massEffect") == true) {
				x = object.getJSONObject("massEffect");
				tvTitleFirst.setText(x.getString("label"));
				tvDescriptionFirst.setText("Get news for your games");
				final String link = x.getString("link");
				tvDescriptionFirst.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						launchWebsite(link);

					}
				});
			}
			object = mainObject.getJSONObject("third");
			if (user.getBoolean("starWars") == true) {
				x = object.getJSONObject("starwars");
				tvTitleThird.setText(x.getString("label"));
				tvDescriptionThird.setText(x.getString("description"));
			} else if (user.getBoolean("ageUltron") == true) {
				x = object.getJSONObject("avengers");
				tvTitleThird.setText(x.getString("label"));
				tvDescriptionThird.setText(x.getString("description"));
			}

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
