package com.example.homework;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.homework.base.BaseFragmentActivity;
import com.example.homework.home.FragmentHome;
import com.example.homework.login.FragmentLogin;
import com.example.homework.register.FragmentRegister;

/**
 * First Activity initialization of buttons and actions
 * 
 * @author Andrei
 * 
 */
public class MainActivity extends BaseFragmentActivity {

	private Context mContext;
	private TextView tvTitle;
	private FragmentMain fragmentMain;
	private FragmentLogin fragmentLogin;
	private FragmentRegister fragmentRegister;
	private FragmentHome fragmentHome;

	@Override
	public void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		setNavTitle("Homework");
		launchMain();
	}

	/** launches Main Fragment */
	private void launchMain() {
		if (fragmentMain == null) {
			fragmentMain = new FragmentMain();
		}
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();

		Fragment currentFrag = getFragmentManager().findFragmentById(
				R.id.fragments_container);

		if (currentFrag != fragmentMain) {

			if (currentFrag != null) {
				transaction.remove(currentFrag);
			}

			if (!fragmentMain.isAdded()) {
				transaction.add(R.id.fragments_container, fragmentMain);
			} else {
				transaction.show(fragmentMain);
			}

			transaction.addToBackStack(null);
			transaction.commit();

		}

	}

	/** launches Login fragment */
	public void launchLogin() {
		if (fragmentLogin == null)
			fragmentLogin = new FragmentLogin();

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

		Fragment currentFrag = getFragmentManager().findFragmentById(
				R.id.fragments_container);

		if (currentFrag != fragmentLogin) {

			if (currentFrag != null) {
				transaction.remove(currentFrag);
			}

			if (!fragmentLogin.isAdded()) {
				transaction.add(R.id.fragments_container, fragmentLogin);
			} else {
				transaction.show(fragmentLogin);
			}

			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();

		}

	}

	/** launches Register fragment */
	public void launchRegister() {
		if (fragmentRegister == null)
			fragmentRegister = new FragmentRegister();

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

		Fragment currentFrag = getFragmentManager().findFragmentById(
				R.id.fragments_container);

		if (currentFrag != fragmentRegister) {

			if (currentFrag != null) {
				transaction.remove(currentFrag);
			}

			if (!fragmentRegister.isAdded()) {
				transaction.add(R.id.fragments_container, fragmentRegister);
			} else {
				transaction.show(fragmentRegister);
			}

			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();

		}

	}

	/** launch Home Fragment */
	public void launchHome() {
		if (fragmentHome == null) {
			fragmentHome = new FragmentHome();
		}
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();

		Fragment currentFrag = getFragmentManager().findFragmentById(
				R.id.fragments_container);

		if (currentFrag != fragmentHome) {

			if (currentFrag != null) {
				transaction.remove(currentFrag);
			}

			if (!fragmentHome.isAdded()) {
				transaction.add(R.id.fragments_container, fragmentHome);
			} else {
				transaction.show(fragmentHome);
			}

			transaction.addToBackStack(null);
			transaction.commit();

		}

	}

	/** sets the Navigation Bar title */
	private void setNavTitle(String title) {
		if (title == null) {
			return;
		}
		if (title.equals("Homework")) {
		} else {
			tvTitle.setText(title);
		}
	}
}