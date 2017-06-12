package odigeo.nativeteam.schedulersexample;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ImagesService {

	@GET("photos")
	Call<List<CustomPhoto>> listPhotos();
}
