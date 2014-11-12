package com.example.homework.weather;

import java.util.List;

import android.location.Location;

/** 
 * Interface for specifing the methods that are needed to be implemented 
 * 
 * */
public interface WeatherDataSource {
	public interface Callback<T> {
		void onSuccess(T arg);

		void onError();
	}

	void getCurrentCondition(Location location,
			Callback<WeatherCondition> callback);

	void getForecast(Location location,
			Callback<List<WeatherCondition>> callback);
}
