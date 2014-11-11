package com.example.homework.home;

import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.homework.MainActivity;
import com.example.homework.R;
import com.example.homework.base.BaseFragment;
import com.example.homework.base.BaseModel;
import com.parse.ParseUser;

public class FragmentHome extends BaseFragment {

	private Context mContext;
	private Button btnBackground, btnSettings, btnLogout;
	private RelativeLayout mainLayout;
	private int[] colors = { Color.YELLOW, Color.BLUE, Color.CYAN, Color.RED,
			Color.MAGENTA, Color.GREEN, Color.LTGRAY };
	private Random rand;

	public FragmentHome() {
	}

	public static FragmentHome newInstance() {
		return new FragmentHome();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		rand = new Random();
	}

	@Override
	public void onViewCreated(View v, Bundle savedInstanceState) {
		super.onViewCreated(v, savedInstanceState);
		initUI(v);

		btnSettings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).launchSettings();

			}
		});

		btnBackground.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mainLayout.setBackgroundColor(colors[rand
						.nextInt(colors.length)]);

			}
		});

		btnLogout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ParseUser.logOut();

			}
		});

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		SCREEN_TITLE = "Home";
		mContext = getActivity();
		return inflater.inflate(R.layout.fragment_home, parent, false);
	}

	@Override
	public void onResponse(BaseModel model) {

	}

	@Override
	public void initUI(View view) {
		btnSettings = (Button) view.findViewById(R.id.btn_accountSettings);
		btnLogout = (Button) view.findViewById(R.id.btn_logout);
		btnBackground = (Button) view.findViewById(R.id.btn_changeBg);
		mainLayout = (RelativeLayout) view
				.findViewById(R.id.fragment_container);

	}

}
