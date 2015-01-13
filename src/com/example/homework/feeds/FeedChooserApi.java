package com.example.homework.feeds;

import com.example.homework.base.BaseApiInterface;
import com.example.homework.base.ModelFailureResponse;
import com.example.homework.base.ModelSuccessResponse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class FeedChooserApi extends BaseApiInterface {
	boolean izabela = false;
	boolean music = false;
	boolean mom = false;
	boolean avengers = false;
	boolean starwars = false;
	boolean bioshock = false;
	boolean masseffect = false;

	public void onSaveFeeds(String feed1, String feed2, String feed3) {
		ParseUser user = ParseUser.getCurrentUser();
		if (feed1.equals("Ziua Izabellei")) {
			izabela = true;
		} else if (feed1.equals("Music")) {
			music = true;
		} else if (feed1.equals("Call Mom")) {
			mom = true;
		}

		if (feed2.equals("Age of Ultron")) {
			avengers = true;
		} else if (feed2.equals("Star Wars")) {
			starwars = true;
		}
		if (feed3.equals("Bioshock")) {
			bioshock = true;
		} else if (feed3.equals("Mass Effect")) {
			masseffect = true;
		}
		System.out.println(avengers + " " + starwars + " " + masseffect + " "
				+ bioshock + " " + music + " " + mom + " " + izabela);
		user.put("ageUltron", avengers);
		user.put("starWars", starwars);
		user.put("massEffect", masseffect);
		user.put("bioshock", bioshock);
		user.put("music", music);
		user.put("call", mom);
		user.put("izabela", izabela);

		user.saveInBackground(new SaveCallback() {

			@Override
			public void done(ParseException e) {
				if (e == null) {
					onSaveDone("");
				} else {
					onSaveDone(e.getMessage());
				}

			}
		});
	}

	public void onSaveDone(String error) {
		if (apiListener != null) {
			if (error.equals("")) {
				apiListener.onResponse(new ModelSuccessResponse());
				izabela = false;
				music = false;
				mom = false;
				avengers = false;
				starwars = false;
				bioshock = false;
				masseffect = false;
			} else {
				ModelFailureResponse mfr = new ModelFailureResponse();
				mfr.setDescription(error);
				apiListener.onResponse(mfr);
			}
		}
	}
}
