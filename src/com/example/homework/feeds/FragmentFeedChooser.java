package com.example.homework.feeds;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.homework.R;
import com.example.homework.base.BaseFragment;
import com.example.homework.base.BaseModel;
import com.example.homework.base.ModelSuccessResponse;

@SuppressLint("ClickableViewAccessibility")
public class FragmentFeedChooser extends BaseFragment {
	@SuppressWarnings("unused")
	private Context mContext;
	RadioGroup rgMovies, rgRandom, rgGames;
	RadioButton rIzabella, rMusic, rCall, rAvengers, rStarWars, rMassEffect,
			rBioshock;
	Button btnSave;
	FeedChooserApi api = new FeedChooserApi();

	public FragmentFeedChooser() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addApiInterface(api);
	}

	@Override
	public void onViewCreated(View v, Bundle savedInstanceState) {
		super.onViewCreated(v, savedInstanceState);
		initUI(v);
		btnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onSave();
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		SCREEN_TITLE = "Change Password";
		mContext = getActivity();
		return inflater.inflate(R.layout.fragment_feed_chooser, parent, false);

	}

	@Override
	public void onResponse(BaseModel model) {
		if (model instanceof ModelSuccessResponse) {
			Toast.makeText(getActivity().getApplicationContext(),
					"Save succesful", Toast.LENGTH_LONG).show();

		} else {
			Toast.makeText(getActivity().getApplicationContext(),
					"Save failed", Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public void initUI(View view) {
		rgGames = (RadioGroup) view.findViewById(R.id.rgGames);
		rgMovies = (RadioGroup) view.findViewById(R.id.rgMovies);
		rgRandom = (RadioGroup) view.findViewById(R.id.rgRandom);
		rAvengers = (RadioButton) view.findViewById(R.id.rAvengers);
		rStarWars = (RadioButton) view.findViewById(R.id.rStarWars);
		rIzabella = (RadioButton) view.findViewById(R.id.rIzabellasBirthday);
		rMusic = (RadioButton) view.findViewById(R.id.rMusic);
		rCall = (RadioButton) view.findViewById(R.id.rCall);
		rMassEffect = (RadioButton) view.findViewById(R.id.rMassEffect);
		rBioshock = (RadioButton) view.findViewById(R.id.rBioshock);
		btnSave = (Button) view.findViewById(R.id.btnSaveFeedInterests);
	}

	@Override
	protected void onAfterStart() {
		// TODO Auto-generated method stub

	}

	public void onSave() {
		String saved1 = "";
		String saved2 = "";
		String saved3 = "";
		if (rIzabella.isChecked() == true) {
			saved1 = rIzabella.getText().toString();
		} else if (rMusic.isChecked() == true) {
			saved1 = rMusic.getText().toString();
		} else if (rCall.isChecked() == true) {
			saved1 = rCall.getText().toString();
		}
		if (rStarWars.isChecked() == true) {
			saved2 = rStarWars.getText().toString();
		} else if (rAvengers.isChecked() == true) {
			saved2 = rAvengers.getText().toString();
		}
		if (rMassEffect.isChecked() == true) {
			saved3 = rMassEffect.getText().toString();
		} else if (rBioshock.isChecked() == true) {
			saved3 = rBioshock.getText().toString();
		}
		api.onSaveFeeds(saved1, saved2, saved3);
	}

}