package odigeo.nativeteam.schedulersexample;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KillerMethods {

	private ImagesService imagesService;

	public KillerMethods() {

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://jsonplaceholder.typicode.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		imagesService = retrofit.create(ImagesService.class);
	}

	public void killerMethod(final OnKillerMethodFinish onKillerMethodFinish) {
		imagesService.listPhotos().enqueue(new Callback<List<CustomPhoto>>() {
			@Override
			public void onResponse(Call<List<CustomPhoto>> call, Response<List<CustomPhoto>> response) {
				onKillerMethodFinish.onFinish(response.body());
			}

			@Override
			public void onFailure(Call<List<CustomPhoto>> call, Throwable t) {
				onKillerMethodFinish.onFinish(new ArrayList<CustomPhoto>());
			}
		});
	}

}
